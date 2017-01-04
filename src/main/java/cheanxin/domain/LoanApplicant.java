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
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '贷款申请人姓名'")
    // 贷款申请人姓名
    private String name;

    @NotNull
    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '婚姻状态'")
    // 婚姻状态
    private Integer marriage;

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
    @Max(4)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '性别'")
    private Integer gender;

    @NotNull
    @Size(min = 6, max = 6)
    @Column(columnDefinition = "CHAR(6) COMMENT '出生年月'")
    private String birthYearMonth;

    @NotNull
    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '学历'")
    private String qualification;

    @NotNull
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) COMMENT '申请人手机号'")
    @Pattern(regexp = "^1[34578][0-9]{9}$")
    private String mobileNumber;

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
    @Column(columnDefinition = "VARCHAR(20) COMMENT '申请人电话'")
    // 申请人电话
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

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '工作单位性质(国企、私企、外企等等)'")
    private String employerType;

    @Size(max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '工作单位所属行业'")
    private String employerIndustry;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '申请人工作岗位'")
    private String post;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '申请人职业'")
    private String occupation;

    @Max(99)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '申请人工作年限'")
    private Integer workYears;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '申请人职务'")
    private String position;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '申请人职称'")
    private String jobTitle;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '邮寄地址'")
    private String postAddress;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '紧急联系人1'")
    // 紧急联系人
    private String firstEmergencyContact;

    @NotNull
    @Size(max = 10)
    @Column(columnDefinition = "VARCHAR(10) COMMENT '紧急联系人1与申请人的关系'")
    private String firstEmergencyContactRelationship;

    @NotNull
    @Pattern(regexp = "^1[34578][0-9]{9}$")
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) COMMENT '紧急联系人1手机号码'")
    // 紧急联系人手机号码
    private String firstEmergencyContactMobileNumber;

    @NotNull
    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '紧急联系人1地址'")
    private String firstEmergencyContactAddress;

    @Size(min = 2, max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '紧急联系人2'")
    // 紧急联系人
    private String secondEmergencyContact;

    @Size(max = 10)
    @Column(columnDefinition = "VARCHAR(10) COMMENT '紧急联系人1与申请人的关系'")
    private String secondEmergencyContactRelationship;

    @Pattern(regexp = "^1[34578][0-9]{9}$")
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) COMMENT '紧急联系人2手机号码'")
    // 紧急联系人手机号码
    private String secondEmergencyContactMobileNumber;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '紧急联系人2地址'")
    private String secondEmergencyContactAddress;

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

    public LoanApplicant(String name, Integer marriage, Integer certificateType, String certificateNumber, Integer gender, String birthYearMonth, String qualification, String mobileNumber, String census, String address, Long incomePerMonth, String telephone, String employerName, String employerTelephone, String employerAddress, String employerType, String employerIndustry, String post, String occupation, Integer workYears, String position, String jobTitle, String postAddress, String firstEmergencyContact, String firstEmergencyContactRelationship, String firstEmergencyContactMobileNumber, String firstEmergencyContactAddress, String secondEmergencyContact, String secondEmergencyContactRelationship, String secondEmergencyContactMobileNumber, String secondEmergencyContactAddress, String certificateFileIds, String qualificationFileIds, String incomeFileIds, String estateFileIds, String vehicleFileIds, String otherFileIds) {
        this.name = name;
        this.marriage = marriage;
        this.certificateType = certificateType;
        this.certificateNumber = certificateNumber;
        this.gender = gender;
        this.birthYearMonth = birthYearMonth;
        this.qualification = qualification;
        this.mobileNumber = mobileNumber;
        this.census = census;
        this.address = address;
        this.incomePerMonth = incomePerMonth;
        this.telephone = telephone;
        this.employerName = employerName;
        this.employerTelephone = employerTelephone;
        this.employerAddress = employerAddress;
        this.employerType = employerType;
        this.employerIndustry = employerIndustry;
        this.post = post;
        this.occupation = occupation;
        this.workYears = workYears;
        this.position = position;
        this.jobTitle = jobTitle;
        this.postAddress = postAddress;
        this.firstEmergencyContact = firstEmergencyContact;
        this.firstEmergencyContactRelationship = firstEmergencyContactRelationship;
        this.firstEmergencyContactMobileNumber = firstEmergencyContactMobileNumber;
        this.firstEmergencyContactAddress = firstEmergencyContactAddress;
        this.secondEmergencyContact = secondEmergencyContact;
        this.secondEmergencyContactRelationship = secondEmergencyContactRelationship;
        this.secondEmergencyContactMobileNumber = secondEmergencyContactMobileNumber;
        this.secondEmergencyContactAddress = secondEmergencyContactAddress;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthYearMonth() {
        return birthYearMonth;
    }

    public void setBirthYearMonth(String birthYearMonth) {
        this.birthYearMonth = birthYearMonth;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public String getEmployerType() {
        return employerType;
    }

    public void setEmployerType(String employerType) {
        this.employerType = employerType;
    }

    public String getEmployerIndustry() {
        return employerIndustry;
    }

    public void setEmployerIndustry(String employerIndustry) {
        this.employerIndustry = employerIndustry;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getWorkYears() {
        return workYears;
    }

    public void setWorkYears(Integer workYears) {
        this.workYears = workYears;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getFirstEmergencyContact() {
        return firstEmergencyContact;
    }

    public void setFirstEmergencyContact(String firstEmergencyContact) {
        this.firstEmergencyContact = firstEmergencyContact;
    }

    public String getFirstEmergencyContactRelationship() {
        return firstEmergencyContactRelationship;
    }

    public void setFirstEmergencyContactRelationship(String firstEmergencyContactRelationship) {
        this.firstEmergencyContactRelationship = firstEmergencyContactRelationship;
    }

    public String getFirstEmergencyContactMobileNumber() {
        return firstEmergencyContactMobileNumber;
    }

    public void setFirstEmergencyContactMobileNumber(String firstEmergencyContactMobileNumber) {
        this.firstEmergencyContactMobileNumber = firstEmergencyContactMobileNumber;
    }

    public String getFirstEmergencyContactAddress() {
        return firstEmergencyContactAddress;
    }

    public void setFirstEmergencyContactAddress(String firstEmergencyContactAddress) {
        this.firstEmergencyContactAddress = firstEmergencyContactAddress;
    }

    public String getSecondEmergencyContact() {
        return secondEmergencyContact;
    }

    public void setSecondEmergencyContact(String secondEmergencyContact) {
        this.secondEmergencyContact = secondEmergencyContact;
    }

    public String getSecondEmergencyContactRelationship() {
        return secondEmergencyContactRelationship;
    }

    public void setSecondEmergencyContactRelationship(String secondEmergencyContactRelationship) {
        this.secondEmergencyContactRelationship = secondEmergencyContactRelationship;
    }

    public String getSecondEmergencyContactMobileNumber() {
        return secondEmergencyContactMobileNumber;
    }

    public void setSecondEmergencyContactMobileNumber(String secondEmergencyContactMobileNumber) {
        this.secondEmergencyContactMobileNumber = secondEmergencyContactMobileNumber;
    }

    public String getSecondEmergencyContactAddress() {
        return secondEmergencyContactAddress;
    }

    public void setSecondEmergencyContactAddress(String secondEmergencyContactAddress) {
        this.secondEmergencyContactAddress = secondEmergencyContactAddress;
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
