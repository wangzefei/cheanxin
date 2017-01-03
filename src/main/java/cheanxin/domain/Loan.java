package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 16/12/30.
 */
@Entity
@Table(indexes = {@Index(name = "idx_product_id", columnList = "productId")})
public class Loan {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // 自增id
    private Long id;

    private Long productId;

    @Size(max = 50)
    private String productName;

    @Min(1)
    @Max(16)
    private Integer productType;

    @Min(1)
    @Max(10)
    private Integer loanRate;

    @Min(1)
    @Max(360)
    private Integer loadTerms;

    @Digits(integer = 2, fraction = 4)
    private Float loanMonthlyInterestRate;

    @Size(max = 1000)
    private String applicationPicUrl;

    @Size(max = 200)
    private String remark;

    private Long extSourceId;

    private Long extApplicantId;

    private Long extCoApplicantId;

    private Long extGuarantorId;

    private Long extVehicleId;

    @NotNull
    @Min(0)
    @Max(32)
    private Integer status;

    private Long createdTime;
}
