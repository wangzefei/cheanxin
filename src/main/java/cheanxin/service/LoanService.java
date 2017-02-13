package cheanxin.service;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import org.springframework.data.domain.Page;

/**
 * Created by 273cn on 17/02/08.
 */
public interface LoanService {
    Loan save(Loan unsavedLoan);
    Loan getOne(long id);
    Page<Loan> list(String financialCommissioner, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, int status, int page, int size);
    Loan updateStatus(User user, Loan fromLoan, Loan toLoan);
}
