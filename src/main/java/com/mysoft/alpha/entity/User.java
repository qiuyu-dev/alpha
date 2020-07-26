package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * entity
 * class 数据对象类
 * Serializable 序列化用于网络传输
 * 创建空构造函数
 * get，set
 * tostring
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Entity
@Table(name = "user")
//@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Username.
     */
    @Column(name = "username", columnDefinition = " VARCHAR(255) NULL  COMMENT '登录名，验证，不能重复'")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * Password.
     */
    @Column(name = "password", columnDefinition = " VARCHAR(255) NULL  COMMENT '密码，md5加密'")
    @NotBlank()
    private String password;

    /**
     * Real name.
     */
    @Column(name = "name", columnDefinition = " VARCHAR(255) NULL  COMMENT '姓名'")
    private String name;

    /**
     * Salt for encoding.
     */
    @Column(name = "salt", columnDefinition = " VARCHAR(255) NULL  COMMENT '盐值'")
    private String salt;


    /**
     * Phone number.
     */
    @Column(name = "phone", columnDefinition = " VARCHAR(255) NULL  COMMENT '联系电话'")
    @NotBlank()
    private String phone;

    /**
     * Email address.
     * <p>
     * A Email address can be null,but should be correct if exists.
     */
    @Column(name = "email", columnDefinition = " VARCHAR(255) NULL  COMMENT '邮箱'")
    @Email(message = "请输入正确的邮箱")
    private String email;

    /**
     * User status.
     */
    @Column(name = "enabled", columnDefinition = " INT(11) NULL DEFAULT 1  COMMENT '是否可用，0不可用，1可用'")
    private Integer enabled;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", nullable = true)
    @JsonIgnore
    @JsonIgnoreProperties
    private Company company;

    @Column(name = "operator", columnDefinition = " VARCHAR(255) NULL  COMMENT '操作员'")
    private String operator;

    @Column(name = "create_time", columnDefinition = " datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date createTime;


    /**
     * Transient property for storing role owned by current user.
     */
    @Transient
    private List<AdminRole> roles;

    public User() {
    }

    public User(@NotBlank(message = "用户名不能为空") String username, @NotBlank() String password, String name, String salt,
                @NotBlank() String phone, @Email(message = "请输入正确的邮箱") String email, Integer enabled, Company company,
                String operator, List<AdminRole> roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.salt = salt;
        this.phone = phone;
        this.email = email;
        this.enabled = enabled;
        this.company = company;
        this.operator = operator;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<AdminRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AdminRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", salt='").append(salt).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", enabled=").append(enabled);
        sb.append(", company=").append(company);
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}

