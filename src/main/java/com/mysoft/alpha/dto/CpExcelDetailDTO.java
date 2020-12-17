package com.mysoft.alpha.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CpExcelDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * 险种代码
	 */
	private String insuranceCode;
	/**
	 * 险种名称
	 */
	private String insuranceName;
	/**
	 * 产品代码
	 */
	private String productCode;
	/**
	 * 产品名称，目前录入，验证生成产品表
	 */
	private String productName;
	/**
	 * 保单号
	 */
	private String outTradeNo;

	/**
	 * 起保时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date effectiveDate;
	/**
	 * 终保时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date closingDate;

	/**
	 * 证件类型
	 */
	private String customerType;

	/**
	 * 客户姓名
	 */
	private String customerName;

	/**
	 * 证件号码
	 */
	private String recordNumber;

	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 出生日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date birthday;
	/**
	 * 保单状态
	 */
	@Column(name = "insurance_state")
	private String insuranceState;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInsuranceCode() {
		return insuranceCode;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
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

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getInsuranceState() {
		return insuranceState;
	}

	public void setInsuranceState(String insuranceState) {
		this.insuranceState = insuranceState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CpExcelDetailDTO [id=" + id + ", insuranceCode=" + insuranceCode + ", insuranceName=" + insuranceName
				+ ", productCode=" + productCode + ", productName=" + productName + ", outTradeNo=" + outTradeNo
				+ ", effectiveDate=" + effectiveDate + ", closingDate=" + closingDate + ", customerType=" + customerType
				+ ", customerName=" + customerName + ", recordNumber=" + recordNumber + ", sex=" + sex + ", birthday="
				+ birthday + ", insuranceState=" + insuranceState + "]";
	}

}
