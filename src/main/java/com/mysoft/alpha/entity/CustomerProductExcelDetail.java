package com.mysoft.alpha.entity;

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

//@Data
@Entity
@Table(name = "cp_excel_detail")
//@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class CustomerProductExcelDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

	@Column(name = "row_num")
    private int rowNum;//excel 行号
    
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
    
    @Column(name="explanation")
    private String explanation;//系统说明
    
    @Column(name="status")
    private String status;//状态1、触发，2、已申请，3、重新触发，4、重新申请 、5、审核通过，6、确认，7、提供中，8、完成，9、评价，-1、失败，-5审核未通过（目前没有1，6，7）
    
    @Column(name="create_time")
    private Date createTime;//创建时间
    
    @Column(name="operator")
    private String operator;// 操作者

	@Column(name="cp_excel_mst_id")
	private int cpExcelMstId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cp_excel_mst_id",insertable = false,updatable = false)
     private CustomerProductExcelMst cpExcelMst;
    
    @Column(name="customer_id")
    private int customerId;//客户id
    
    @Column(name="company_id")
    private int companyId;//企业id
    
    @Column(name="product_id")
    private int productId;//产品id

	public CustomerProductExcelDetail() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public CustomerProductExcelMst getCpExcelMst() {
		return cpExcelMst;
	}

	public void setCpExcelMst(CustomerProductExcelMst cpExcelMst) {
		this.cpExcelMst = cpExcelMst;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public int getCpExcelMstId() {
		return cpExcelMstId;
	}

	public void setCpExcelMstId(int cpExcelMstId) {
		this.cpExcelMstId = cpExcelMstId;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("CustomerProductExcelDetail{");
		sb.append("id=").append(id);
		sb.append(", rowNum=").append(rowNum);
		sb.append(", seqNumber=").append(seqNumber);
		sb.append(", policyNumber='").append(policyNumber).append('\'');
		sb.append(", product='").append(product).append('\'');
		sb.append(", insuredName='").append(insuredName).append('\'');
		sb.append(", certificateType='").append(certificateType).append('\'');
		sb.append(", phonenum='").append(phonenum).append('\'');
		sb.append(", insuredId='").append(insuredId).append('\'');
		sb.append(", effectiveDate=").append(effectiveDate);
		sb.append(", closingDate=").append(closingDate);
		sb.append(", sex='").append(sex).append('\'');
		sb.append(", age=").append(age);
		sb.append(", location='").append(location).append('\'');
		sb.append(", remark='").append(remark).append('\'');
		sb.append(", explanation='").append(explanation).append('\'');
		sb.append(", status=").append(status);
		sb.append(", createTime=").append(createTime);
		sb.append(", operator='").append(operator).append('\'');
		sb.append(", cpExcelMstId=").append(cpExcelMstId);
		sb.append(", cpExcelMst=").append(cpExcelMst);
		sb.append(", customerId=").append(customerId);
		sb.append(", companyId=").append(companyId);
		sb.append(", productId=").append(productId);
		sb.append('}');
		return sb.toString();
	}
}
