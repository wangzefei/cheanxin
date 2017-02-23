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
public class PricingPendingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_PRICING.name());

    public PricingPendingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public Integer getValue() {
        return 3;
    }

    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        super.checkAuthority(user);

        Assert.notNull(unsavedLoan.getVehicleDealPrice(), "Vehicle deal price is empty.");

        savedLoan.setVehicleDealPrice(unsavedLoan.getVehicleDealPrice());
        return super.updateLoan(unsavedLoan, savedLoan, false);
    }

    @Override
    public Loan submit(User user, Loan unsavedLoan, Loan savedLoan) {
        super.checkAuthority(user);

        Assert.notNull(savedLoan.getVehicleDealPrice(), "Vehicle deal price is empty.");

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
        throw new UnauthorizedException("Undefined loan operation.");
    }
}
