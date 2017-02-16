package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by 273cn on 16/12/30.
 * 贷款操作日志
 */
@Entity
@Table(indexes = {@Index(name = "idx_loan_id", columnList = "loanId"), @Index(name = "idx_operator_username", columnList = "operatorUsername"), @Index(name = "idx_loan_draft_id", columnList = "loanDraftId")})
public class LoanLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '贷款id'")
    // 贷款id
    private Long loanId;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '贷款草稿箱id'")
    // 贷款草稿箱id
    private Long loanDraftId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20) COMMENT '操作人username'")
    private String operatorUsername;

    @NotNull
    @Min(0)
    @Max(16)
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '操作类型'")
    // 操作类型
    private Integer operatorType;

    @Size(max = 255)
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注'")
    // 备注
    private String remark;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    // 创建时间
    private Long createdTime;

    public LoanLog() {}

    public LoanLog(Long loanId, Long loanDraftId, String operatorUsername, Integer operatorType, String remark, Long createdTime) {
        this.loanId = loanId;
        this.loanDraftId = loanDraftId;
        this.operatorUsername = operatorUsername;
        this.operatorType = operatorType;
        this.remark = remark;
        this.createdTime = createdTime;
    }

    public LoanLog(Long loanDraftId, String operatorUsername, Integer operatorType, String remark, Long createdTime) {
        this.loanDraftId = loanDraftId;
        this.operatorUsername = operatorUsername;
        this.operatorType = operatorType;
        this.remark = remark;
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "LoanLog{" +
                "loanDraftId=" + loanDraftId +
                ", id=" + id +
                ", operatorUsername='" + operatorUsername + '\'' +
                ", operatorType=" + operatorType +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getLoanDraftId() {
        return loanDraftId;
    }

    public void setLoanDraftId(Long loanDraftId) {
        this.loanDraftId = loanDraftId;
    }

    public String getOperatorUsername() {
        return operatorUsername;
    }

    public void setOperatorUsername(String operatorUsername) {
        this.operatorUsername = operatorUsername;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
