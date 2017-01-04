package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 16/12/30.
 * 共同申请人
 */
@Entity
public class LoanCoApplicant {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '共同申请人姓名'")
    private String name;

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
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) COMMENT '共同申请人手机号'")
    @Pattern(regexp = "^1[34578][0-9]{9}$")
    private String mobileNumber;

    @NotNull
    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '学历'")
    private String qualification;

    @NotNull
    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '户籍'")
    private String census;

    @NotNull
    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '现居住地'")
    private String address;

    @NotNull
    @Min(0)
    @Column(columnDefinition = "INT(10) COMMENT '月收入'")
    // 月收入
    private Long incomePerMonth;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '共同申请人电话'")
    private String telephone;

    @Size(max = 200)
    @Column(columnDefinition = "INT(10) COMMENT '工作单位名称'")
    private String employerName;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '单位电话'")
    private String employerTelephone;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '单位居住地'")
    private String employerAddress;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '证件图片'")
    // 证件图片
    private String certificateFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '收入证明图片'")
    // 收入证明图片
    private String incomeFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '房产图片'")
    // 房产图片
    private String estateFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '其他图片'")
    // 其他图片
    private String otherFileIds;

    public LoanCoApplicant() {}

    public LoanCoApplicant(String name, Integer certificateType, String certificateNumber, String mobileNumber, String qualification, String census, String address, Long incomePerMonth, String telephone, String employerName, String employerTelephone, String employerAddress, String certificateFileIds, String incomeFileIds, String estateFileIds, String otherFileIds) {
        this.name = name;
        this.certificateType = certificateType;
        this.certificateNumber = certificateNumber;
        this.mobileNumber = mobileNumber;
        this.qualification = qualification;
        this.census = census;
        this.address = address;
        this.incomePerMonth = incomePerMonth;
        this.telephone = telephone;
        this.employerName = employerName;
        this.employerTelephone = employerTelephone;
        this.employerAddress = employerAddress;
        this.certificateFileIds = certificateFileIds;
        this.incomeFileIds = incomeFileIds;
        this.estateFileIds = estateFileIds;
        this.otherFileIds = otherFileIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getCensus() {
        return census;
    }

    public void setCensus(String census) {
        this.census = census;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getIncomePerMonth() {
        return incomePerMonth;
    }

    public void setIncomePerMonth(Long incomePerMonth) {
        this.incomePerMonth = incomePerMonth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerTelephone() {
        return employerTelephone;
    }

    public void setEmployerTelephone(String employerTelephone) {
        this.employerTelephone = employerTelephone;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }

    public void setEmployerAddress(String employerAddress) {
        this.employerAddress = employerAddress;
    }

    public String getCertificateFileIds() {
        return certificateFileIds;
    }

    public void setCertificateFileIds(String certificateFileIds) {
        this.certificateFileIds = certificateFileIds;
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

    public String getOtherFileIds() {
        return otherFileIds;
    }

    public void setOtherFileIds(String otherFileIds) {
        this.otherFileIds = otherFileIds;
    }
}
