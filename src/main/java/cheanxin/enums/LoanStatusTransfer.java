package cheanxin.enums;

import cheanxin.domain.User;
import cheanxin.exceptions.UnauthorizedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;


/**
 * Created by Jawinton on 17/02/09.
 */
public enum LoanStatusTransfer {
    FIRST_REVIEW_ACCEPTED_TO_FIRST_REVIEW_ACCEPTED(1, LoanStatus.FIRST_REVIEW_ACCEPTED, LoanStatus.FIRST_REVIEW_ACCEPTED, new SimpleGrantedAuthority(Authority.ROLE_FIRST_REVIEW_ACCEPTED_TO_FIRST_REVIEW_ACCEPTED.name())),
    FIRST_REVIEW_ACCEPTED_TO_SECOND_REVIEW_PENDING(2, LoanStatus.FIRST_REVIEW_ACCEPTED, LoanStatus.SECOND_REVIEW_PENDING, new SimpleGrantedAuthority(Authority.ROLE_FIRST_REVIEW_ACCEPTED_TO_SECOND_REVIEW_PENDING.name())),
    SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_ACCEPTED(3, LoanStatus.SECOND_REVIEW_PENDING, LoanStatus.SECOND_REVIEW_ACCEPTED, new SimpleGrantedAuthority(Authority.ROLE_SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_ACCEPTED.name())),
    SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_REJECTED(4, LoanStatus.SECOND_REVIEW_PENDING, LoanStatus.SECOND_REVIEW_REJECTED, new SimpleGrantedAuthority(Authority.ROLE_SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_REJECTED.name())),
    SECOND_REVIEW_REJECTED_TO_SECOND_REVIEW_PENDING(5, LoanStatus.SECOND_REVIEW_REJECTED, LoanStatus.SECOND_REVIEW_PENDING, new SimpleGrantedAuthority(Authority.ROLE_SECOND_REVIEW_REJECTED_TO_SECOND_REVIEW_PENDING.name())),
    SECOND_REVIEW_REJECTED_TO_LOAN_ABORTED(6, LoanStatus.SECOND_REVIEW_REJECTED, LoanStatus.LOAN_ABORTED, new SimpleGrantedAuthority(Authority.ROLE_SECOND_REVIEW_REJECTED_TO_LOAN_ABORTED.name())),
    SECOND_REVIEW_ACCEPTED_TO_CONTRACT_ACCEPTED(7, LoanStatus.SECOND_REVIEW_ACCEPTED, LoanStatus.CONTRACT_ACCEPTED, new SimpleGrantedAuthority(Authority.ROLE_SECOND_REVIEW_ACCEPTED_TO_CONTRACT_ACCEPTED.name())),
    SECOND_REVIEW_ACCEPTED_TO_CONTRACT_REJECTED(8, LoanStatus.SECOND_REVIEW_ACCEPTED, LoanStatus.CONTRACT_REJECTED, new SimpleGrantedAuthority(Authority.ROLE_SECOND_REVIEW_ACCEPTED_TO_CONTRACT_REJECTED.name())),
    CONTRACT_REJECTED_TO_SECOND_REVIEW_PENDING(9, LoanStatus.CONTRACT_REJECTED, LoanStatus.SECOND_REVIEW_PENDING, new SimpleGrantedAuthority(Authority.ROLE_CONTRACT_REJECTED_TO_SECOND_REVIEW_PENDING.name())),
    CONTRACT_REJECTED_TO_LOAN_ABORTED(10, LoanStatus.CONTRACT_REJECTED, LoanStatus.LOAN_ABORTED, new SimpleGrantedAuthority(Authority.ROLE_CONTRACT_REJECTED_TO_LOAN_ABORTED.name()));

    private final Integer value;
    private final LoanStatus fromStatus;
    private final LoanStatus toStatus;
    private final GrantedAuthority authority;

    LoanStatusTransfer(Integer value, LoanStatus fromStatus, LoanStatus toStatus, GrantedAuthority authority) {
        this.value = value;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.authority = authority;
    }

    public Integer getValue() {
        return value;
    }

    public static LoanStatusTransfer valueOf(int fromStatus, int toStatus) {
        for (LoanStatusTransfer productStatusTransfer : LoanStatusTransfer.values()) {
            if (productStatusTransfer.fromStatus.value() == fromStatus && productStatusTransfer.toStatus.value() == toStatus)
                return productStatusTransfer;
        }
        return null;
    }

    public static void checkAuthority(User user, int fromStatus, int toStatus) throws UnauthorizedException {
        LoanStatusTransfer productStatusTransfer = LoanStatusTransfer.valueOf(fromStatus, toStatus);
        if (productStatusTransfer == null)
            throw new UnauthorizedException("Undefined state transfer.");
        GrantedAuthority neededAuthority = productStatusTransfer.authority;
        Collection<? extends GrantedAuthority> userAuthorities = user.getAuthorities();
        if (userAuthorities == null || !userAuthorities.contains(neededAuthority))
            throw new UnauthorizedException("User unauthorized.");

    }
}
