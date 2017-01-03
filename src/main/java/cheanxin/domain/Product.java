package cheanxin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    // 自增id
    private Long id;

    @NotNull
    @Min(0)
    // 产品模板id，如果该id为0表示为产品模板
    private Long productTemplateId;

    @NotNull
    @Size(min = 1, max = 50)
    // 产品名称
    private String name;

    @NotNull
    @Min(1)
    @Max(16)
    // 产品类型
    private Integer productType;

    @NotNull
    @Min(1)
    @Max(16)
    // 还款类型
    private Integer paybackType;

    @NotNull
    @Min(1)
    @Max(10)
    // 最低还款比例
    private Integer minAvailableRate;

    @NotNull
    @Min(1)
    @Max(10)
    // 最高还款比例
    private Integer maxAvailableRate;

    @NotNull
    @Size(min = 1, max = 50)
    // 可用期数
    private String availableTerms;

    @NotNull
    @Min(1)
    @Max(16)
    // 贷款政策
    private Integer loanPolicy;

    @NotNull
    @Size(min = 1, max = 50)
    // 贷款月利率
    private String loanBenifitPerMonth;

    @NotNull
    @Min(0)
    // 城市id
    private Long cityId;

    @NotNull
    // 是否禁用
    private Boolean isDiabled;

    // 修改时间
    private Long modifiedTime;

    // 创建时间
    private Long createdTime;

    // 创建人
    private Long creatorUid;

    public Product() {}

    public Product(Long productTemplateId, String name, Integer productType, Integer paybackType, Integer minAvailableRate, Integer maxAvailableRate, String availableTerms, Integer loanPolicy, String loanBenifitPerMonth, Long cityId, Boolean isDiabled, Long modifiedTime, Long createdTime, Long creatorUid) {
        this.productTemplateId = productTemplateId;
        this.name = name;
        this.productType = productType;
        this.paybackType = paybackType;
        this.minAvailableRate = minAvailableRate;
        this.maxAvailableRate = maxAvailableRate;
        this.availableTerms = availableTerms;
        this.loanPolicy = loanPolicy;
        this.loanBenifitPerMonth = loanBenifitPerMonth;
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

    public String getAvailableTerms() {
        return availableTerms;
    }

    public void setAvailableTerms(String availableTerms) {
        this.availableTerms = availableTerms;
    }

    public Integer getLoanPolicy() {
        return loanPolicy;
    }

    public void setLoanPolicy(Integer loanPolicy) {
        this.loanPolicy = loanPolicy;
    }

    public String getLoanBenifitPerMonth() {
        return loanBenifitPerMonth;
    }

    public void setLoanBenifitPerMonth(String loanBenifitPerMonth) {
        this.loanBenifitPerMonth = loanBenifitPerMonth;
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
