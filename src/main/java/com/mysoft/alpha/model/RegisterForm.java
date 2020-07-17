package com.mysoft.alpha.model;

import lombok.Builder;

@Builder
public class RegisterForm {
	private String orgcode;
	private String crop;
	private int ctype;
	private String username;
	private String password;
	private String name;
	private String phone;
	private String email;

	public RegisterForm(String orgcode, String crop, int ctype, String username, String password, String name,
			String phone, String email) {
		super();
		this.orgcode = orgcode;
		this.crop = crop;
		this.ctype = ctype;
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public int getCtype() {
		return ctype;
	}

	public void setCtype(int ctype) {
		this.ctype = ctype;
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

	@Override
	public String toString() {
		return "RegisterForm [orgcode=" + orgcode + ", crop=" + crop + ", ctype=" + ctype + ", username=" + username
				+ ", password=" + password + ", name=" + name + ", phone=" + phone + ", email=" + email + "]";
	}

}
