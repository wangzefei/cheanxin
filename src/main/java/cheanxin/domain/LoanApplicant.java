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
public class LoanApplicant {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // 自增id
    private Long id;

    @NotNull
    @Size(max = 20)
    private String applicantName;

    @NotNull
    @Size(max = 20)
    private String applicantTel;

    @NotNull
    @Min(0)
    @Max(8)
    private Integer certificateType;

    @NotNull
    @Size(min = 6, max = 20)
    private String certificateNum;

    @NotNull
    @Min(0)
    @Max(8)
    private Integer marriage;

    @NotNull
    @Min(0)
    private Long incomePerMonth;

    @NotNull
    @Size(min = 0, max = 100)
    private String certificateFileIds;

    @Size(max = 200)
    private String qualificationFileIds;

    @Size(max = 500)
    private String incomeFileIds;

    @Size(max = 200)
    private String estateFileIds;

    @Size(max = 200)
    private String vehicleFileIds;

    @Size(max = 500)
    private String otherFileIds;
}
