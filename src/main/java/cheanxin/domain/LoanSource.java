package cheanxin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 16/12/30.
 * 贷款来源
 */
@Entity
public class LoanSource {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // 自增id
    private Long id;

    @NotNull
    // 城市id
    private Long cityId;

    @NotNull
    @Min(0)
    @Max(8)
    // 来源渠道
    private Integer applicationSource;

    @NotNull
    @Size(max = 30)
    // 来源联系人姓名
    private String sourcePersonName;

    @NotNull
    @Size(max = 20)
    // 来源联系人电话
    private String sourcePersonTel;

    public LoanSource() {}

    public LoanSource(Long cityId, Integer applicationSource, String sourcePersonName, String sourcePersonTel) {
        this.cityId = cityId;
        this.applicationSource = applicationSource;
        this.sourcePersonName = sourcePersonName;
        this.sourcePersonTel = sourcePersonTel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Integer getApplicationSource() {
        return applicationSource;
    }

    public void setApplicationSource(Integer applicationSource) {
        this.applicationSource = applicationSource;
    }

    public String getSourcePersonName() {
        return sourcePersonName;
    }

    public void setSourcePersonName(String sourcePersonName) {
        this.sourcePersonName = sourcePersonName;
    }

    public String getSourcePersonTel() {
        return sourcePersonTel;
    }

    public void setSourcePersonTel(String sourcePersonTel) {
        this.sourcePersonTel = sourcePersonTel;
    }
}
