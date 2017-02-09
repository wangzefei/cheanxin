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
    FIRST_REVIEW_PENDING_TO_FIRST_REVIEW_ACCEPTED(1, LoanStatus.FIRST_REVIEW_PENDING, LoanStatus.FIRST_REVIEW_ACCEPTED, new SimpleGrantedAuthority(Authority.ROLE_FIRST_REVIEW_PENDING_TO_FIRST_REVIEW_ACCEPTED.name())),
    FIRST_REVIEW_PENDING_TO_FIRST_REVIEW_REJECTED(1, LoanStatus.FIRST_REVIEW_PENDING, LoanStatus.FIRST_REVIEW_REJECTED, new SimpleGrantedAuthority(Authority.ROLE_FIRST_REVIEW_PENDING_TO_FIRST_REVIEW_REJECTED.name()));

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
