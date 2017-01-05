package cheanxin.domain;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created by 273cn on 16/12/15.
 * 用户，可以用usernmae登录到系统
 */
@Entity
@Table(indexes = { @Index(name = "idx_username", columnList = "username", unique = true) })
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '用户账号（登录用）'")
    // 用户账号（登录用）
    private String username;

    @NotNull
    @Size(min = 6, max = 80)
    @Column(columnDefinition = "CHAR(80) COMMENT '用户密码（登录用）,使用Spring Security的BaseEncoder加密'")
    // 用户密码（登录用）
    private String password;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '用户姓名'")
    // 用户姓名
    private String realName;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '部门id'")
    // 部门id
    private Long deptId;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '用户照片'")
    // 用户照片
    private String photo;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '地区'")
    // 地区
    private String region;

    @NotNull
    @Pattern(regexp = "^1[34578][0-9]{9}$")
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) COMMENT '手机号'")
    // 手机号
    private String mobileNumber;

    @NotNull
    @Email
    @Size(max = 64)
    @Column(columnDefinition = "VARCHAR(64) COMMENT '邮箱'")
    // 邮箱
    private String email;

    @NotNull
    @Pattern(regexp = "[0-9]{6}(18|19|20)[0-9]{2}((0[0-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)[0-9]{2}[12][0-9Xx]")
    @Size(max = 18)
    @Column(columnDefinition = "CHAR(18) COMMENT '身份证号码'")
    // 身份证号码
    private String identityNumber;

    @NotNull
    @Size(min = 4, max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '身份证照片'")
    // 身份证照片
    private String identityPhoto;

    @NotNull
    @Size(min = 10, max = 300)
    @Column(columnDefinition = "VARCHAR(300) COMMENT '地址'")
    // 地址
    private String address;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(columnDefinition = "VARCHAR(30) COMMENT '紧急联系人'")
    // 紧急联系人
    private String emergencyContact;

    @NotNull
    @Pattern(regexp = "^1[34578][0-9]{9}$")
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) COMMENT '紧急联系人手机号码'")
    // 紧急联系人手机号码
    private String emergencyContactMobileNumber;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    // 创建时间
    private Long createdTime;

    @Transient
    // 用户权限
    private Collection<? extends GrantedAuthority> authorities;

    public User() {}

    public User(String username, String password, String realName, Long deptId, String photo, String region, String mobileNumber, String email, String identityNumber, String identityPhoto, String address, String emergencyContact, String emergencyContactMobileNumber, Long createdTime, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.deptId = deptId;
        this.photo = photo;
        this.region = region;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.identityNumber = identityNumber;
        this.identityPhoto = identityPhoto;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.emergencyContactMobileNumber = emergencyContactMobileNumber;
        this.createdTime = createdTime;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getIdentityPhoto() {
        return identityPhoto;
    }

    public void setIdentityPhoto(String identityPhoto) {
        this.identityPhoto = identityPhoto;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactMobileNumber() {
        return emergencyContactMobileNumber;
    }

    public void setEmergencyContactMobileNumber(String emergencyContactMobileNumber) {
        this.emergencyContactMobileNumber = emergencyContactMobileNumber;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
