package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 *
 *  entity
 *  class 数据对象类
 *  Serializable 序列化用于网络传输
 *  创建空构造函数
 *  get，set
 *  tostring
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Entity
@Table(name = "company")
//@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Company implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

	@Column(name ="code",columnDefinition = " VARCHAR(255) NULL  COMMENT '组织机构代码，验证，不能重复'")
	@NotBlank()
	private String code;

	@Column(name ="name",columnDefinition = " VARCHAR(255) NULL  COMMENT '企业名称'")
	private String name;

	@Column(name = "ctype",columnDefinition = " INT(11) NULL  COMMENT '企业类型，1、保险商（产品企业），2、服务商（服务企业）'")
	private Integer ctype;

	@Column(name ="phone",columnDefinition = " VARCHAR(255) NULL  COMMENT '联系电话'")
	@NotBlank()
	private String phone;

	@Column(name = "enabled",columnDefinition = " INT(11) NULL  COMMENT '是否可用，0不可用，1可用'")
	private Integer enabled;

	@Column(name ="operator",columnDefinition = " VARCHAR(255) NULL  COMMENT '操作员'")
	private String operator;

	@Column(name ="create_time" ,columnDefinition = " datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Date createTime;


	public Company() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCtype() {
		return ctype;
	}

	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
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

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Company{");
		sb.append("id=").append(id);
		sb.append(", code='").append(code).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append(", ctype=").append(ctype);
		sb.append(", phone='").append(phone).append('\'');
		sb.append(", enabled=").append(enabled);
		sb.append(", operator='").append(operator).append('\'');
		sb.append(", createTime=").append(createTime);
		sb.append('}');
		return sb.toString();
	}
}
