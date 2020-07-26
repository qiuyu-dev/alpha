package com.mysoft.alpha.model;

import java.util.Date;

public class CPExcelForm {

	private int mstId;
	private int detailId;
	private int rowNum;// excel 行号
	private String seqNumber;// 序号
	private String policyNumber;// 保单号
	private String product;// 产品
	private String insuredName;// 被保险人姓名
	private String certificateType;// 证件类型 1 身份证 2 护照
	private String phone;// 电话号码
	private String insuredId;// 被保险人证件号
	private Date effectiveDate;// 生效日
	private Date closingDate;// 截止日

	private String sex;// 性别 1男 2女

	private int age;// 年龄
	private String location;// 所在地
	private String remark;// 备注

	private String explanation;// 系统说明

	private String status;// 状态1、触发，2、已申请，3、重新触发，4、重新申请
							// 、5、审核通过，6、确认，7、提供中，8、完成，9、评价，-1、失败，-5审核未通过（目前没有1，6，7）

	private Date createTime;// 创建时间

	private String operator;// 操作者

	private int fromType;
	private int fromId;
	private int toType;
	private int toId;
	private String fileName;
	private int ctype;

	public CPExcelForm() {
	}

	public CPExcelForm(int mstId, int detailId, String seqNumber, String policyNumber, String product, String insuredName,
			String certificateType, String phone, String insuredId, Date effectiveDate, Date closingDate, String remark,
			String explanation, String status, Date createTime, String operator, int toType, int toId,
			String fileName) {
		this.mstId = mstId;
		this.detailId = detailId;
		this.seqNumber = seqNumber;
		this.policyNumber = policyNumber;
		this.product = product;
		this.insuredName = insuredName;
		this.certificateType = certificateType;
		this.phone = phone;
		this.insuredId = insuredId;
		this.effectiveDate = effectiveDate;
		this.closingDate = closingDate;
		this.remark = remark;
		this.explanation = explanation;
		this.status = status;
		this.createTime = createTime;
		this.operator = operator;
		this.toType = toType;
		this.toId = toId;
		this.fileName = fileName;
	}

	public int getMstId() {
		return mstId;
	}

	public void setMstId(int mstId) {
		this.mstId = mstId;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

	public int getToType() {
		return toType;
	}

	public void setToType(int toType) {
		this.toType = toType;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getCtype() {
		return ctype;
	}

	public void setCtype(int ctype) {
		this.ctype = ctype;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("CPExcelForm{");
		sb.append("mstId=").append(mstId);
		sb.append(", detailId=").append(detailId);
		sb.append(", rowNum=").append(rowNum);
		sb.append(", seqNumber=").append(seqNumber);
		sb.append(", policyNumber='").append(policyNumber).append('\'');
		sb.append(", product='").append(product).append('\'');
		sb.append(", insuredName='").append(insuredName).append('\'');
		sb.append(", certificateType='").append(certificateType).append('\'');
		sb.append(", phone='").append(phone).append('\'');
		sb.append(", insuredId='").append(insuredId).append('\'');
		sb.append(", effectiveDate=").append(effectiveDate);
		sb.append(", closingDate=").append(closingDate);
		sb.append(", sex='").append(sex).append('\'');
		sb.append(", age=").append(age);
		sb.append(", location='").append(location).append('\'');
		sb.append(", remark='").append(remark).append('\'');
		sb.append(", explanation='").append(explanation).append('\'');
		sb.append(", status='").append(status).append('\'');
		sb.append(", createTime=").append(createTime);
		sb.append(", operator='").append(operator).append('\'');
		sb.append(", fromType=").append(fromType);
		sb.append(", fromId=").append(fromId);
		sb.append(", toType=").append(toType);
		sb.append(", toId=").append(toId);
		sb.append(", fileName='").append(fileName).append('\'');
		sb.append(", ctype=").append(ctype);
		sb.append('}');
		return sb.toString();
	}
}
