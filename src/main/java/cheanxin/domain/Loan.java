package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 16/12/30.
 */
@Entity
@Table(indexes = {@Index(name = "idx_product_id", columnList = "productId"), @Index(name = "idx_created_time", columnList = "createdTime")})
public class Loan {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Min(0)
    @Max(Integer.MAX_VALUE)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '车辆成交价格'")
    private Integer vehicleDealPrice;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '产品id'")
    private Long productId;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '贷款比率'")
    private Integer loanRate;

    @NotNull
    @Min(1)
    @Max(360)
    @Column(columnDefinition = "SMALLINT(3) UNSIGNED COMMENT '贷款期数'")
    private Integer loadTerms;

    @NotNull
    @Digits(integer = 2, fraction = 4)
    @Column(columnDefinition = "DECIMAL(6,4) UNSIGNED COMMENT '贷款月利率'")
    private Float loanMonthlyInterestRate;

    @NotNull
    @Digits(integer = 2, fraction = 4)
    @Column(columnDefinition = "DECIMAL(6,4) UNSIGNED COMMENT '提前还款违约金比例'")
    private Float prepaymentPenaltyRate;

    @Size(max = 1000)
    @Column(columnDefinition = "VARCHAR(1000) COMMENT '申请图片列表'")
    private String applicationPicUrl;

    @Size(max = 255)
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String remark;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '贷款来源id'")
    private Long extSourceId;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '贷款申请人id'")
    private Long extApplicantId;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '贷款共同申请人id'")
    private Long extCoApplicantId;


    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '贷款担保人id'")
    private Long extGuarantorId;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '贷款车辆id'")
    private Long extVehicleId;

    @NotNull
    @Min(0)
    @Max(32)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '贷款状态'")
    private Integer status;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '贷款创建时间'")
    private Long createdTime;

    public Loan() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVehicleDealPrice() {
        return vehicleDealPrice;
    }

    public void setVehicleDealPrice(Integer vehicleDealPrice) {
        this.vehicleDealPrice = vehicleDealPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(Integer loanRate) {
        this.loanRate = loanRate;
    }

    public Integer getLoadTerms() {
        return loadTerms;
    }

    public void setLoadTerms(Integer loadTerms) {
        this.loadTerms = loadTerms;
    }

    public Float getLoanMonthlyInterestRate() {
        return loanMonthlyInterestRate;
    }

    public void setLoanMonthlyInterestRate(Float loanMonthlyInterestRate) {
        this.loanMonthlyInterestRate = loanMonthlyInterestRate;
    }

    public Float getPrepaymentPenaltyRate() {
        return prepaymentPenaltyRate;
    }

    public void setPrepaymentPenaltyRate(Float prepaymentPenaltyRate) {
        this.prepaymentPenaltyRate = prepaymentPenaltyRate;
    }

    public String getApplicationPicUrl() {
        return applicationPicUrl;
    }

    public void setApplicationPicUrl(String applicationPicUrl) {
        this.applicationPicUrl = applicationPicUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getExtSourceId() {
        return extSourceId;
    }

    public void setExtSourceId(Long extSourceId) {
        this.extSourceId = extSourceId;
    }

    public Long getExtApplicantId() {
        return extApplicantId;
    }

    public void setExtApplicantId(Long extApplicantId) {
        this.extApplicantId = extApplicantId;
    }

    public Long getExtCoApplicantId() {
        return extCoApplicantId;
    }

    public void setExtCoApplicantId(Long extCoApplicantId) {
        this.extCoApplicantId = extCoApplicantId;
    }

    public Long getExtGuarantorId() {
        return extGuarantorId;
    }

    public void setExtGuarantorId(Long extGuarantorId) {
        this.extGuarantorId = extGuarantorId;
    }

    public Long getExtVehicleId() {
        return extVehicleId;
    }

    public void setExtVehicleId(Long extVehicleId) {
        this.extVehicleId = extVehicleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
