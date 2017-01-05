package cheanxin.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by 273cn on 16/12/30.
 */
@Entity
@Table(indexes = { @Index(name = "idx_parent_dept_id", columnList = "parentDeptId") })
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(columnDefinition = "VARCHAR(10) COMMENT '部门编码'")
    // 部门编码
    private String deptCode;

    @NotNull
    @Size(max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '部门名称'")
    // 部门名称
    private String name;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '上级部门'")
    // 上级部门
    private Long parentDeptId;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '是否启用'")
    // 是否启用
    private Boolean enabled;

    // 创建时间
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    public Dept() {}

    public Dept(String deptCode, Long parentDeptId, Boolean enabled, Long createdTime) {
        this.deptCode = deptCode;
        this.parentDeptId = parentDeptId;
        this.enabled = enabled;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        enabled = enabled;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
