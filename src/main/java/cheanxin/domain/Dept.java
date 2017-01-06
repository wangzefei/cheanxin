package cheanxin.domain;


import cheanxin.global.Constants;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    @Min(1)
    @Max(Constants.DEPT_MAX_LEVEL)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED NOT NULL COMMENT '部门层级'")
    private Integer level;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '是否启用'")
    // 是否启用
    private Boolean enabled;

    // 创建时间
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    public Dept() {}

    public Dept(String deptCode, String name, Long parentDeptId, Integer level, Boolean enabled, Long createdTime) {
        this.deptCode = deptCode;
        this.name = name;
        this.parentDeptId = parentDeptId;
        this.level = level;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(Long parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
