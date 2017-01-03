package cheanxin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * Created by 273cn on 16/12/30.
 */
@Entity
public class LoanCoApplicant {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // 自增id
    private Long id;

    @NotNull
    @Size(min = 5, max = 200)
    private String certificateFileIds;

    @Size(max = 500)
    private String incomeFileIds;

    @Size(max = 200)
    private String estateFileIds;

    @Size(max = 500)
    private String otherFileIds;
}
