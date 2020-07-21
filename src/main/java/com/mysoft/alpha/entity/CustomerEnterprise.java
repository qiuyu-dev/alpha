package com.mysoft.alpha.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;


@Data
@Entity
@Table(name = "customer_enterprise")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class CustomerEnterprise {
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
    
    @Column(name = "phonenum")
    private String phonenum;
    
    @Column(name = "eid")
    private int eid;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "age")
    private String age;
    
    @Column(name = "sex")
    private String sex;
    
    @Column(name = "begin_time")
    private Date beginTime;
    
    @Column(name = "end_time")
    private Date endTime;
    
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
    
    @Column(name = "create_time")
    private Date createTime;
    
    @Column(name = "reson")
    private String reson;
    
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public String getReson() {
		return reson;
	}

	public void setReson(String reson) {
		this.reson = reson;
	}    

}
