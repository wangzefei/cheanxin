package cheanxin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * Created by 273cn on 16/12/30.
 */
@Entity
public class LoanGuarantor {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // 自增id
    private Long id;

    @Size(max = 500)
    private String incomeFileIds;

    @Size(max = 200)
    private String estateFileIds;

    @Size(max = 500)
    private String otherFileIds;
}
