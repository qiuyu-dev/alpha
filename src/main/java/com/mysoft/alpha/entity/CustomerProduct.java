package com.mysoft.alpha.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "customer_product")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class CustomerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "cc_id")
    private int ccId;
    
    @Column(name = "company_id")
    private int companyId;
    
    @Column(name = "product_id")
    private int productId;
    
    @Column(name = "policy_number")
    private String policyNumber;
    
    @Column(name = "begin_time")
    private Date beginTime;
    
    @Column(name = "end_time")
    private Date endTime;
    
    @Column(name = "status")
    private int status;
    
    @Column(name = "seq_number")
    private int seqNumber;
    
    @Column(name = "from_type")
    private int fromType;
    
    @Column(name = "from_id")
    private int fromId;
    
    @Column(name  = "remark")
    private String remark;
    
    @Column(name = "operator")
    private String operator;
    
    @Column(name = "create_time")
    private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCcId() {
		return ccId;
	}

	public void setCcId(int ccId) {
		this.ccId = ccId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return "CustomerProduct [id=" + id + ", ccId=" + ccId + ", companyId=" + companyId + ", productId=" + productId
				+ ", policyNumber=" + policyNumber + ", beginTime=" + beginTime + ", endTime=" + endTime + ", status="
				+ status + ", seqNumber=" + seqNumber + ", fromType=" + fromType + ", fromId=" + fromId + ", remark="
				+ remark + ", operator=" + operator + ", createTime=" + createTime + "]";
	}
    
}
