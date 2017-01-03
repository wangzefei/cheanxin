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
 * 共同申请人
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
    // 证件图片
    private String certificateFileIds;

    @Size(max = 500)
    // 收入证明图片
    private String incomeFileIds;

    @Size(max = 200)
    // 房产图片
    private String estateFileIds;

    @Size(max = 500)
    // 其他图片
    private String otherFileIds;

    public LoanCoApplicant() {}

    public LoanCoApplicant(String certificateFileIds, String incomeFileIds, String estateFileIds, String otherFileIds) {
        this.certificateFileIds = certificateFileIds;
        this.incomeFileIds = incomeFileIds;
        this.estateFileIds = estateFileIds;
        this.otherFileIds = otherFileIds;
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
