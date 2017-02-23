package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.enums.Authority;
import cheanxin.exceptions.UnauthorizedException;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

/**
 * Created by Jawinton on 2017/2/22.
 */
public class SchemePendingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_SCHEME.name());

    public SchemePendingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public Integer getValue() {
        return 7;
    }

    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        super.checkSelfAuthority(user, savedLoan);

        Assert.notNull(unsavedLoan.getLoanRate(), "Loan rate is null.");
        Assert.notNull(unsavedLoan.getLoanTerms(), "Loan terms is null.");
        Assert.notNull(unsavedLoan.getLoanMonthlyInterestRate(), "Loan monthly interest rate is null.");
        Assert.notNull(unsavedLoan.getLoanFirstPayment(), "Loan first payment is null.");

        savedLoan.setLoanRate(unsavedLoan.getLoanRate());
        savedLoan.setLoanTerms(unsavedLoan.getLoanTerms());
        savedLoan.setLoanMonthlyInterestRate(unsavedLoan.getLoanMonthlyInterestRate());
        savedLoan.setLoanFirstPayment(unsavedLoan.getLoanFirstPayment());
        return super.updateLoan(unsavedLoan, savedLoan, false);
    }

    @Override
    public Loan submit(User user, Loan unsavedLoan, Loan savedLoan) {
        super.checkAuthority(user);

        LoanState targetState = super.getLoanService().getSecondReviewPendingLoanState();
        return super.updateLoanState(user, unsavedLoan, savedLoan, targetState);
    }

    @Override
    public Loan accept(User user, Loan unsavedLoan, Loan savedLoan) {
        throw new UnauthorizedException("Undefined loan operation.");
    }

    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        throw new UnauthorizedException("Undefined loan operation.");
    }

    @Override
    public Loan abort(User user, Loan unsavedLoan, Loan savedLoan) {
        super.checkAuthority(user);

        LoanState targetState = super.getLoanService().getAbortedLoanState();
        return super.updateLoanState(user, unsavedLoan, savedLoan, targetState);
    }
}
