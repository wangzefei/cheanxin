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
public class SecondReviewPendingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_SECOND_REVIEW.name());

    public SecondReviewPendingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public Integer getValue() {
        return 4;
    }

    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) {
        throw new UnauthorizedException("Undefined loan operation.");
    }

    @Override
    public Loan submit(User user, Loan unsavedLoan, Loan savedLoan) {
        throw new UnauthorizedException("Undefined loan operation.");
    }

    @Override
    public Loan accept(User user, Loan unsavedLoan, Loan savedLoan) {
        super.checkAuthority(user);

        LoanState targetState = super.getLoanService().getContractPendingLoanState();
        savedLoan.setReviewRemark(unsavedLoan.getReviewRemark());
        return super.updateLoanState(user, unsavedLoan, savedLoan, targetState);
    }

    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        super.checkAuthority(user);

        Assert.notNull(unsavedLoan.getReviewRemark(), "Review remark can not be null.");

        LoanState targetState = super.getLoanService().isLoanLogExists(savedLoan.getId(), this, super.getLoanService().getMaterialsPendingLoanState())
                ? super.getLoanService().getAbortedLoanState()
                : super.getLoanService().getMaterialsPendingLoanState();
        savedLoan.setReviewRemark(unsavedLoan.getReviewRemark());
        return super.updateLoanState(user, unsavedLoan, savedLoan, targetState);
    }

    @Override
    public Loan abort(User user, Loan unsavedLoan, Loan savedLoan) {
        throw new UnauthorizedException("Undefined loan operation.");
    }
}
