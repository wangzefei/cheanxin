package cheanxin.state;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.exceptions.UnauthorizedException;
import cheanxin.service.LoanService;
import cheanxin.util.ReflectUtil;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Jawinton on 2017/2/22.
 */
public abstract class LoanState {
    private GrantedAuthority neededAuthority;
    private LoanService loanService;
    public LoanState(GrantedAuthority neededAuthority, LoanService loanService) {
        this.neededAuthority = neededAuthority;
        this.loanService = loanService;
    }
    public abstract Integer getValue();
    public abstract Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException;
    public abstract Loan submit(User user, Loan unsavedLoan, Loan savedLoan);
    public abstract Loan accept(User user, Loan unsavedLoan, Loan savedLoan);
    public abstract Loan reject(User user, Loan unsavedLoan, Loan savedLoan);
    public abstract Loan abort(User user, Loan unsavedLoan, Loan savedLoan);

    protected LoanService getLoanService() {
        return loanService;
    }

    protected void checkAuthority(User user) {
        Collection<? extends GrantedAuthority> userAuthorities = user.getAuthorities();
        if (userAuthorities == null || !userAuthorities.contains(neededAuthority)) {
            throw new UnauthorizedException("Unauthorized Operation.");
        }
    }

    protected Loan checkSelfAuthority(User user, Loan savedLoan) {
        if (!user.getUsername().equals(savedLoan.getCreatorUsername())) {
            throw new UnauthorizedException("You do not own this loan.");
        }
        return savedLoan;
    }

    protected Loan updateLoanState(User user, Loan unsavedLoan, Loan savedLoan, LoanState targetState) {
        unsavedLoan.setStatus(savedLoan.getStatus());
        savedLoan.setStatus(targetState.getValue());
        return loanService.updateStatus(user, unsavedLoan, savedLoan, true);
    }

    protected Loan updateLoan(Loan unsavedLoan, Loan savedLoan, boolean isMergeNotNullAttribute) throws IllegalAccessException {
        if (isMergeNotNullAttribute) {
            ReflectUtil.mergeObject(unsavedLoan, savedLoan);
        }
        savedLoan.setStatus(this.getValue());
        savedLoan.setModifiedTime(unsavedLoan.getModifiedTime());
        return loanService.save(savedLoan, false);
    }
}
