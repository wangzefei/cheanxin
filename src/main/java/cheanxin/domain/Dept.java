package cheanxin.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 16/12/30.
 */
@Entity
@Table(indexes = { @Index(name = "idx_parent_dept_id", columnList = "parentDeptId") })
public class Dept {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // 自增id
    private Long id;

    @NotNull
    @Size(min = 1, max = 10)
    private String deptCode;

    @NotNull
    private Long parentDeptId;

    @NotNull
    @Min(value = 0)
    @Max(value = 4)
    private Integer status;

    private Long createdTime;

    public Dept() {}

    public Dept(String deptCode, Long parentDeptId, Integer status, Long createdTime) {
        this.deptCode = deptCode;
        this.parentDeptId = parentDeptId;
        this.status = status;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Long getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(Long parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
