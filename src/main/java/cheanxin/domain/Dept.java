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
    // 部门编码
    private String deptCode;

    @NotNull
    @Size(max = 50)
    // 部门名称
    private String name;

    @NotNull
    // 上级部门
    private Long parentDeptId;

    @NotNull
    // 是否禁用
    private Boolean isDiabled;

    // 创建时间
    private Long createdTime;

    public Dept() {}

    public Dept(String deptCode, Long parentDeptId, Boolean isDiabled, Long createdTime) {
        this.deptCode = deptCode;
        this.parentDeptId = parentDeptId;
        this.isDiabled = isDiabled;
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

    public Boolean getDiabled() {
        return isDiabled;
    }

    public void setDiabled(Boolean diabled) {
        isDiabled = diabled;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
