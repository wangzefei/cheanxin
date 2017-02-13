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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '岗位名称'")
    // 岗位名称
    private String name;

    @NotNull
    @Min(0)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '岗位类型'")
    // 岗位类型
    private Long postTypeId;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '是否启用'")
    // 是否启用
    private Boolean enabled;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    // 创建时间
    private Long createdTime;

    @Transient
    // 岗位类型
    private String postType;

    public Post() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(Long postTypeId) {
        this.postTypeId = postTypeId;
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

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postTypeId=" + postTypeId +
                ", postType='" + postType + '\'' +
                '}';
    }
}
