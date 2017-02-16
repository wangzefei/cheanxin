package cheanxin.web;

import cheanxin.constant.LogicConstants;
import cheanxin.domain.Loan;
import cheanxin.domain.LoanDraft;
import cheanxin.domain.User;
import cheanxin.enums.LoanDraftStatus;
import cheanxin.enums.LoanStatus;
import cheanxin.enums.LoanStatusTransfer;
import cheanxin.service.LoanDraftService;
import cheanxin.service.LoanListService;
import cheanxin.service.LoanLogService;
import cheanxin.service.LoanService;
import cheanxin.util.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Jawinton on 17/02/09.
 */
@RestController
@RequestMapping("/loans")
public class LoanController extends BaseController {
    @Autowired
    LoanService loanService;

    @Autowired
    LoanLogService loanLogService;

    @Autowired
    LoanDraftService loanDraftService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Loan> save(
            @Valid @RequestBody Loan unsavedLoan,
            Errors errors,
            @AuthenticationPrincipal User user) {
        String errorMessage = errors.hasErrors() ? errors.getAllErrors().get(0).getDefaultMessage() : null;
        Assert.isNull(errorMessage, errorMessage);

        LoanDraft loanDraft = loanDraftService.getOne(unsavedLoan.getLoanDraftId());
        Assert.notNull(loanDraft, "Loan draft does not exists.");
        Assert.isTrue(LoanDraftStatus.SECOND_DRAFT.value().equals(loanDraft.getStatus()), "Loan draft status error.");

        LoanStatusTransfer.checkAuthority(user, LoanStatus.FIRST_REVIEW_ACCEPTED.value(), LoanStatus.FIRST_REVIEW_ACCEPTED.value());

        long now = System.currentTimeMillis() / 1000;

        loanDraft.setStatus(LoanDraftStatus.DRAFT_ACCEPTED.value());
        loanDraft.setModifiedTime(now);

        unsavedLoan.setCreatorUsername(user.getUsername());
        unsavedLoan.setCreatedTime(now);
        unsavedLoan.setModifiedTime(now);
        unsavedLoan.setStatus(LoanStatus.FIRST_REVIEW_ACCEPTED.value());
        return new ResponseEntity<>(loanService.save(unsavedLoan, loanDraft), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Loan> list(
            @RequestParam(value = "isFilterSelf", defaultValue = "0") boolean isFilterSelf,
            @RequestParam(value = "sourceFinancialCommissioner", defaultValue = "") String sourceFinancialCommissioner,
            @RequestParam(value = "applicantName", defaultValue = "") String applicantName,
            @RequestParam(value = "applicantMobileNumber", defaultValue = "") String applicantMobileNumber,
            @RequestParam(value = "createdTimeFrom", defaultValue = "0") long createdTimeFrom,
            @RequestParam(value = "createdTimeTo", defaultValue = "0") long createdTimeTo,
            @RequestParam(value = "status", defaultValue = "-1") int status,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size,
            @AuthenticationPrincipal User user) {
        String creatorUsername = isFilterSelf ? user.getUsername() : null;
        return loanService.list(creatorUsername, sourceFinancialCommissioner, applicantName, applicantMobileNumber, createdTimeFrom, createdTimeTo, status, page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Loan> get(@PathVariable(value = "id") long id) {
        Loan loan = loanService.getOne(id);
        Assert.notNull(loan, "Loan not found.");
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Loan> patch(
            @PathVariable(value = "id") long id,
            @RequestBody Loan unsavedLoan,
            @AuthenticationPrincipal User user) throws IllegalAccessException {
        Assert.isTrue(unsavedLoan.getRemark() != null && !unsavedLoan.getRemark().trim().isEmpty(), "Remark should not be empty.");

        Loan savedLoan = loanService.getOne(id);
        Assert.notNull(savedLoan, "Loan not found");
        if (unsavedLoan.getStatus() == null)
            unsavedLoan.setStatus(savedLoan.getStatus());
        int fromStatus = savedLoan.getStatus();

        LoanStatusTransfer.checkAuthority(user, savedLoan.getStatus().intValue(), unsavedLoan.getStatus().intValue());

        LoanStatusTransfer loanStatusTransfer = LoanStatusTransfer.valueOf(savedLoan.getStatus().intValue(), unsavedLoan.getStatus().intValue());

        if (LoanStatusTransfer.SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_REJECTED.equals(loanStatusTransfer) && loanLogService.isExists(id, loanStatusTransfer)) {
            // directly abort loan if loan review rejected for second time.
            loanStatusTransfer = LoanStatusTransfer.SECOND_REVIEW_PENDING_TO_LOAN_ABORTED;
        } else if (LoanStatusTransfer.SECOND_REVIEW_ACCEPTED_TO_CONTRACT_REJECTED.equals(loanStatusTransfer) && loanLogService.isExists(id, loanStatusTransfer)) {
            // directly abort loan if loan contract rejected for second time.
            loanStatusTransfer = LoanStatusTransfer.SECOND_REVIEW_ACCEPTED_TO_LOAN_ABORTED;
        } else if (LoanStatusTransfer.SECOND_REVIEW_REJECTED_TO_SECOND_REVIEW_PENDING.equals(loanStatusTransfer)) {
            ReflectUtil.mergeObject(unsavedLoan, savedLoan);
        } else if (LoanStatusTransfer.CONTRACT_REJECTED_TO_SECOND_REVIEW_PENDING.equals(loanStatusTransfer)) {
            savedLoan.setProductId(unsavedLoan.getProductId());
            savedLoan.setLoanRate(unsavedLoan.getLoanRate());
            savedLoan.setLoanTerms(unsavedLoan.getLoanTerms());
            savedLoan.setLoanMonthlyInterestRate(unsavedLoan.getLoanMonthlyInterestRate());
            savedLoan.setPrepaymentPenaltyRate(unsavedLoan.getPrepaymentPenaltyRate());
        }

        savedLoan.setModifiedTime(System.currentTimeMillis() / 1000);
        savedLoan.setStatus(loanStatusTransfer.getToStatus().value());
        return new ResponseEntity<>(loanService.updateStatus(user, fromStatus, savedLoan), HttpStatus.OK);
    }
}
