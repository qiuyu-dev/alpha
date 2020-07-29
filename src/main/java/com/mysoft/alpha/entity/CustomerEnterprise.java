package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
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

@Entity
@Table(name = "customer_enterprise")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class CustomerEnterprise  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Id 主键列
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) 主键生成策略
	 * @Column(name = "id") 列名
	 * 最好使用包装数据类型Integer等
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "certificate_type")
    private String certificateType;
    
    @Column(name = "insured_id")
    private String insuredId;
    
    @Column(name = "cname")
    private String cname;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "eid")
    private int eid;

    @Column(name = "effective_date")
    private Date effectiveDate;
    
    @Column(name = "closing_date")
    private Date closingDate;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "from_type")
    private int fromType;
    
    @Column(name = "from_id")
    private int fromId;
    
    @Column(name = "cpem_id")
    private int cpemId;
    
    @Column(name="cped_id")
    private int cpedId;



	@Column(name = "remark")
	private String remark;

	@Column(name = "confirm_remark")
	private String confirmRemark;
    
    @Column(name = "operator")
    private String operator;


	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh",timezone="GMT+8")
	@Column(name = "create_time",insertable = false)
    private Date createTime;

	public CustomerEnterprise() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getInsuredId() {
		return insuredId;
	}

	public void setInsuredId(String insuredId) {
		this.insuredId = insuredId;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFromType() {
		return fromType;
	}

	public void setFromType(int fromType) {
		this.fromType = fromType;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getCpemId() {
		return cpemId;
	}

	public void setCpemId(int cpemId) {
		this.cpemId = cpemId;
	}

	public int getCpedId() {
		return cpedId;
	}

	public void setCpedId(int cpedId) {
		this.cpedId = cpedId;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getConfirmRemark() {
		return confirmRemark;
	}

	public void setConfirmRemark(String confirmRemark) {
		this.confirmRemark = confirmRemark;
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
		final StringBuffer sb = new StringBuffer("CustomerEnterprise{");
		sb.append("id=").append(id);
		sb.append(", certificateType='").append(certificateType).append('\'');
		sb.append(", insuredId='").append(insuredId).append('\'');
		sb.append(", cname='").append(cname).append('\'');
		sb.append(", phone='").append(phone).append('\'');
		sb.append(", eid=").append(eid);
		sb.append(", effectiveDate=").append(effectiveDate);
		sb.append(", closingDate=").append(closingDate);
		sb.append(", status='").append(status).append('\'');
		sb.append(", fromType=").append(fromType);
		sb.append(", fromId=").append(fromId);
		sb.append(", cpemId=").append(cpemId);
		sb.append(", cpedId=").append(cpedId);
		sb.append(", confirmRemark='").append(confirmRemark).append('\'');
		sb.append(", remark='").append(remark).append('\'');
		sb.append(", operator='").append(operator).append('\'');
		sb.append(", createTime=").append(createTime);
		sb.append('}');
		return sb.toString();
	}
}
