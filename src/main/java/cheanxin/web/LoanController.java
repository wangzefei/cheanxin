package cheanxin.web;

import cheanxin.domain.Loan;
import cheanxin.domain.Product;
import cheanxin.domain.User;
import cheanxin.enums.LoanStatusTransfer;
import cheanxin.enums.ProductStatusTransfer;
import cheanxin.global.Constants;
import cheanxin.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jawinton on 17/02/09.
 */
@RestController
@RequestMapping("/loans")
public class LoanController extends BaseController {
    @Autowired
    LoanService loanService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Loan> getLoans(
            @RequestParam(value = "financialCommissioner", defaultValue = "") String financialCommissioner,
            @RequestParam(value = "applicantName", defaultValue = "") String applicantName,
            @RequestParam(value = "applicantMobileNumber", defaultValue = "") String applicantMobileNumber,
            @RequestParam(value = "createdTimeFrom", defaultValue = "0L") long createdTimeFrom,
            @RequestParam(value = "createdTimeTo", defaultValue = "0L") long createdTimeTo,
            @RequestParam(value = "status", defaultValue = "-1") int status,
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_SIZE) int size) {
        return loanService.getLoans(financialCommissioner, applicantName, applicantMobileNumber, createdTimeFrom, createdTimeTo, status, page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Loan> patch(
            @PathVariable(value = "id") long id,
            @RequestBody Loan unsavedLoan,
            @AuthenticationPrincipal User user) {
        Assert.isTrue(unsavedLoan.getRemark() != null && !unsavedLoan.getRemark().trim().isEmpty(), "Remark should not be empty.");

        Loan savedLoan = loanService.findOne(id);

        Assert.notNull(savedLoan, "Loan not found");
        LoanStatusTransfer.checkAuthority(user, savedLoan.getStatus().intValue(), unsavedLoan.getStatus().intValue());

        return new ResponseEntity<>(loanService.updateStatus(user, savedLoan, unsavedLoan), HttpStatus.OK);
    }
}
