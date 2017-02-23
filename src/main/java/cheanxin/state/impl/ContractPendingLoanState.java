package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.enums.Authority;
import cheanxin.exceptions.UnauthorizedException;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Created by Jawinton on 2017/2/22.
 */
public class ContractPendingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_CONTRACT.name());

    public ContractPendingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public Integer getValue() {
        return 6;
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

        LoanState targetState = super.getLoanService().getDisbursementPendingLoanState();
        return super.updateLoanState(user, unsavedLoan, savedLoan, targetState);
    }

    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        super.checkAuthority(user);

        LoanState targetState = super.getLoanService().isLoanLogExists(unsavedLoan.getId(), this, super.getLoanService().getSchemePendingLoanState())
                ? super.getLoanService().getAbortedLoanState()
                : super.getLoanService().getSchemePendingLoanState();
        return super.updateLoanState(user, unsavedLoan, savedLoan, targetState);
    }

    @Override
    public Loan abort(User user, Loan unsavedLoan, Loan savedLoan) {
        throw new UnauthorizedException("Undefined loan operation.");
    }
}
