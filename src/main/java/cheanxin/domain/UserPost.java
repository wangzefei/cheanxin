package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by 273cn on 17/1/3.
 * 用户岗位表
 */
@Entity
@Table(indexes = { @Index(name = "idx_username_post_id", columnList = "username, postId", unique = true) })
public class UserPost {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '用户名'")
    // 用户名
    private String username;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '岗位id'")
    // 岗位id
    private Long postId;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    // 创建时间
    private Long createdTime;

    public UserPost() {}

    public UserPost(String username, Long postId, Long createdTime) {
        this.username = username;
        this.postId = postId;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
