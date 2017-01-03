package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 16/12/30.
 * 产品
 */
@Entity
public class Product {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Min(0)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '产品模板id，如果该id为0表示为产品模板'")
    // 产品模板id，如果该id为0表示为产品模板
    private Long productTemplateId;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '产品名称'")
    // 产品名称
    private String name;

    @NotNull
    @Min(1)
    @Max(16)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '产品类型'")
    // 产品类型
    private Integer productType;

    @NotNull
    @Min(1)
    @Max(16)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '还款类型'")
    // 还款类型
    private Integer paybackType;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '最低可贷成数'")
    // 最低可贷成数
    private Integer minAvailableRate;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '最高可贷成数'")
    // 最高可贷成数
    private Integer maxAvailableRate;

    @NotNull
    @Min(1)
    @Max(3600)
    @Column(columnDefinition = "SMALLINT(4) UNSIGNED COMMENT '可贷期数'")
    // 可贷期数
    private Integer availableTerms;

    @NotNull
    @Min(1)
    @Max(16)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '贷款政策'")
    // 贷款政策
    private Integer loanPolicy;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(columnDefinition = "DECIMAL(6,4) UNSIGNED COMMENT '贷款月利率'")
    // 贷款月利率
    private Float loanMonthlyInterestRate;

    @NotNull
    @Min(0)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '城市id'")
    // 城市id
    private Long cityId;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '是否禁用'")
    // 是否禁用
    private Boolean isDiabled;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    // 修改时间
    private Long modifiedTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    // 创建时间
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建人'")
    // 创建人
    private Long creatorUid;

    public Product() {}

    public Product(Long productTemplateId, String name, Integer productType, Integer paybackType, Integer minAvailableRate, Integer maxAvailableRate, Integer availableTerms, Integer loanPolicy, Float loanMonthlyInterestRate, Long cityId, Boolean isDiabled, Long modifiedTime, Long createdTime, Long creatorUid) {
        this.productTemplateId = productTemplateId;
        this.name = name;
        this.productType = productType;
        this.paybackType = paybackType;
        this.minAvailableRate = minAvailableRate;
        this.maxAvailableRate = maxAvailableRate;
        this.availableTerms = availableTerms;
        this.loanPolicy = loanPolicy;
        this.loanMonthlyInterestRate = loanMonthlyInterestRate;
        this.cityId = cityId;
        this.isDiabled = isDiabled;
        this.modifiedTime = modifiedTime;
        this.createdTime = createdTime;
        this.creatorUid = creatorUid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductTemplateId() {
        return productTemplateId;
    }

    public void setProductTemplateId(Long productTemplateId) {
        this.productTemplateId = productTemplateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getPaybackType() {
        return paybackType;
    }

    public void setPaybackType(Integer paybackType) {
        this.paybackType = paybackType;
    }

    public Integer getMinAvailableRate() {
        return minAvailableRate;
    }

    public void setMinAvailableRate(Integer minAvailableRate) {
        this.minAvailableRate = minAvailableRate;
    }

    public Integer getMaxAvailableRate() {
        return maxAvailableRate;
    }

    public void setMaxAvailableRate(Integer maxAvailableRate) {
        this.maxAvailableRate = maxAvailableRate;
    }

    public Integer getAvailableTerms() {
        return availableTerms;
    }

    public void setAvailableTerms(Integer availableTerms) {
        this.availableTerms = availableTerms;
    }

    public Integer getLoanPolicy() {
        return loanPolicy;
    }

    public void setLoanPolicy(Integer loanPolicy) {
        this.loanPolicy = loanPolicy;
    }

    public Float getLoanMonthlyInterestRate() {
        return loanMonthlyInterestRate;
    }

    public void setLoanMonthlyInterestRate(Float loanMonthlyInterestRate) {
        this.loanMonthlyInterestRate = loanMonthlyInterestRate;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Boolean getDiabled() {
        return isDiabled;
    }

    public void setDiabled(Boolean diabled) {
        isDiabled = diabled;
    }

    public Long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getCreatorUid() {
        return creatorUid;
    }

    public void setCreatorUid(Long creatorUid) {
        this.creatorUid = creatorUid;
    }
}
