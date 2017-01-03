package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 16/12/30.
 * 贷款申请人
 */
@Entity
public class LoanApplicant {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 贷款申请人姓名
    private String applicantName;

    @NotNull
    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '申请人电话'")
    // 申请人电话
    private String applicantTel;

    @NotNull
    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '证件类型'")
    // 证件类型
    private Integer certificateType;

    @NotNull
    @Size(min = 6, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '证件号码'")
    // 证件号码
    private String certificateNumber;

    @NotNull
    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '婚姻状态'")
    // 婚姻状态
    private Integer marriage;

    @NotNull
    @Min(0)
    @Column(columnDefinition = "INT(10) COMMENT '月收入'")
    // 月收入
    private Long incomePerMonth;

    @NotNull
    @Size(max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '证件图片'")
    // 证件图片
    private String certificateFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '学历证明图片'")
    // 学历证明图片
    private String qualificationFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '收入证明图片'")
    // 收入证明图片
    private String incomeFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '房产图片'")
    // 房产图片
    private String estateFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '车辆图片'")
    // 车辆图片
    private String vehicleFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '其他图片'")
    // 其他图片
    private String otherFileIds;

    public LoanApplicant() {}

    public LoanApplicant(String applicantName, String applicantTel, Integer certificateType, String certificateNumber, Integer marriage, Long incomePerMonth, String certificateFileIds, String qualificationFileIds, String incomeFileIds, String estateFileIds, String vehicleFileIds, String otherFileIds) {
        this.applicantName = applicantName;
        this.applicantTel = applicantTel;
        this.certificateType = certificateType;
        this.certificateNumber = certificateNumber;
        this.marriage = marriage;
        this.incomePerMonth = incomePerMonth;
        this.certificateFileIds = certificateFileIds;
        this.qualificationFileIds = qualificationFileIds;
        this.incomeFileIds = incomeFileIds;
        this.estateFileIds = estateFileIds;
        this.vehicleFileIds = vehicleFileIds;
        this.otherFileIds = otherFileIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantTel() {
        return applicantTel;
    }

    public void setApplicantTel(String applicantTel) {
        this.applicantTel = applicantTel;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumer() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public Long getIncomePerMonth() {
        return incomePerMonth;
    }

    public void setIncomePerMonth(Long incomePerMonth) {
        this.incomePerMonth = incomePerMonth;
    }

    public String getCertificateFileIds() {
        return certificateFileIds;
    }

    public void setCertificateFileIds(String certificateFileIds) {
        this.certificateFileIds = certificateFileIds;
    }

    public String getQualificationFileIds() {
        return qualificationFileIds;
    }

    public void setQualificationFileIds(String qualificationFileIds) {
        this.qualificationFileIds = qualificationFileIds;
    }

    public String getIncomeFileIds() {
        return incomeFileIds;
    }

    public void setIncomeFileIds(String incomeFileIds) {
        this.incomeFileIds = incomeFileIds;
    }

    public String getEstateFileIds() {
        return estateFileIds;
    }

    public void setEstateFileIds(String estateFileIds) {
        this.estateFileIds = estateFileIds;
    }

    public String getVehicleFileIds() {
        return vehicleFileIds;
    }

    public void setVehicleFileIds(String vehicleFileIds) {
        this.vehicleFileIds = vehicleFileIds;
    }

    public String getOtherFileIds() {
        return otherFileIds;
    }

    public void setOtherFileIds(String otherFileIds) {
        this.otherFileIds = otherFileIds;
    }
}
