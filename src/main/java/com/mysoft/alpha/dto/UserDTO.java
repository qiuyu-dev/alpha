package com.mysoft.alpha.dto;

import com.mysoft.alpha.dto.base.OutputConverter;
import com.mysoft.alpha.entity.AdminRole;
import com.mysoft.alpha.entity.User;

import java.util.List;

//@Data
//@ToString
/**
 *
 *  dto
 *  class 输出对象类
 *  get，set
 *  tostring
 *
 */
public class UserDTO implements OutputConverter<UserDTO, User> {

    private int id;

    private String username;

    private String name;

    private String phone;

    private String email;

    private boolean enabled;

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
		final StringBuffer sb = new StringBuffer("UserDTO{");
		sb.append("id=").append(id);
		sb.append(", username='").append(username).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append(", phone='").append(phone).append('\'');
		sb.append(", email='").append(email).append('\'');
		sb.append(", enabled=").append(enabled);
		sb.append(", roles=").append(roles);
		sb.append('}');
		return sb.toString();
	}
}
