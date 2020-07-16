package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * User entity.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Username.
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * Password.
     */
    private String password;

    /**
     * Salt for encoding.
     */
    private String salt;

    /**
     * Real name.
     */
    private String name;

    /**
     * Phone number.
     */
    private String phone;

    /**
     * Email address.
     *
     * A Email address can be null,but should be correct if exists.
     */
    @Email(message = "请输入正确的邮箱")
    private String email;
    
    private String crop;//公司名称
    
    private String orgcode;//组织机构代码

    /**
     * User status.
     */
    private boolean enabled;

    /**
     * Transient property for storing role owned by current user.
     */
    @Transient
    private List<AdminRole> roles;

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
    public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<AdminRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AdminRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", name="
				+ name + ", phone=" + phone + ", email=" + email +", crop=" + crop+ ", orgcode=" + orgcode + ", enabled=" + enabled + ", roles=" + roles + "]";
	}

}

