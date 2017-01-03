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
public class LoanVehicle {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // 自增id
    private Long id;

    @NotNull
    @Size(min = 20, max = 20)
    private String vin;

    @NotNull
    @Size(max = 20)
    private String manufacturers;

    @NotNull
    @Size(max = 20)
    private String brand;

    @NotNull
    @Size(max = 50)
    private String series;

    @NotNull
    @Digits(integer = 3, fraction = 4)
    private Float kilometers;

    @NotNull
    @Size(min = 5, max = 100)
    private String registrationCertificateFileIds;

    @NotNull
    @Size(min = 5, max = 100)
    private String licenseFileIds;

    @NotNull
    @Size(min = 5, max = 2000)
    private String vehicleFileIds;
}
