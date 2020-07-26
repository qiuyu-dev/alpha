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
	
	 @Column(name = "cp_excel_detail_id")
	 private int cpExcelDetailId;
	
    @Column(name = "certificate_type")
    private String certificateType;
    
    @Column(name = "insured_id")
    private String insuredId;
    
    @Column(name = "cname")
    private String cname;
    
    @Column(name = "phonenum")
    private String phonenum;
    
    @Column(name = "eid")
    private int eid;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "age")
    private Integer age;
    
    @Column(name = "sex")
    private String sex;
    
    @Column(name = "effective_date")
    private Date effectiveDate;
    
    @Column(name = "closing_date")
    private Date closingDate;
    
    @Column(name = "cestatus")
    private int cestatus;//状态,1，新增，2已核实，3未通过，4、延续，
    
    @Column(name = "from_type")
    private int fromType;
    
    @Column(name = "from_id")
    private int fromId;
    
    @Column(name = "cpem_id")
    private int cpemId;
    
    @Column(name="cped_id")
    private int cpedId;
    
    @Column(name = "operator")
    private String operator;
    

	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh",timezone="GMT+8")
	@Column(name = "create_time",insertable = false)
    private Date createTime;
    
    @Column(name = "reson")
    private String reson;

	public CustomerEnterprise() {
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

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public int getCestatus() {
		return cestatus;
	}

	public void setCestatus(int cestatus) {
		this.cestatus = cestatus;
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

	public String getReson() {
		return reson;
	}

	public void setReson(String reson) {
		this.reson = reson;
	}
	
	public int getCpExcelDetailId() {
		return cpExcelDetailId;
	}

	public void setCpExcelDetailId(int cpExcelDetailId) {
		this.cpExcelDetailId = cpExcelDetailId;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("CustomerEnterprise{");
		sb.append("id=").append(id);
		sb.append(", cpExcelDetailId='").append(cpExcelDetailId).append('\'');
		sb.append(", certificateType='").append(certificateType).append('\'');
		sb.append(", insuredId='").append(insuredId).append('\'');
		sb.append(", cname='").append(cname).append('\'');
		sb.append(", phonenum='").append(phonenum).append('\'');
		sb.append(", eid=").append(eid);
		sb.append(", location='").append(location).append('\'');
		sb.append(", age=").append(age);
		sb.append(", sex='").append(sex).append('\'');
		sb.append(", effectiveDate=").append(effectiveDate);
		sb.append(", closingDate=").append(closingDate);
		sb.append(", cestatus=").append(cestatus);
		sb.append(", fromType=").append(fromType);
		sb.append(", fromId=").append(fromId);
		sb.append(", cpemId=").append(cpemId);
		sb.append(", cpedId=").append(cpedId);
		sb.append(", operator='").append(operator).append('\'');
		sb.append(", createTime=").append(createTime);
		sb.append(", reson='").append(reson).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
