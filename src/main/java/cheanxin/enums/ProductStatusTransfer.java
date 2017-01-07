package cheanxin.enums;

import cheanxin.domain.Product;
import cheanxin.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


/**
 * Created by 273cn on 17/1/3.
 */
public enum ProductStatusTransfer {
    DRAFT_TO_PENDING_REVIEW(ProductStatus.DRAFT, ProductStatus.PENDING_REVIEW, null),
    PENDING_REVIEW_TO_ACCEPTED(ProductStatus.PENDING_REVIEW, ProductStatus.ACCEPTED, new SimpleGrantedAuthority(Authority.ROLE_PENDING_REVIEW_TO_ACCEPTED.name())),
    PENDING_REVIEW_TO_REJECTED(ProductStatus.PENDING_REVIEW, ProductStatus.REJECTED, new SimpleGrantedAuthority(Authority.ROLE_PENDING_REVIEW_TO_ACCEPTED.name()));

    private final ProductStatus fromStatus;
    private final ProductStatus toStatus;
    private final GrantedAuthority authority;

    ProductStatusTransfer(ProductStatus fromStatus, ProductStatus toStatus, GrantedAuthority authority) {
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.authority = authority;
    }

    private static ProductStatusTransfer valueOf(int fromStatus, int toStatus) {
        for (ProductStatusTransfer productStatusTransfer : ProductStatusTransfer.values()) {
            if (productStatusTransfer.fromStatus.value() == fromStatus && productStatusTransfer.toStatus.value() == toStatus)
                return productStatusTransfer;
        }
        return null;
    }

    public static boolean hasAuthority(User user, Product product, int toStatus) {
        ProductStatusTransfer productStatusTransfer = ProductStatusTransfer.valueOf(product.getStatus(), toStatus);
        if (productStatusTransfer == null) return false;
        GrantedAuthority neededAuthority = productStatusTransfer.authority;
        if (neededAuthority == null) {
            return user.getUsername().equals(product.getCreatorUsername());
        }
        return user.getAuthorities().contains(neededAuthority);
    }
}
