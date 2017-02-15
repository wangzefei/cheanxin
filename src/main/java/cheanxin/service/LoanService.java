package cheanxin.service;

import cheanxin.domain.Loan;
import cheanxin.domain.LoanDraft;
import cheanxin.domain.User;
import org.springframework.data.domain.Page;

/**
 * Created by 273cn on 17/02/08.
 */
public abstract class LoanService extends LoanListService<Loan> {
    public abstract Loan save(Loan unsavedLoan, LoanDraft loanDraft);
    public abstract Loan getOne(long id);
    public abstract Loan updateStatus(User user, int fromStatus, Loan unsavedLoan);
}
