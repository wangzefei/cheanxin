package cheanxin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    // 自增id
    private Long id;

    @NotNull
    @Size(min = 20, max = 20)
    // 车辆vin码
    private String vin;

    @NotNull
    @Size(max = 20)
    // 厂家
    private String manufacturers;

    @NotNull
    @Size(max = 20)
    // 品牌
    private String brand;

    @NotNull
    @Size(max = 50)
    // 车系
    private String series;

    @NotNull
    @Digits(integer = 3, fraction = 4)
    // 公里数
    private Float kilometers;

    @NotNull
    @Size(min = 5, max = 100)
    // 车辆登记证书图片
    private String registrationCertificateFileIds;

    @NotNull
    @Size(min = 5, max = 100)
    // 机动车行驶证图片
    private String licenseFileIds;

    @NotNull
    @Size(min = 5, max = 2000)
    // 车辆图片
    private String vehicleFileIds;

    public LoanVehicle() {}

    public LoanVehicle(String vin, String manufacturers, String brand, String series, Float kilometers, String registrationCertificateFileIds, String licenseFileIds, String vehicleFileIds) {
        this.vin = vin;
        this.manufacturers = manufacturers;
        this.brand = brand;
        this.series = series;
        this.kilometers = kilometers;
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

    public Float getKilometers() {
        return kilometers;
    }

    public void setKilometers(Float kilometers) {
        this.kilometers = kilometers;
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
