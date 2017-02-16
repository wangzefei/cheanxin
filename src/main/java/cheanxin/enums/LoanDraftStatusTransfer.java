package cheanxin.enums;

import cheanxin.domain.User;
import cheanxin.exceptions.UnauthorizedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;


/**
 * Created by Jawinton on 17/02/09.
 */
public enum LoanDraftStatusTransfer {
    FIRST_DRAFT_TO_FIRST_DRAFT(1, LoanDraftStatus.FIRST_DRAFT, LoanDraftStatus.FIRST_DRAFT, new SimpleGrantedAuthority(Authority.ROLE_FIRST_DRAFT_TO_FIRST_DRAFT.name())),
    FIRST_DRAFT_TO_SECOND_DRAFT(2, LoanDraftStatus.FIRST_DRAFT, LoanDraftStatus.SECOND_DRAFT, new SimpleGrantedAuthority(Authority.ROLE_FIRST_DRAFT_TO_SECOND_DRAFT.name())),
    SECOND_DRAFT_TO_SECOND_DRAFT(3, LoanDraftStatus.SECOND_DRAFT, LoanDraftStatus.SECOND_DRAFT, new SimpleGrantedAuthority(Authority.ROLE_SECOND_DRAFT_TO_SECOND_DRAFT.name())),
    SECOND_DRAFT_TO_DRAFT_ABORTED(4, LoanDraftStatus.SECOND_DRAFT, LoanDraftStatus.DRAFT_ABORTED, new SimpleGrantedAuthority(Authority.ROLE_SECOND_DRAFT_TO_DRAFT_ABORTED.name())),
    SECOND_DRAFT_TO_DRAFT_REJECTED(5, LoanDraftStatus.SECOND_DRAFT, LoanDraftStatus.DRAFT_REJECTED, new SimpleGrantedAuthority(Authority.ROLE_SECOND_DRAFT_TO_DRAFT_REJECTED.name())),
    DRAFT_REJECTED_TO_DRAFT_REJECTED(6, LoanDraftStatus.DRAFT_REJECTED, LoanDraftStatus.DRAFT_REJECTED, new SimpleGrantedAuthority(Authority.ROLE_FIRST_DRAFT_TO_FIRST_DRAFT.name())),
    DRAFT_REJECTED_TO_SECOND_DRAFT(7, LoanDraftStatus.DRAFT_REJECTED, LoanDraftStatus.SECOND_DRAFT, new SimpleGrantedAuthority(Authority.ROLE_FIRST_DRAFT_TO_SECOND_DRAFT.name()));

    private final Integer value;
    private final LoanDraftStatus fromStatus;
    private final LoanDraftStatus toStatus;
    private final GrantedAuthority authority;

    LoanDraftStatusTransfer(Integer value, LoanDraftStatus fromStatus, LoanDraftStatus toStatus, GrantedAuthority authority) {
        this.value = value;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.authority = authority;
    }

    public Integer getValue() {
        return value;
    }

    public static LoanDraftStatusTransfer valueOf(int fromStatus, int toStatus) {
        for (LoanDraftStatusTransfer productStatusTransfer : LoanDraftStatusTransfer.values()) {
            if (productStatusTransfer.fromStatus.value() == fromStatus && productStatusTransfer.toStatus.value() == toStatus)
                return productStatusTransfer;
        }
        return null;
    }

    public static void checkAuthority(User user, int fromStatus, int toStatus) throws UnauthorizedException {
        LoanDraftStatusTransfer productStatusTransfer = LoanDraftStatusTransfer.valueOf(fromStatus, toStatus);
        if (productStatusTransfer == null)
            throw new UnauthorizedException("Undefined state transfer.");
        GrantedAuthority neededAuthority = productStatusTransfer.authority;
        Collection<? extends GrantedAuthority> userAuthorities = user.getAuthorities();
        if (userAuthorities == null || !userAuthorities.contains(neededAuthority))
            throw new UnauthorizedException("User unauthorized.");

    }
}
