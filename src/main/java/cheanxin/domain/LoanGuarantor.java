package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by 273cn on 16/12/30.
 * 担保人
 */
@Entity
public class LoanGuarantor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '证件图片'")
    // 证件图片
    private String certificateFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '收入证明图片'")
    // 收入证明图片
    private String incomeFileIds;

    @Size(max = 200)
    @Column(columnDefinition = "VARCHAR(200) COMMENT '房产图片'")
    // 房产图片
    private String estateFileIds;

    @Size(max = 500)
    @Column(columnDefinition = "VARCHAR(500) COMMENT '其他图片'")
    // 其他图片
    private String otherFileIds;

    public LoanGuarantor() {}

    public LoanGuarantor(LoanDraft loanDraft) {
        this.incomeFileIds = loanDraft.getGuarantorIncomeFileIds();
        this.estateFileIds = loanDraft.getGuarantorEstateFileIds();
        this.otherFileIds = loanDraft.getGuarantorOtherFileIds();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertificateFileIds() {
        return certificateFileIds;
    }

    public void setCertificateFileIds(String certificateFileIds) {
        this.certificateFileIds = certificateFileIds;
    }

    public String getIncomeFileIds() {
        return incomeFileIds;
    }

    public void setIncomeFileIds(String incomeFileIds) {
        this.incomeFileIds = incomeFileIds;
    }

    public String getEstateFileIds() {
        return estateFileIds;
    }

    public void setEstateFileIds(String estateFileIds) {
        this.estateFileIds = estateFileIds;
    }

    public String getOtherFileIds() {
        return otherFileIds;
    }

    public void setOtherFileIds(String otherFileIds) {
        this.otherFileIds = otherFileIds;
    }
}
