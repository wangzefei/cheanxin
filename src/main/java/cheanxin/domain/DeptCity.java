package cheanxin.domain;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Jawinton on 17/02/16.
 */
@Entity
@Table(indexes = { @Index(name = "idx_dept_id_city_id", columnList = "deptId, cityId", unique = true) })
public class DeptCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '部门ID'")
    // 部门ID
    private Long deptId;

    @Min(0)
    @Column(columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '城市id'")
    // 城市id
    private Long cityId;

    @Transient
    private Set<Long> cityIds;

    // 创建时间
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private String creatorUsername;

    public DeptCity() {}

    @Override
    public String toString() {
        return "DeptCity{" +
                "id=" + id +
                ", deptId=" + deptId +
                ", cityId=" + cityId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public Set<Long> getCityIds() {
        return cityIds;
    }

    public void setCityIds(Set<Long> cityIds) {
        this.cityIds = cityIds;
    }
}
