package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_product")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class CustomerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "company_id")
    private int companyId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "policy_number")
    private String policyNumber;//订单号


    @Column(name = "name")
    private String name;//姓名

    @Column(name = "certificate_type")
    private String certificateType;//证件类型 1  身份证 2 护照

    @Column(name = "insured_id")
    private String insuredId;//证件号

    @Column(name = "phone ")
    private String phone;//电话号码


    @Column(name = "effective_date")
    private Date effectiveDate;

    @Column(name = "closing_date")
    private Date closingDate;

    @Column(name = "status")
    private String status;

    @Column(name = "seq_number")
    private int seqNumber;

    @Column(name = "from_type")
    private int fromType;

    @Column(name = "from_id")
    private int fromId;

    @Column(name = "to_type")
    private int toType;

    @Column(name = "to_id")
    private int toId;

    @Column(name = "remark")
    private String remark;

    @Column(name = "operator")
    private String operator;

    @Column(name = "create_time")
    private Date createTime;

    public CustomerProduct() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        final StringBuffer sb = new StringBuffer("CustomerProduct{");
        sb.append("id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", productId=").append(productId);
        sb.append(", policyNumber='").append(policyNumber).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", certificateType='").append(certificateType).append('\'');
        sb.append(", insuredId='").append(insuredId).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", effectiveDate=").append(effectiveDate);
        sb.append(", closingDate=").append(closingDate);
        sb.append(", status='").append(status).append('\'');
        sb.append(", seqNumber=").append(seqNumber);
        sb.append(", fromType=").append(fromType);
        sb.append(", fromId=").append(fromId);
        sb.append(", toType=").append(toType);
        sb.append(", toId=").append(toId);
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
