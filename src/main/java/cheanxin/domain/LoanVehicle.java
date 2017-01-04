package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 16/12/30.
 * 贷款车辆
 */
@Entity
public class LoanVehicle {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Size(min = 17, max = 17)
    @Column(columnDefinition = "CHAR(17) COMMENT '车辆vin码'")
    // 车辆vin码
    private String vin;

    @NotNull
    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '厂家'")
    // 厂家
    private String manufacturers;

    @NotNull
    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '品牌'")
    // 品牌
    private String brand;

    @NotNull
    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '车系'")
    // 车系
    private String series;

    @NotNull
    @Size(min = 4, max = 10)
    @Column(columnDefinition = "VARCHAR(10) COMMENT '生产年月'")
    private String productionYearMonth;

    @NotNull
    @Size(min = 4, max = 10)
    @Column(columnDefinition = "VARCHAR(10) COMMENT '首次登记年月'")
    private String registrationYearMonth;

    @NotNull
    @Digits(integer = 3, fraction = 4)
    @Column(columnDefinition = "DECIMAL(7,4) UNSIGNED COMMENT '公里数'")
    // 公里数
    private Float kilometers;

    @NotNull
    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '使用性质'")
    private String utilityType;

    @NotNull
    @Size(max = 10)
    @Column(columnDefinition = "VARCHAR(10) COMMENT '排放标准'")
    private String emission;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '车辆登记证书图片'")
    // 车辆登记证书图片
    private String registrationCertificateFileIds;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '机动车行驶证图片'")
    // 机动车行驶证图片
    private String licenseFileIds;

    @NotNull
    @Size(min = 5, max = 2000)
    @Column(columnDefinition = "VARCHAR(2000) COMMENT '车辆图片'")
    // 车辆图片
    private String vehicleFileIds;

    public LoanVehicle() {}

    public LoanVehicle(String vin, String manufacturers, String brand, String series, String productionYearMonth, String registrationYearMonth, Float kilometers, String utilityType, String emission, String registrationCertificateFileIds, String licenseFileIds, String vehicleFileIds) {
        this.vin = vin;
        this.manufacturers = manufacturers;
        this.brand = brand;
        this.series = series;
        this.productionYearMonth = productionYearMonth;
        this.registrationYearMonth = registrationYearMonth;
        this.kilometers = kilometers;
        this.utilityType = utilityType;
        this.emission = emission;
        this.registrationCertificateFileIds = registrationCertificateFileIds;
        this.licenseFileIds = licenseFileIds;
        this.vehicleFileIds = vehicleFileIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(String manufacturers) {
        this.manufacturers = manufacturers;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getProductionYearMonth() {
        return productionYearMonth;
    }

    public void setProductionYearMonth(String productionYearMonth) {
        this.productionYearMonth = productionYearMonth;
    }

    public String getRegistrationYearMonth() {
        return registrationYearMonth;
    }

    public void setRegistrationYearMonth(String registrationYearMonth) {
        this.registrationYearMonth = registrationYearMonth;
    }

    public Float getKilometers() {
        return kilometers;
    }

    public void setKilometers(Float kilometers) {
        this.kilometers = kilometers;
    }

    public String getUtilityType() {
        return utilityType;
    }

    public void setUtilityType(String utilityType) {
        this.utilityType = utilityType;
    }

    public String getEmission() {
        return emission;
    }

    public void setEmission(String emission) {
        this.emission = emission;
    }

    public String getRegistrationCertificateFileIds() {
        return registrationCertificateFileIds;
    }

    public void setRegistrationCertificateFileIds(String registrationCertificateFileIds) {
        this.registrationCertificateFileIds = registrationCertificateFileIds;
    }

    public String getLicenseFileIds() {
        return licenseFileIds;
    }

    public void setLicenseFileIds(String licenseFileIds) {
        this.licenseFileIds = licenseFileIds;
    }

    public String getVehicleFileIds() {
        return vehicleFileIds;
    }

    public void setVehicleFileIds(String vehicleFileIds) {
        this.vehicleFileIds = vehicleFileIds;
    }
}
