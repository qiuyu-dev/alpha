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

//客户单
@Data
@Entity
@Table(name = "customer_order")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "seq_number")
    private String seqNumber;//序号
    
    @Column(name = "policy_number")
    private String policyNumber;//保单号
    
    @Column(name = "product")
    private String product;//产品
    
    @Column(name = "insured_name")
    private String insuredName;//被保险人姓名
    
    @Column(name="certificate_type")
    private String certificateType;//证件类型 1  身份证 2 护照
    
    @Column(name="phonenum ")
    private String phonenum ;//电话号码
    
    @Column(name="insured_id")
    private String insuredId;//被保险人证件号
    
    @Column(name="effective_date")
    private Date effectiveDate;//生效日
    
    @Column(name="closing_date")
    private Date closingDate;//截止日
    
    @Column(name="sex")
    private String sex;//性别 1男 2女
    
    @Column(name="age")
    private int age;//年龄
    
    @Column(name="location")
    private String location;//所在地
    
    @Column(name="remark")
    private String remark;//备注
    
    @Column(name="state")
    private String state;//状态 1新增 2通过 3未通过 4修改

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(String seqNumber) {
		this.seqNumber = seqNumber;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getInsuredId() {
		return insuredId;
	}

	public void setInsuredId(String insuredId) {
		this.insuredId = insuredId;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "CustomerOrder [id=" + id + ", seqNumber=" + seqNumber + ", policyNumber=" + policyNumber + ", product="
				+ product + ", insuredName=" + insuredName + ", certificateType=" + certificateType + ", phonenum="
				+ phonenum + ", insuredId=" + insuredId + ", effectiveDate=" + effectiveDate + ", closingDate="
				+ closingDate + ", sex=" + sex + ", age=" + age + ", location=" + location + ", remark=" + remark
				+ ", state=" + state + "]";
	}   
    
}
