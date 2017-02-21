package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 16/12/30.
 */
@Entity
@Table(indexes = {@Index(name = "idx_applicantName", columnList = "applicantName"),
        @Index(name = "idx_applicant_mobile_number", columnList = "applicantMobileNumber"),
        @Index(name = "idx_source_financial_commissioner", columnList = "sourceFinancialCommissioner"),
        @Index(name = "idx_created_time", columnList = "createdTime")})
public class Loan {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '贷款草稿id'")
    private Long loanDraftId;

    @NotNull
    @Min(0)
    @Max(Integer.MAX_VALUE)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '车辆成交价格'")
    private Integer vehicleDealPrice;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '产品id'")
    private Long productId;

    @Transient
    private String productName;

    @Transient
    private String productType;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '贷款比率'")
    private Integer loanRate;

    @NotNull
    @Min(1)
    @Max(360)
    @Column(columnDefinition = "SMALLINT(3) UNSIGNED COMMENT '贷款期数'")
    private Integer loanTerms;

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
    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '贷款申请人姓名'")
    private String applicantName;

    @NotNull
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) COMMENT '申请人手机号'")
    @Pattern(regexp = "^1[34578][0-9]{9}$")
    private String applicantMobileNumber;

    @NotNull
    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '跟单金融专员'")
    private String sourceFinancialCommissioner;

    @NotNull
    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '收单员'")
    private String sourceReceiver;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '城市id'")
    private Long sourceCityId;

    @NotNull
    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '来源渠道'")
    private Integer sourceChannel;

    @NotNull
    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '来源联系人姓名'")
    private String sourcePersonName;

    @NotNull
    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '来源联系人电话'")
    private String sourcePersonTel;

    @NotNull
    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '婚姻状态'")
    private Integer applicantMarriage;

    @NotNull
    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '证件类型'")
    private Integer applicantCertificateType;

    @NotNull
    @Size(min = 6, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '证件号码'")
    private String applicantCertificateNumber;

    @Min(0)
    @Max(4)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '性别'")
    private Integer applicantGender;

    @Size(min = 6, max = 6)
    @Column(columnDefinition = "CHAR(6) COMMENT '出生年月'")
    private String applicantBirthYearMonth;

    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '学历'")
    private String applicantQualification;

    @Column(columnDefinition = "INT(10) COMMENT '户籍所在城市'")
    private Long applicantCensusCityId;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '现居住地'")
    private String applicantAddress;

    @NotNull
    @Min(0)
    @Column(columnDefinition = "INT(10) COMMENT '月收入'")
    private Long applicantIncomePerMonth;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '申请人电话'")
    private String applicantTelephone;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '工作单位名称'")
    private String applicantEmployerName;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '单位电话'")
    private String applicantEmployerTelephone;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '单位居住地'")
    private String applicantEmployerAddress;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '工作单位性质(国企、私企、外企等等)'")
    private String applicantEmployerType;

    @Size(max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '工作单位所属行业'")
    private String applicantEmployerIndustry;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '申请人工作岗位'")
    private String applicantPost;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '申请人职业'")
    private String applicantOccupation;

    @Max(99)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '申请人工作年限'")
    private Integer applicantWorkYears;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '申请人职务'")
    private String applicantPosition;

    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '申请人职称'")
    private String applicantJobTitle;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '邮寄地址'")
    private String applicantPostAddress;

    @Size(min = 6, max = 6)
    @Column(columnDefinition = "CHAR(6) COMMENT '邮编'")
    private String applicantPostCode;

    @Size(min = 2, max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '紧急联系人1'")
    private String applicantFirstEmergencyContact;

    @Size(max = 10)
    @Column(columnDefinition = "VARCHAR(10) COMMENT '紧急联系人1与申请人的关系'")
    private String applicantFirstEmergencyContactRelationship;

    @Pattern(regexp = "^1[34578][0-9]{9}$")
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) COMMENT '紧急联系人1手机号码'")
    private String applicantFirstEmergencyContactMobileNumber;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '紧急联系人1地址'")
    private String applicantFirstEmergencyContactAddress;

    @Size(min = 2, max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '紧急联系人2'")
    private String applicantSecondEmergencyContact;

    @Size(max = 10)
    @Column(columnDefinition = "VARCHAR(10) COMMENT '紧急联系人1与申请人的关系'")
    private String applicantSecondEmergencyContactRelationship;

    @Pattern(regexp = "^1[34578][0-9]{9}$")
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) COMMENT '紧急联系人2手机号码'")
    private String applicantSecondEmergencyContactMobileNumber;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '紧急联系人2地址'")
    private String applicantSecondEmergencyContactAddress;

    @NotNull
    @Size(max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '证件图片'")
    private String applicantCertificateFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '学历证明图片'")
    private String applicantQualificationFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '收入证明图片'")
    private String applicantIncomeFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '房产图片'")
    private String applicantEstateFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '车辆图片'")
    private String applicantVehicleFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '其他图片'")
    private String applicantOtherFileIds;


    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '共同申请人姓名'")
    private String coApplicantName;

    @Size(min = 6, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '证件号码'")
    private String coApplicantCertificateNumber;

    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) COMMENT '共同申请人手机号'")
    @Pattern(regexp = "^1[34578][0-9]{9}$")
    private String coApplicantMobileNumber;

    @Size(max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '学历'")
    private String coApplicantQualification;

    @Column(columnDefinition = "INT(10) COMMENT '户籍所在城市'")
    private Long coApplicantCensusCityId;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '现居住地'")
    private String coApplicantAddress;

    @Min(0)
    @Column(columnDefinition = "INT(10) COMMENT '月收入'")
    private Long coApplicantIncomePerMonth;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '共同申请人电话'")
    private String coApplicantTelephone;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '工作单位名称'")
    private String coApplicantEmployerName;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '单位电话'")
    private String coApplicantEmployerTelephone;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '单位居住地'")
    private String coApplicantEmployerAddress;

    @Size(min = 5, max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '证件图片'")
    private String coApplicantCertificateFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '收入证明图片'")
    private String coApplicantIncomeFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '房产图片'")
    private String coApplicantEstateFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '其他图片'")
    private String coApplicantOtherFileIds;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '担保人姓名'")
    private String guarantorName;

    @Size(max = 10)
    @Column(columnDefinition = "VARCHAR(10) COMMENT '担保人与申请人的关系'")
    private String guarantorRelationship;

    @Size(min = 6, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '担保人证件号码'")
    private String guarantorCertificateNumber;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '担保人房产权属'")
    private String guarantorRealEstateOwnType;

    @Min(0)
    @Column(columnDefinition = "INT(10) COMMENT '担保人月收入'")
    private Long guarantorIncomePerMonth;

    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) COMMENT '担保人手机号'")
    @Pattern(regexp = "^1[34578][0-9]{9}$")
    private String guarantorMobileNumber;

    @Size(min = 5, max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '证件图片'")
    private String guarantorCertificateFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '收入证明图片'")
    private String guarantorIncomeFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '房产图片'")
    private String guarantorEstateFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '其他图片'")
    private String guarantorOtherFileIds;

    @NotNull
    @Size(min = 17, max = 17)
    @Column(columnDefinition = "CHAR(17) COMMENT '车辆vin码'")
    private String vehicleVin;

    @NotNull
    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '厂家'")
    private String vehicleManufacturers;

    @NotNull
    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '品牌'")
    private String vehicleBrand;

    @NotNull
    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '车系'")
    private String vehicleSeries;

    @Size(min = 4, max = 10)
    @Column(columnDefinition = "VARCHAR(10) COMMENT '生产年月'")
    private String vehicleProductionYearMonth;

    @Size(min = 4, max = 10)
    @Column(columnDefinition = "VARCHAR(10) COMMENT '首次登记年月'")
    private String vehicleRegistrationYearMonth;

    @NotNull
    @Digits(integer = 3, fraction = 4)
    @Column(columnDefinition = "DECIMAL(7,4) UNSIGNED COMMENT '公里数'")
    private Float vehicleKilometers;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '使用性质'")
    private String vehicleUtilityType;

    @Size(max = 10)
    @Column(columnDefinition = "VARCHAR(10) COMMENT '排放标准'")
    private String vehicleEmission;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '车辆登记证书图片'")
    private String vehicleRegistrationCertificateFileIds;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '机动车行驶证图片'")
    private String vehicleLicenseFileIds;

    @NotNull
    @Size(min = 5, max = 2000)
    @Column(columnDefinition = "VARCHAR(2000) COMMENT '车辆图片'")
    private String vehicleFileIds;
    
    @NotNull
    @Min(0)
    @Max(512)
    @Column(columnDefinition = "TINYINT(3) UNSIGNED COMMENT '贷款状态'")
    private Integer status;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '贷款创建时间'")
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    private Long modifiedTime;

    @Size(min = 3, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '创建贷款草稿用户'")
    private String creatorUsername;

    public Loan() {}

    public Loan(LoanDraft loanDraft) {
        this.loanDraftId = loanDraft.getId();
        this.vehicleDealPrice = loanDraft.getVehicleDealPrice();
        this.productId = loanDraft.getProductId();
        this.productName = loanDraft.getProductName();
        this.productType = loanDraft.getProductType();
        this.loanRate = loanDraft.getLoanRate();
        this.loanTerms = loanDraft.getLoanTerms();
        this.loanMonthlyInterestRate = loanDraft.getLoanMonthlyInterestRate();
        this.prepaymentPenaltyRate = loanDraft.getPrepaymentPenaltyRate();
        this.applicationPicUrl = loanDraft.getApplicationPicUrl();
        this.remark = loanDraft.getRemark();
        this.applicantName = loanDraft.getApplicantName();
        this.applicantMobileNumber = loanDraft.getApplicantMobileNumber();
        this.sourceFinancialCommissioner = loanDraft.getSourceFinancialCommissioner();
        this.sourceReceiver = loanDraft.getSourceReceiver();
        this.sourceCityId = loanDraft.getSourceCityId();
        this.sourceChannel = loanDraft.getSourceChannel();
        this.sourcePersonName = loanDraft.getSourcePersonName();
        this.sourcePersonTel = loanDraft.getSourcePersonTel();
        this.applicantMarriage = loanDraft.getApplicantMarriage();
        this.applicantCertificateType = loanDraft.getApplicantCertificateType();
        this.applicantCertificateNumber = loanDraft.getApplicantCertificateNumber();
        this.applicantGender = loanDraft.getApplicantGender();
        this.applicantBirthYearMonth = loanDraft.getApplicantBirthYearMonth();
        this.applicantQualification = loanDraft.getApplicantQualification();
        this.applicantCensusCityId = loanDraft.getApplicantCensusCityId();
        this.applicantAddress = loanDraft.getApplicantAddress();
        this.applicantIncomePerMonth = loanDraft.getApplicantIncomePerMonth();
        this.applicantTelephone = loanDraft.getApplicantTelephone();
        this.applicantEmployerName = loanDraft.getApplicantEmployerName();
        this.applicantEmployerTelephone = loanDraft.getApplicantEmployerTelephone();
        this.applicantEmployerAddress = loanDraft.getApplicantEmployerAddress();
        this.applicantEmployerType = loanDraft.getApplicantEmployerType();
        this.applicantEmployerIndustry = loanDraft.getApplicantEmployerIndustry();
        this.applicantPost = loanDraft.getApplicantPost();
        this.applicantOccupation = loanDraft.getApplicantOccupation();
        this.applicantWorkYears = loanDraft.getApplicantWorkYears();
        this.applicantPosition = loanDraft.getApplicantPosition();
        this.applicantJobTitle = loanDraft.getApplicantJobTitle();
        this.applicantPostAddress = loanDraft.getApplicantPostAddress();
        this.applicantPostCode = loanDraft.getApplicantPostCode();
        this.applicantFirstEmergencyContact = loanDraft.getApplicantFirstEmergencyContact();
        this.applicantFirstEmergencyContactRelationship = loanDraft.getApplicantFirstEmergencyContactRelationship();
        this.applicantFirstEmergencyContactMobileNumber = loanDraft.getApplicantFirstEmergencyContactMobileNumber();
        this.applicantFirstEmergencyContactAddress = loanDraft.getApplicantFirstEmergencyContactAddress();
        this.applicantSecondEmergencyContact = loanDraft.getApplicantSecondEmergencyContact();
        this.applicantSecondEmergencyContactRelationship = loanDraft.getApplicantSecondEmergencyContactRelationship();
        this.applicantSecondEmergencyContactMobileNumber = loanDraft.getApplicantSecondEmergencyContactMobileNumber();
        this.applicantSecondEmergencyContactAddress = loanDraft.getApplicantSecondEmergencyContactAddress();
        this.applicantCertificateFileIds = loanDraft.getApplicantCertificateFileIds();
        this.applicantQualificationFileIds = loanDraft.getApplicantQualificationFileIds();
        this.applicantIncomeFileIds = loanDraft.getApplicantIncomeFileIds();
        this.applicantEstateFileIds = loanDraft.getApplicantEstateFileIds();
        this.applicantVehicleFileIds = loanDraft.getApplicantVehicleFileIds();
        this.applicantOtherFileIds = loanDraft.getApplicantOtherFileIds();
        this.coApplicantName = loanDraft.getCoApplicantName();
        this.coApplicantCertificateNumber = loanDraft.getCoApplicantCertificateNumber();
        this.coApplicantMobileNumber = loanDraft.getCoApplicantMobileNumber();
        this.coApplicantQualification = loanDraft.getCoApplicantQualification();
        this.coApplicantCensusCityId = loanDraft.getCoApplicantCensusCityId();
        this.coApplicantAddress = loanDraft.getCoApplicantAddress();
        this.coApplicantIncomePerMonth = loanDraft.getCoApplicantIncomePerMonth();
        this.coApplicantTelephone = loanDraft.getCoApplicantTelephone();
        this.coApplicantEmployerName = loanDraft.getCoApplicantEmployerName();
        this.coApplicantEmployerTelephone = loanDraft.getCoApplicantEmployerTelephone();
        this.coApplicantEmployerAddress = loanDraft.getCoApplicantEmployerAddress();
        this.coApplicantCertificateFileIds = loanDraft.getCoApplicantCertificateFileIds();
        this.coApplicantIncomeFileIds = loanDraft.getCoApplicantIncomeFileIds();
        this.coApplicantEstateFileIds = loanDraft.getCoApplicantEstateFileIds();
        this.coApplicantOtherFileIds = loanDraft.getCoApplicantOtherFileIds();
        this.guarantorName = loanDraft.getGuarantorName();
        this.guarantorRelationship = loanDraft.getGuarantorRelationship();
        this.guarantorCertificateNumber = loanDraft.getGuarantorCertificateNumber();
        this.guarantorRealEstateOwnType = loanDraft.getGuarantorRealEstateOwnType();
        this.guarantorIncomePerMonth = loanDraft.getGuarantorIncomePerMonth();
        this.guarantorMobileNumber = loanDraft.getGuarantorMobileNumber();
        this.guarantorCertificateFileIds = loanDraft.getGuarantorCertificateFileIds();
        this.guarantorIncomeFileIds = loanDraft.getGuarantorIncomeFileIds();
        this.guarantorEstateFileIds = loanDraft.getGuarantorEstateFileIds();
        this.guarantorOtherFileIds = loanDraft.getGuarantorOtherFileIds();
        this.vehicleVin = loanDraft.getVehicleVin();
        this.vehicleManufacturers = loanDraft.getVehicleManufacturers();
        this.vehicleBrand = loanDraft.getVehicleBrand();
        this.vehicleSeries = loanDraft.getVehicleSeries();
        this.vehicleProductionYearMonth = loanDraft.getVehicleProductionYearMonth();
        this.vehicleRegistrationYearMonth = loanDraft.getVehicleRegistrationYearMonth();
        this.vehicleKilometers = loanDraft.getVehicleKilometers();
        this.vehicleUtilityType = loanDraft.getVehicleUtilityType();
        this.vehicleEmission = loanDraft.getVehicleEmission();
        this.vehicleRegistrationCertificateFileIds = loanDraft.getVehicleRegistrationCertificateFileIds();
        this.vehicleLicenseFileIds = loanDraft.getVehicleLicenseFileIds();
        this.vehicleFileIds = loanDraft.getVehicleFileIds();
        this.status = loanDraft.getStatus();
        this.createdTime = loanDraft.getCreatedTime();
        this.modifiedTime = loanDraft.getModifiedTime();
        this.creatorUsername = loanDraft.getCreatorUsername();
    }

    public Loan(Loan that) {
        this.loanDraftId = that.getLoanDraftId();
        this.vehicleDealPrice = that.getVehicleDealPrice();
        this.productId = that.getProductId();
        this.productName = that.getProductName();
        this.productType = that.getProductType();
        this.loanRate = that.getLoanRate();
        this.loanTerms = that.getLoanTerms();
        this.loanMonthlyInterestRate = that.getLoanMonthlyInterestRate();
        this.prepaymentPenaltyRate = that.getPrepaymentPenaltyRate();
        this.applicationPicUrl = that.getApplicationPicUrl();
        this.remark = that.getRemark();
        this.applicantName = that.getApplicantName();
        this.applicantMobileNumber = that.getApplicantMobileNumber();
        this.sourceFinancialCommissioner = that.getSourceFinancialCommissioner();
        this.sourceReceiver = that.getSourceReceiver();
        this.sourceCityId = that.getSourceCityId();
        this.sourceChannel = that.getSourceChannel();
        this.sourcePersonName = that.getSourcePersonName();
        this.sourcePersonTel = that.getSourcePersonTel();
        this.applicantMarriage = that.getApplicantMarriage();
        this.applicantCertificateType = that.getApplicantCertificateType();
        this.applicantCertificateNumber = that.getApplicantCertificateNumber();
        this.applicantGender = that.getApplicantGender();
        this.applicantBirthYearMonth = that.getApplicantBirthYearMonth();
        this.applicantQualification = that.getApplicantQualification();
        this.applicantCensusCityId = that.getApplicantCensusCityId();
        this.applicantAddress = that.getApplicantAddress();
        this.applicantIncomePerMonth = that.getApplicantIncomePerMonth();
        this.applicantTelephone = that.getApplicantTelephone();
        this.applicantEmployerName = that.getApplicantEmployerName();
        this.applicantEmployerTelephone = that.getApplicantEmployerTelephone();
        this.applicantEmployerAddress = that.getApplicantEmployerAddress();
        this.applicantEmployerType = that.getApplicantEmployerType();
        this.applicantEmployerIndustry = that.getApplicantEmployerIndustry();
        this.applicantPost = that.getApplicantPost();
        this.applicantOccupation = that.getApplicantOccupation();
        this.applicantWorkYears = that.getApplicantWorkYears();
        this.applicantPosition = that.getApplicantPosition();
        this.applicantJobTitle = that.getApplicantJobTitle();
        this.applicantPostAddress = that.getApplicantPostAddress();
        this.applicantPostCode = that.getApplicantPostCode();
        this.applicantFirstEmergencyContact = that.getApplicantFirstEmergencyContact();
        this.applicantFirstEmergencyContactRelationship = that.getApplicantFirstEmergencyContactRelationship();
        this.applicantFirstEmergencyContactMobileNumber = that.getApplicantFirstEmergencyContactMobileNumber();
        this.applicantFirstEmergencyContactAddress = that.getApplicantFirstEmergencyContactAddress();
        this.applicantSecondEmergencyContact = that.getApplicantSecondEmergencyContact();
        this.applicantSecondEmergencyContactRelationship = that.getApplicantSecondEmergencyContactRelationship();
        this.applicantSecondEmergencyContactMobileNumber = that.getApplicantSecondEmergencyContactMobileNumber();
        this.applicantSecondEmergencyContactAddress = that.getApplicantSecondEmergencyContactAddress();
        this.applicantCertificateFileIds = that.getApplicantCertificateFileIds();
        this.applicantQualificationFileIds = that.getApplicantQualificationFileIds();
        this.applicantIncomeFileIds = that.getApplicantIncomeFileIds();
        this.applicantEstateFileIds = that.getApplicantEstateFileIds();
        this.applicantVehicleFileIds = that.getApplicantVehicleFileIds();
        this.applicantOtherFileIds = that.getApplicantOtherFileIds();
        this.coApplicantName = that.getCoApplicantName();
        this.coApplicantCertificateNumber = that.getCoApplicantCertificateNumber();
        this.coApplicantMobileNumber = that.getCoApplicantMobileNumber();
        this.coApplicantQualification = that.getCoApplicantQualification();
        this.coApplicantCensusCityId = that.getCoApplicantCensusCityId();
        this.coApplicantAddress = that.getCoApplicantAddress();
        this.coApplicantIncomePerMonth = that.getCoApplicantIncomePerMonth();
        this.coApplicantTelephone = that.getCoApplicantTelephone();
        this.coApplicantEmployerName = that.getCoApplicantEmployerName();
        this.coApplicantEmployerTelephone = that.getCoApplicantEmployerTelephone();
        this.coApplicantEmployerAddress = that.getCoApplicantEmployerAddress();
        this.coApplicantCertificateFileIds = that.getCoApplicantCertificateFileIds();
        this.coApplicantIncomeFileIds = that.getCoApplicantIncomeFileIds();
        this.coApplicantEstateFileIds = that.getCoApplicantEstateFileIds();
        this.coApplicantOtherFileIds = that.getCoApplicantOtherFileIds();
        this.guarantorName = that.getGuarantorName();
        this.guarantorRelationship = that.getGuarantorRelationship();
        this.guarantorCertificateNumber = that.getGuarantorCertificateNumber();
        this.guarantorRealEstateOwnType = that.getGuarantorRealEstateOwnType();
        this.guarantorIncomePerMonth = that.getGuarantorIncomePerMonth();
        this.guarantorMobileNumber = that.getGuarantorMobileNumber();
        this.guarantorCertificateFileIds = that.getGuarantorCertificateFileIds();
        this.guarantorIncomeFileIds = that.getGuarantorIncomeFileIds();
        this.guarantorEstateFileIds = that.getGuarantorEstateFileIds();
        this.guarantorOtherFileIds = that.getGuarantorOtherFileIds();
        this.vehicleVin = that.getVehicleVin();
        this.vehicleManufacturers = that.getVehicleManufacturers();
        this.vehicleBrand = that.getVehicleBrand();
        this.vehicleSeries = that.getVehicleSeries();
        this.vehicleProductionYearMonth = that.getVehicleProductionYearMonth();
        this.vehicleRegistrationYearMonth = that.getVehicleRegistrationYearMonth();
        this.vehicleKilometers = that.getVehicleKilometers();
        this.vehicleUtilityType = that.getVehicleUtilityType();
        this.vehicleEmission = that.getVehicleEmission();
        this.vehicleRegistrationCertificateFileIds = that.getVehicleRegistrationCertificateFileIds();
        this.vehicleLicenseFileIds = that.getVehicleLicenseFileIds();
        this.vehicleFileIds = that.getVehicleFileIds();
        this.status = that.getStatus();
        this.createdTime = that.getCreatedTime();
        this.modifiedTime = that.getModifiedTime();
        this.creatorUsername = that.getCreatorUsername();
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", productId=" + productId +
                ", applicantName='" + applicantName + '\'' +
                ", loanRate=" + loanRate +
                ", loanTerms=" + loanTerms +
                ", loanMonthlyInterestRate=" + loanMonthlyInterestRate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanDraftId() {
        return loanDraftId;
    }

    public void setLoanDraftId(Long loanDraftId) {
        this.loanDraftId = loanDraftId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(Integer loanRate) {
        this.loanRate = loanRate;
    }

    public Integer getLoanTerms() {
        return loanTerms;
    }

    public void setLoanTerms(Integer loanTerms) {
        this.loanTerms = loanTerms;
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

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantMobileNumber() {
        return applicantMobileNumber;
    }

    public void setApplicantMobileNumber(String applicantMobileNumber) {
        this.applicantMobileNumber = applicantMobileNumber;
    }

    public String getSourceFinancialCommissioner() {
        return sourceFinancialCommissioner;
    }

    public void setSourceFinancialCommissioner(String sourceFinancialCommissioner) {
        this.sourceFinancialCommissioner = sourceFinancialCommissioner;
    }

    public String getSourceReceiver() {
        return sourceReceiver;
    }

    public void setSourceReceiver(String sourceReceiver) {
        this.sourceReceiver = sourceReceiver;
    }

    public Long getSourceCityId() {
        return sourceCityId;
    }

    public void setSourceCityId(Long sourceCityId) {
        this.sourceCityId = sourceCityId;
    }

    public Integer getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(Integer sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getSourcePersonName() {
        return sourcePersonName;
    }

    public void setSourcePersonName(String sourcePersonName) {
        this.sourcePersonName = sourcePersonName;
    }

    public String getSourcePersonTel() {
        return sourcePersonTel;
    }

    public void setSourcePersonTel(String sourcePersonTel) {
        this.sourcePersonTel = sourcePersonTel;
    }

    public Integer getApplicantMarriage() {
        return applicantMarriage;
    }

    public void setApplicantMarriage(Integer applicantMarriage) {
        this.applicantMarriage = applicantMarriage;
    }

    public Integer getApplicantCertificateType() {
        return applicantCertificateType;
    }

    public void setApplicantCertificateType(Integer applicantCertificateType) {
        this.applicantCertificateType = applicantCertificateType;
    }

    public String getApplicantCertificateNumber() {
        return applicantCertificateNumber;
    }

    public void setApplicantCertificateNumber(String applicantCertificateNumber) {
        this.applicantCertificateNumber = applicantCertificateNumber;
    }

    public Integer getApplicantGender() {
        return applicantGender;
    }

    public void setApplicantGender(Integer applicantGender) {
        this.applicantGender = applicantGender;
    }

    public String getApplicantBirthYearMonth() {
        return applicantBirthYearMonth;
    }

    public void setApplicantBirthYearMonth(String applicantBirthYearMonth) {
        this.applicantBirthYearMonth = applicantBirthYearMonth;
    }

    public String getApplicantQualification() {
        return applicantQualification;
    }

    public void setApplicantQualification(String applicantQualification) {
        this.applicantQualification = applicantQualification;
    }

    public Long getApplicantCensusCityId() {
        return applicantCensusCityId;
    }

    public void setApplicantCensusCityId(Long applicantCensusCityId) {
        this.applicantCensusCityId = applicantCensusCityId;
    }

    public String getApplicantAddress() {
        return applicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    public Long getApplicantIncomePerMonth() {
        return applicantIncomePerMonth;
    }

    public void setApplicantIncomePerMonth(Long applicantIncomePerMonth) {
        this.applicantIncomePerMonth = applicantIncomePerMonth;
    }

    public String getApplicantTelephone() {
        return applicantTelephone;
    }

    public void setApplicantTelephone(String applicantTelephone) {
        this.applicantTelephone = applicantTelephone;
    }

    public String getApplicantEmployerName() {
        return applicantEmployerName;
    }

    public void setApplicantEmployerName(String applicantEmployerName) {
        this.applicantEmployerName = applicantEmployerName;
    }

    public String getApplicantEmployerTelephone() {
        return applicantEmployerTelephone;
    }

    public void setApplicantEmployerTelephone(String applicantEmployerTelephone) {
        this.applicantEmployerTelephone = applicantEmployerTelephone;
    }

    public String getApplicantEmployerAddress() {
        return applicantEmployerAddress;
    }

    public void setApplicantEmployerAddress(String applicantEmployerAddress) {
        this.applicantEmployerAddress = applicantEmployerAddress;
    }

    public String getApplicantEmployerType() {
        return applicantEmployerType;
    }

    public void setApplicantEmployerType(String applicantEmployerType) {
        this.applicantEmployerType = applicantEmployerType;
    }

    public String getApplicantEmployerIndustry() {
        return applicantEmployerIndustry;
    }

    public void setApplicantEmployerIndustry(String applicantEmployerIndustry) {
        this.applicantEmployerIndustry = applicantEmployerIndustry;
    }

    public String getApplicantPost() {
        return applicantPost;
    }

    public void setApplicantPost(String applicantPost) {
        this.applicantPost = applicantPost;
    }

    public String getApplicantOccupation() {
        return applicantOccupation;
    }

    public void setApplicantOccupation(String applicantOccupation) {
        this.applicantOccupation = applicantOccupation;
    }

    public Integer getApplicantWorkYears() {
        return applicantWorkYears;
    }

    public void setApplicantWorkYears(Integer applicantWorkYears) {
        this.applicantWorkYears = applicantWorkYears;
    }

    public String getApplicantPosition() {
        return applicantPosition;
    }

    public void setApplicantPosition(String applicantPosition) {
        this.applicantPosition = applicantPosition;
    }

    public String getApplicantJobTitle() {
        return applicantJobTitle;
    }

    public void setApplicantJobTitle(String applicantJobTitle) {
        this.applicantJobTitle = applicantJobTitle;
    }

    public String getApplicantPostAddress() {
        return applicantPostAddress;
    }

    public void setApplicantPostAddress(String applicantPostAddress) {
        this.applicantPostAddress = applicantPostAddress;
    }

    public String getApplicantPostCode() {
        return applicantPostCode;
    }

    public void setApplicantPostCode(String applicantPostCode) {
        this.applicantPostCode = applicantPostCode;
    }

    public String getApplicantFirstEmergencyContact() {
        return applicantFirstEmergencyContact;
    }

    public void setApplicantFirstEmergencyContact(String applicantFirstEmergencyContact) {
        this.applicantFirstEmergencyContact = applicantFirstEmergencyContact;
    }

    public String getApplicantFirstEmergencyContactRelationship() {
        return applicantFirstEmergencyContactRelationship;
    }

    public void setApplicantFirstEmergencyContactRelationship(String applicantFirstEmergencyContactRelationship) {
        this.applicantFirstEmergencyContactRelationship = applicantFirstEmergencyContactRelationship;
    }

    public String getApplicantFirstEmergencyContactMobileNumber() {
        return applicantFirstEmergencyContactMobileNumber;
    }

    public void setApplicantFirstEmergencyContactMobileNumber(String applicantFirstEmergencyContactMobileNumber) {
        this.applicantFirstEmergencyContactMobileNumber = applicantFirstEmergencyContactMobileNumber;
    }

    public String getApplicantFirstEmergencyContactAddress() {
        return applicantFirstEmergencyContactAddress;
    }

    public void setApplicantFirstEmergencyContactAddress(String applicantFirstEmergencyContactAddress) {
        this.applicantFirstEmergencyContactAddress = applicantFirstEmergencyContactAddress;
    }

    public String getApplicantSecondEmergencyContact() {
        return applicantSecondEmergencyContact;
    }

    public void setApplicantSecondEmergencyContact(String applicantSecondEmergencyContact) {
        this.applicantSecondEmergencyContact = applicantSecondEmergencyContact;
    }

    public String getApplicantSecondEmergencyContactRelationship() {
        return applicantSecondEmergencyContactRelationship;
    }

    public void setApplicantSecondEmergencyContactRelationship(String applicantSecondEmergencyContactRelationship) {
        this.applicantSecondEmergencyContactRelationship = applicantSecondEmergencyContactRelationship;
    }

    public String getApplicantSecondEmergencyContactMobileNumber() {
        return applicantSecondEmergencyContactMobileNumber;
    }

    public void setApplicantSecondEmergencyContactMobileNumber(String applicantSecondEmergencyContactMobileNumber) {
        this.applicantSecondEmergencyContactMobileNumber = applicantSecondEmergencyContactMobileNumber;
    }

    public String getApplicantSecondEmergencyContactAddress() {
        return applicantSecondEmergencyContactAddress;
    }

    public void setApplicantSecondEmergencyContactAddress(String applicantSecondEmergencyContactAddress) {
        this.applicantSecondEmergencyContactAddress = applicantSecondEmergencyContactAddress;
    }

    public String getApplicantCertificateFileIds() {
        return applicantCertificateFileIds;
    }

    public void setApplicantCertificateFileIds(String applicantCertificateFileIds) {
        this.applicantCertificateFileIds = applicantCertificateFileIds;
    }

    public String getApplicantQualificationFileIds() {
        return applicantQualificationFileIds;
    }

    public void setApplicantQualificationFileIds(String applicantQualificationFileIds) {
        this.applicantQualificationFileIds = applicantQualificationFileIds;
    }

    public String getApplicantIncomeFileIds() {
        return applicantIncomeFileIds;
    }

    public void setApplicantIncomeFileIds(String applicantIncomeFileIds) {
        this.applicantIncomeFileIds = applicantIncomeFileIds;
    }

    public String getApplicantEstateFileIds() {
        return applicantEstateFileIds;
    }

    public void setApplicantEstateFileIds(String applicantEstateFileIds) {
        this.applicantEstateFileIds = applicantEstateFileIds;
    }

    public String getApplicantVehicleFileIds() {
        return applicantVehicleFileIds;
    }

    public void setApplicantVehicleFileIds(String applicantVehicleFileIds) {
        this.applicantVehicleFileIds = applicantVehicleFileIds;
    }

    public String getApplicantOtherFileIds() {
        return applicantOtherFileIds;
    }

    public void setApplicantOtherFileIds(String applicantOtherFileIds) {
        this.applicantOtherFileIds = applicantOtherFileIds;
    }

    public String getCoApplicantName() {
        return coApplicantName;
    }

    public void setCoApplicantName(String coApplicantName) {
        this.coApplicantName = coApplicantName;
    }

    public String getCoApplicantCertificateNumber() {
        return coApplicantCertificateNumber;
    }

    public void setCoApplicantCertificateNumber(String coApplicantCertificateNumber) {
        this.coApplicantCertificateNumber = coApplicantCertificateNumber;
    }

    public String getCoApplicantMobileNumber() {
        return coApplicantMobileNumber;
    }

    public void setCoApplicantMobileNumber(String coApplicantMobileNumber) {
        this.coApplicantMobileNumber = coApplicantMobileNumber;
    }

    public String getCoApplicantQualification() {
        return coApplicantQualification;
    }

    public void setCoApplicantQualification(String coApplicantQualification) {
        this.coApplicantQualification = coApplicantQualification;
    }

    public Long getCoApplicantCensusCityId() {
        return coApplicantCensusCityId;
    }

    public void setCoApplicantCensusCityId(Long coApplicantCensusCityId) {
        this.coApplicantCensusCityId = coApplicantCensusCityId;
    }

    public String getCoApplicantAddress() {
        return coApplicantAddress;
    }

    public void setCoApplicantAddress(String coApplicantAddress) {
        this.coApplicantAddress = coApplicantAddress;
    }

    public Long getCoApplicantIncomePerMonth() {
        return coApplicantIncomePerMonth;
    }

    public void setCoApplicantIncomePerMonth(Long coApplicantIncomePerMonth) {
        this.coApplicantIncomePerMonth = coApplicantIncomePerMonth;
    }

    public String getCoApplicantTelephone() {
        return coApplicantTelephone;
    }

    public void setCoApplicantTelephone(String coApplicantTelephone) {
        this.coApplicantTelephone = coApplicantTelephone;
    }

    public String getCoApplicantEmployerName() {
        return coApplicantEmployerName;
    }

    public void setCoApplicantEmployerName(String coApplicantEmployerName) {
        this.coApplicantEmployerName = coApplicantEmployerName;
    }

    public String getCoApplicantEmployerTelephone() {
        return coApplicantEmployerTelephone;
    }

    public void setCoApplicantEmployerTelephone(String coApplicantEmployerTelephone) {
        this.coApplicantEmployerTelephone = coApplicantEmployerTelephone;
    }

    public String getCoApplicantEmployerAddress() {
        return coApplicantEmployerAddress;
    }

    public void setCoApplicantEmployerAddress(String coApplicantEmployerAddress) {
        this.coApplicantEmployerAddress = coApplicantEmployerAddress;
    }

    public String getCoApplicantCertificateFileIds() {
        return coApplicantCertificateFileIds;
    }

    public void setCoApplicantCertificateFileIds(String coApplicantCertificateFileIds) {
        this.coApplicantCertificateFileIds = coApplicantCertificateFileIds;
    }

    public String getCoApplicantIncomeFileIds() {
        return coApplicantIncomeFileIds;
    }

    public void setCoApplicantIncomeFileIds(String coApplicantIncomeFileIds) {
        this.coApplicantIncomeFileIds = coApplicantIncomeFileIds;
    }

    public String getCoApplicantEstateFileIds() {
        return coApplicantEstateFileIds;
    }

    public void setCoApplicantEstateFileIds(String coApplicantEstateFileIds) {
        this.coApplicantEstateFileIds = coApplicantEstateFileIds;
    }

    public String getCoApplicantOtherFileIds() {
        return coApplicantOtherFileIds;
    }

    public void setCoApplicantOtherFileIds(String coApplicantOtherFileIds) {
        this.coApplicantOtherFileIds = coApplicantOtherFileIds;
    }

    public String getGuarantorName() {
        return guarantorName;
    }

    public void setGuarantorName(String guarantorName) {
        this.guarantorName = guarantorName;
    }

    public String getGuarantorRelationship() {
        return guarantorRelationship;
    }

    public void setGuarantorRelationship(String guarantorRelationship) {
        this.guarantorRelationship = guarantorRelationship;
    }

    public String getGuarantorCertificateNumber() {
        return guarantorCertificateNumber;
    }

    public void setGuarantorCertificateNumber(String guarantorCertificateNumber) {
        this.guarantorCertificateNumber = guarantorCertificateNumber;
    }

    public String getGuarantorRealEstateOwnType() {
        return guarantorRealEstateOwnType;
    }

    public void setGuarantorRealEstateOwnType(String guarantorRealEstateOwnType) {
        this.guarantorRealEstateOwnType = guarantorRealEstateOwnType;
    }

    public Long getGuarantorIncomePerMonth() {
        return guarantorIncomePerMonth;
    }

    public void setGuarantorIncomePerMonth(Long guarantorIncomePerMonth) {
        this.guarantorIncomePerMonth = guarantorIncomePerMonth;
    }

    public String getGuarantorMobileNumber() {
        return guarantorMobileNumber;
    }

    public void setGuarantorMobileNumber(String guarantorMobileNumber) {
        this.guarantorMobileNumber = guarantorMobileNumber;
    }

    public String getGuarantorCertificateFileIds() {
        return guarantorCertificateFileIds;
    }

    public void setGuarantorCertificateFileIds(String guarantorCertificateFileIds) {
        this.guarantorCertificateFileIds = guarantorCertificateFileIds;
    }

    public String getGuarantorIncomeFileIds() {
        return guarantorIncomeFileIds;
    }

    public void setGuarantorIncomeFileIds(String guarantorIncomeFileIds) {
        this.guarantorIncomeFileIds = guarantorIncomeFileIds;
    }

    public String getGuarantorEstateFileIds() {
        return guarantorEstateFileIds;
    }

    public void setGuarantorEstateFileIds(String guarantorEstateFileIds) {
        this.guarantorEstateFileIds = guarantorEstateFileIds;
    }

    public String getGuarantorOtherFileIds() {
        return guarantorOtherFileIds;
    }

    public void setGuarantorOtherFileIds(String guarantorOtherFileIds) {
        this.guarantorOtherFileIds = guarantorOtherFileIds;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getVehicleManufacturers() {
        return vehicleManufacturers;
    }

    public void setVehicleManufacturers(String vehicleManufacturers) {
        this.vehicleManufacturers = vehicleManufacturers;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleSeries() {
        return vehicleSeries;
    }

    public void setVehicleSeries(String vehicleSeries) {
        this.vehicleSeries = vehicleSeries;
    }

    public String getVehicleProductionYearMonth() {
        return vehicleProductionYearMonth;
    }

    public void setVehicleProductionYearMonth(String vehicleProductionYearMonth) {
        this.vehicleProductionYearMonth = vehicleProductionYearMonth;
    }

    public String getVehicleRegistrationYearMonth() {
        return vehicleRegistrationYearMonth;
    }

    public void setVehicleRegistrationYearMonth(String vehicleRegistrationYearMonth) {
        this.vehicleRegistrationYearMonth = vehicleRegistrationYearMonth;
    }

    public Float getVehicleKilometers() {
        return vehicleKilometers;
    }

    public void setVehicleKilometers(Float vehicleKilometers) {
        this.vehicleKilometers = vehicleKilometers;
    }

    public String getVehicleUtilityType() {
        return vehicleUtilityType;
    }

    public void setVehicleUtilityType(String vehicleUtilityType) {
        this.vehicleUtilityType = vehicleUtilityType;
    }

    public String getVehicleEmission() {
        return vehicleEmission;
    }

    public void setVehicleEmission(String vehicleEmission) {
        this.vehicleEmission = vehicleEmission;
    }

    public String getVehicleRegistrationCertificateFileIds() {
        return vehicleRegistrationCertificateFileIds;
    }

    public void setVehicleRegistrationCertificateFileIds(String vehicleRegistrationCertificateFileIds) {
        this.vehicleRegistrationCertificateFileIds = vehicleRegistrationCertificateFileIds;
    }

    public String getVehicleLicenseFileIds() {
        return vehicleLicenseFileIds;
    }

    public void setVehicleLicenseFileIds(String vehicleLicenseFileIds) {
        this.vehicleLicenseFileIds = vehicleLicenseFileIds;
    }

    public String getVehicleFileIds() {
        return vehicleFileIds;
    }

    public void setVehicleFileIds(String vehicleFileIds) {
        this.vehicleFileIds = vehicleFileIds;
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

    public Long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }
}
