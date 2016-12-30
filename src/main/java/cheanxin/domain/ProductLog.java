package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 16/12/30.
 */
@Entity
@Table(indexes = {@Index(name = "idx_product_id", columnList = "productId"), @Index(name = "idx_operator_uid", columnList = "operatorUid")})
public class ProductLog {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // 自增id
    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    private Long operatorUid;

    @NotNull
    @Min(0)
    @Max(8)
    private Integer operatorType;

    @Size(max = 200)
    private String remark;

    private Long createdTime;
}
