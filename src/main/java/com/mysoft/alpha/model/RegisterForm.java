package com.mysoft.alpha.model;

import java.io.Serializable;

/**
 *
 *  model
 *  class 输出模型
 *  Serializable 序列化用于网络传输
 *  创建空构造函数, 和全部参数函数
 *  get，set
 *  tostring
 */
//@Builder
public class RegisterForm implements Serializable {
	private String orgcode;
	private String crop;
	private int ctype;
	private String username;
	private String password;
	private String name;
	private String phone;
	private String email;
	private int itype;//插入类型，1为注册，2为管理员添加
    private int roleid;//角色选择

	public RegisterForm() {
	}

    public RegisterForm(String orgcode, String crop, int ctype, String username, String password, String name,
                        String phone, String email, int itype, int roleid) {
        this.orgcode = orgcode;
        this.crop = crop;
        this.ctype = ctype;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.itype = itype;
        this.roleid = roleid;
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


	public int getItype() {
		return itype;
	}

	public void setItype(int itype) {
		this.itype = itype;
	}

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RegisterForm{");
        sb.append("orgcode='").append(orgcode).append('\'');
        sb.append(", crop='").append(crop).append('\'');
        sb.append(", ctype=").append(ctype);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", itype=").append(itype);
        sb.append(", roleid=").append(roleid);
        sb.append('}');
        return sb.toString();
    }
}
