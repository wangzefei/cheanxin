package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by 273cn on 17/1/3.
 * 岗位
 */
@Entity
public class Post {
    @Id
    @Null
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Digits(integer = 3, fraction = 0)
    @Size(min = 3, max = 3)
    @Column(columnDefinition = "CHAR(3) COMMENT '岗位编号'")
    // 岗位编号
    private String serialNumber;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '岗位名称'")
    // 岗位名称
    private String name;

    @NotNull
    @Min(0)
    @Max(8)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '岗位类型'")
    // 岗位类型
    private Integer type;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '是否禁用'")
    // 是否禁用
    private Boolean isDiabled;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    // 创建时间
    private Long createdTime;

    public Post() {}

    public Post(String serialNumber, String name, Integer type, Boolean isDiabled, Long createdTime) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.type = type;
        this.isDiabled = isDiabled;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
