package cheanxin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 16/12/30.
 */
@Entity
public class Product {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // 自增id
    private Long id;

    @NotNull
    @Min(0)
    private Long productTemplateId;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Min(1)
    @Max(16)
    private Integer productType;

    @NotNull
    @Min(1)
    @Max(16)
    private Integer paybackType;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer minAvailableRate;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer maxAvailableRate;

    @NotNull
    @Size(min = 1, max = 50)
    private String availableTerms;

    @NotNull
    @Min(1)
    @Max(16)
    private Integer loanPolicy;

    @NotNull
    @Size(min = 1, max = 50)
    private String loanBenifitPerMonth;

    @NotNull
    @Min(0)
    private Long cityId;

    @NotNull
    @Min(0)
    @Max(8)
    private Integer status;

    private Long modifiedTime;

    private Long createdTime;

    private Long creatorUid;


}
