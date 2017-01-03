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
public class LoanSource {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // 自增id
    private Long id;

    @NotNull
    private Long cityId;

    @NotNull
    @Min(0)
    @Max(8)
    private Integer applicationSource;

    @NotNull
    @Size(max = 30)
    private String sourcePersonName;

    @NotNull
    @Size(max = 20)
    private String sourcePersonTel;


}
