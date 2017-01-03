package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 16/12/30.
 */
@Entity
@Table(indexes = {@Index(name = "idx_loan_id", columnList = "loanId"), @Index(name = "idx_operator_uid", columnList = "operatorUid")})
public class LoanLog {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // 自增id
    private Long id;

    @NotNull
    private Long loanId;

    @NotNull
    private Long operatorUid;

    @NotNull
    @Min(0)
    @Max(16)
    private Integer operatorType;

    @Size(max = 200)
    private String remark;

    private Long createdTime;
}
