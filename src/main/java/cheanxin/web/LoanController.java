package cheanxin.web;

import cheanxin.constant.LogicConstants;
import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.enums.LoanOperation;
import cheanxin.exceptions.ResourceNotFoundException;
import cheanxin.service.LoanLogService;
import cheanxin.service.LoanService;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Loan> save(
            @Valid @RequestBody Loan unsavedLoan,
            Errors errors,
            @AuthenticationPrincipal User user) {
        String errorMessage = errors.hasErrors() ? errors.getAllErrors().get(0).getDefaultMessage() : null;
        Assert.isNull(errorMessage, errorMessage);

        long now = System.currentTimeMillis() / 1000;
        unsavedLoan.setCreatorUsername(user.getUsername());
        unsavedLoan.setCreatedTime(now);
        unsavedLoan.setModifiedTime(now);
        unsavedLoan.setStatus(loanService.getDraftLoanState().getValue());
        unsavedLoan.setVehicleDealPrice(null);
        return new ResponseEntity<>(loanService.save(unsavedLoan, true), HttpStatus.CREATED);
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
            @RequestParam(value = "loanOperate", defaultValue = "0") int loanOperate,
            @RequestBody Loan unsavedLoan,
            @AuthenticationPrincipal User user) throws Throwable {
        unsavedLoan.setId(id);
        unsavedLoan.setModifiedTime(System.currentTimeMillis() / 1000);

        LoanOperation loanOperation = LoanOperation.valueOf(loanOperate);
        Assert.notNull(loanOperation, "Loan operate error.");

        Loan savedLoan = loanService.handle(user, loanOperation, unsavedLoan);
        if (savedLoan == null) {
            throw new ResourceNotFoundException("Loan", "id", String.valueOf(id));
        }

        return new ResponseEntity<>(savedLoan, HttpStatus.OK);
    }
}
