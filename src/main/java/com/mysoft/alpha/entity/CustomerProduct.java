package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 客户-企业-产品订单(CustomerProduct)实体类
 *
 * @author makejava
 * @since 2020-08-02 16:13:52
 */
@Entity
@Table(name = "customer_product")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class CustomerProduct implements Serializable {
    private static final long serialVersionUID = 366306108075287561L;
    /**
     * 客户-企业-产品订单主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 客户主体ID，这里是客户id
     */
    @Column(name = "customer_subject_id")
    private Integer customerSubjectId;
    /**
     * 提供产品ID，保险产品
     */
    @Column(name = "product_id")
    private Integer productId;
    /**
     * 开始时间
     */
    @Column(name = "effective_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date effectiveDate;
    /**
     * 结束时间
     */
    @Column(name = "closing_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date closingDate;
    /**
     * 订单标识,唯一，可能是保单号，服务单据号等，对应客户+产品
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 申请备注
     */
    @Column(name = "confirm_remark")
    private String confirmRemark;
    /**
     * 申请类型，1、申请（付费）企业excel，2，系统维护
     */
    @Column(name = "source_type")
    private Integer sourceType;
    /**
     * 触发ID，客户-产品excle主表ID，冗余
     */
    @Column(name = "source_id")
    private Integer sourceId;
    /**
     * 触发id，客户-产品excle明细ID, 之一
     */
    @Column(name = "source_detail_id")
    private Integer sourceDetailId;
    /**
     * 状态：1、已触发待申请，2 = 重新触发待申请，3 = 审核通过待付费，4=重新申请待审核，5 = 审核通过可付费，6 = 付费完成待收款，7 = 确认收款服务中，8 = 服务完成，9 = 服务完成且评价，-3=审核不通过，-5=未付费，-7=未收款
     */
    @Column(name = "state")
    private Integer state;
    /**
     * 付费次数
     */
    @Column(name = "pay_times")
    private Integer payTimes;
    /**
     * 操作员
     */
    @Column(name = "operator")
    private String operator;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 客户主体ID
     */
    @Transient
    private AlphaSubject customerSubject;
    /**
     * 产品ID
     */
    @Transient
    private Product product;

    /**
     * 触发ID，客户-产品excle主表ID，冗余
     */
    @Transient
    private CpExcelMst sourceMst;
    /**
     * 触发id，客户-产品excle明细ID, 之一
     */
    @Transient
    private CpExcelDetail sourceDetail;

    /**
     * 投诉
     */
    @Transient
    private List<Complaint> complaints;

    public CustomerProduct() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerSubjectId() {
        return customerSubjectId;
    }

    public void setCustomerSubjectId(Integer customerSubjectId) {
        this.customerSubjectId = customerSubjectId;
    }

    public AlphaSubject getCustomerSubject() {
        return customerSubject;
    }

    public void setCustomerSubject(AlphaSubject customerSubject) {
        this.customerSubject = customerSubject;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
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

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getSourceDetailId() {
        return sourceDetailId;
    }

    public void setSourceDetailId(Integer sourceDetailId) {
        this.sourceDetailId = sourceDetailId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPayTimes() {
        return payTimes;
    }

    public void setPayTimes(Integer payTimes) {
        this.payTimes = payTimes;
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


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CpExcelMst getSourceMst() {
        return sourceMst;
    }

    public void setSourceMst(CpExcelMst sourceMst) {
        this.sourceMst = sourceMst;
    }

    public CpExcelDetail getSourceDetail() {
        return sourceDetail;
    }

    public void setSourceDetail(CpExcelDetail sourceDetail) {
        this.sourceDetail = sourceDetail;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomerProduct{");
        sb.append("id=").append(id);
        sb.append(", customerSubjectId=").append(customerSubjectId);
        sb.append(", productId=").append(productId);
        sb.append(", effectiveDate=").append(effectiveDate);
        sb.append(", closingDate=").append(closingDate);
        sb.append(", outTradeNo='").append(outTradeNo).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", confirmRemark='").append(confirmRemark).append('\'');
        sb.append(", sourceType=").append(sourceType);
        sb.append(", sourceId=").append(sourceId);
        sb.append(", sourceDetailId=").append(sourceDetailId);
        sb.append(", state=").append(state);
        sb.append(", payTimes=").append(payTimes);
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", customerSubject=").append(customerSubject);
        sb.append(", product=").append(product);
        sb.append(", sourceMst=").append(sourceMst);
        sb.append(", sourceDetail=").append(sourceDetail);
        sb.append(", complaints=").append(complaints);
        sb.append('}');
        return sb.toString();
    }
}