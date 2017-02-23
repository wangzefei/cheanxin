package cheanxin.service;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.enums.LoanOperation;
import cheanxin.state.LoanState;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by 273cn on 17/02/08.
 */
public abstract class LoanService extends LoanListService<Loan> {
    public abstract Loan save(Loan unsavedLoan, boolean isLog);
    public abstract Loan getOne(long id);
    public abstract Loan updateStatus(User user, Loan unsavedLoan, Loan savedLoan, boolean isLog);
    public abstract boolean isLoanLogExists(long id, LoanState fromLoanState, LoanState toLoanState);
    public abstract Loan handle(User user, LoanOperation loanOperation, Loan unsavedLoan) throws Throwable;

    public abstract LoanState getAbortedLoanState();
    public abstract LoanState getDraftLoanState();
    public abstract LoanState getFirstReviewPendingLoanState();
    public abstract LoanState getPricingPendingLoanState();
    public abstract LoanState getSecondReviewPendingLoanState();
    public abstract LoanState getMaterialsPendingLoanState();
    public abstract LoanState getContractPendingLoanState();
    public abstract LoanState getSchemePendingLoanState();
    public abstract LoanState getDisbursementPendingLoanState();
}
