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
    FIRST_REVIEW_PENDING_TO_FIRST_REVIEW_PENDING(1, LoanStatus.FIRST_REVIEW_PENDING, LoanStatus.FIRST_REVIEW_PENDING, new SimpleGrantedAuthority(Authority.ROLE_FIRST_REVIEW_PENDING_TO_FIRST_REVIEW_PENDING.name())),
    FIRST_REVIEW_PENDING_TO_FIRST_REVIEW_ACCEPTED(2, LoanStatus.FIRST_REVIEW_PENDING, LoanStatus.FIRST_REVIEW_ACCEPTED, new SimpleGrantedAuthority(Authority.ROLE_FIRST_REVIEW_PENDING_TO_FIRST_REVIEW_ACCEPTED.name())),
    FIRST_REVIEW_PENDING_TO_FIRST_REVIEW_REJECTED(3, LoanStatus.FIRST_REVIEW_PENDING, LoanStatus.FIRST_REVIEW_REJECTED, new SimpleGrantedAuthority(Authority.ROLE_FIRST_REVIEW_PENDING_TO_FIRST_REVIEW_REJECTED.name())),
    FIRST_REVIEW_REJECTED_TO_FIRST_REVIEW_PENDING(4, LoanStatus.FIRST_REVIEW_REJECTED, LoanStatus.FIRST_REVIEW_PENDING, new SimpleGrantedAuthority(Authority.ROLE_FIRST_REVIEW_REJECTED_TO_FIRST_REVIEW_PENDING.name())),
    FIRST_REVIEW_ACCEPTED_TO_SECOND_REVIEW_PENDING(5, LoanStatus.FIRST_REVIEW_ACCEPTED, LoanStatus.SECOND_REVIEW_PENDING, new SimpleGrantedAuthority(Authority.ROLE_FIRST_REVIEW_ACCEPTED_TO_SECOND_REVIEW_PENDING.name())),
    SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_ACCEPTED(6, LoanStatus.SECOND_REVIEW_PENDING, LoanStatus.SECOND_REVIEW_ACCEPTED, new SimpleGrantedAuthority(Authority.ROLE_SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_ACCEPTED.name())),
    SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_REJECTED(7, LoanStatus.SECOND_REVIEW_PENDING, LoanStatus.SECOND_REVIEW_REJECTED, new SimpleGrantedAuthority(Authority.ROLE_SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_REJECTED.name())),
    SECOND_REVIEW_REJECTED_TO_SECOND_REVIEW_PENDING(8, LoanStatus.SECOND_REVIEW_REJECTED, LoanStatus.SECOND_REVIEW_PENDING, new SimpleGrantedAuthority(Authority.ROLE_SECOND_REVIEW_REJECTED_TO_SECOND_REVIEW_PENDING.name())),
    SECOND_REVIEW_REJECTED_TO_SECOND_REVIEW_ABORTED(9, LoanStatus.SECOND_REVIEW_REJECTED, LoanStatus.SECOND_REVIEW_ABORTED, new SimpleGrantedAuthority(Authority.ROLE_SECOND_REVIEW_REJECTED_TO_SECOND_REVIEW_ABORTED.name())),
    SECOND_REVIEW_ACCEPTED_TO_CONTRACT_ACCEPTED(10, LoanStatus.SECOND_REVIEW_ACCEPTED, LoanStatus.CONTRACT_ACCEPTED, new SimpleGrantedAuthority(Authority.ROLE_SECOND_REVIEW_ACCEPTED_TO_CONTRACT_ACCEPTED.name())),
    SECOND_REVIEW_ACCEPTED_TO_CONTRACT_REJECTED(11, LoanStatus.SECOND_REVIEW_ACCEPTED, LoanStatus.CONTRACT_REJECTED, new SimpleGrantedAuthority(Authority.ROLE_SECOND_REVIEW_ACCEPTED_TO_CONTRACT_REJECTED.name())),
    CONTRACT_REJECTED_TO_SECOND_REVIEW_PENDING(12, LoanStatus.CONTRACT_REJECTED, LoanStatus.SECOND_REVIEW_PENDING, new SimpleGrantedAuthority(Authority.ROLE_CONTRACT_REJECTED_TO_SECOND_REVIEW_PENDING.name())),
    CONTRACT_REJECTED_TO_CONTRACT_ABORTED(13, LoanStatus.CONTRACT_REJECTED, LoanStatus.CONTRACT_ABORTED, new SimpleGrantedAuthority(Authority.ROLE_CONTRACT_REJECTED_TO_CONTRACT_ABORTED.name()));

    private final int value;
    private final LoanStatus fromStatus;
    private final LoanStatus toStatus;
    private final GrantedAuthority authority;

    LoanStatusTransfer(int value, LoanStatus fromStatus, LoanStatus toStatus, GrantedAuthority authority) {
        this.value = value;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.authority = authority;
    }

    public int getValue() {
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
