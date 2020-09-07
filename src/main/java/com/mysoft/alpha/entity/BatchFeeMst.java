package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 批次付费主表(BatchFeeMst)实体类
 *
 * @author makejava
 * @since 2020-08-09 15:38:44
 */
@Entity
@Table(name = "batch_fee_mst")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class BatchFeeMst implements Serializable {
    private static final long serialVersionUID = 417076524580282839L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 服务批号，P_组织机构代码_YYYYMMDD_序号2位
     */
    @Column(name = "batch_number")
    private String batchNumber;
    /**
     * 付费主体ID，Excel上传企业，这里是保险企业
     */
    @Column(name = "pay_subject_id")
    private Integer paySubjectId;
    /**
     * 提供主体ID，选择的企业，这里是服务企业，冗余
     */
    @Column(name = "charge_subject_id")
    private Integer chargeSubjectId;
    /**
     * 开始时间
     */
    @Column(name = "effective_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date effectiveDate;
    /**
     * 结束时间，默认为开始时间后一月
     */
    @Column(name = "closing_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date closingDate;
    /**
     * 1、按客户付费、2按客户产品
     */
    @Column(name = "pay_type")
    private Integer payType;
    /**
     * 1收款，-1退款
     */
    @Column(name = "charge_type")
    private Integer chargeType;
    /**
     * 有效数
     */
    @Column(name = "effective_number")
    private Integer effectiveNumber;
    /**
     * 单价
     */
    @Column(name = "price")
    private Integer price;
    /**
     * 预付款
     */
    @Column(name = "prepayment")
    private Integer prepayment;
    /**
     * 应收款
     */
    @Column(name = "receivable")
    private Integer receivable;
    /**
     * 付费凭证图片
     */
    @Column(name = "img")
    private String img;
    /**
     * 图片存储url
     */
    @Column(name = "url")
    private String url;
    /**
     * 付费时间
     */
    @Column(name = "pay_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;
    /**
     * 收款时间
     */
    @Column(name = "charge_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date chargeTime;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 确认备注
     */
    @Column(name = "confirm_remark")
    private String confirmRemark;
    /**
     * 状态
     */
    @Column(name = "state")
    private Integer state;
    /**
     * 操作员
     */
    @Column(name = "operator")
    private String operator;
    /**
     * 收款员
     */
    @Column(name = "cashier")
    private String cashier;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 支付主体
     */

    @Transient
    private AlphaSubject paySubject;

    /**
     * 提供（收款）企业
     */

    @Transient
    private AlphaSubject chargeSubject;

    /**
     * 明细
     */
    @Transient
    private List<BatchFeeDetail> batchFeeDetails;


    /**
     * 状态说明
     */

    @Transient
    private String stateReason;

    public BatchFeeMst() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Integer getPaySubjectId() {
        return paySubjectId;
    }

    public void setPaySubjectId(Integer paySubjectId) {
        this.paySubjectId = paySubjectId;
    }

    public Integer getChargeSubjectId() {
        return chargeSubjectId;
    }

    public void setChargeSubjectId(Integer chargeSubjectId) {
        this.chargeSubjectId = chargeSubjectId;
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

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public Integer getEffectiveNumber() {
        return effectiveNumber;
    }

    public void setEffectiveNumber(Integer effectiveNumber) {
        this.effectiveNumber = effectiveNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(Integer prepayment) {
        this.prepayment = prepayment;
    }

    public Integer getReceivable() {
        return receivable;
    }

    public void setReceivable(Integer receivable) {
        this.receivable = receivable;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Date chargeTime) {
        this.chargeTime = chargeTime;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<BatchFeeDetail> getBatchFeeDetails() {
        return batchFeeDetails;
    }

    public void setBatchFeeDetails(List<BatchFeeDetail> batchFeeDetails) {
        this.batchFeeDetails = batchFeeDetails;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    public AlphaSubject getPaySubject() {
        return paySubject;
    }

    public void setPaySubject(AlphaSubject paySubject) {
        this.paySubject = paySubject;
    }

    public AlphaSubject getChargeSubject() {
        return chargeSubject;
    }

    public void setChargeSubject(AlphaSubject chargeSubject) {
        this.chargeSubject = chargeSubject;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BatchFeeMst{");
        sb.append("id=").append(id);
        sb.append(", batchNumber='").append(batchNumber).append('\'');
        sb.append(", paySubjectId=").append(paySubjectId);
        sb.append(", chargeSubjectId=").append(chargeSubjectId);
        sb.append(", effectiveDate=").append(effectiveDate);
        sb.append(", closingDate=").append(closingDate);
        sb.append(", payType=").append(payType);
        sb.append(", chargeType=").append(chargeType);
        sb.append(", effectiveNumber=").append(effectiveNumber);
        sb.append(", price=").append(price);
        sb.append(", prepayment=").append(prepayment);
        sb.append(", receivable=").append(receivable);
        sb.append(", img='").append(img).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", payTime=").append(payTime);
        sb.append(", chargeTime=").append(chargeTime);
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", confirmRemark='").append(confirmRemark).append('\'');
        sb.append(", state=").append(state);
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", cashier='").append(cashier).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", paySubject=").append(paySubject);
        sb.append(", chargeSubject=").append(chargeSubject);
        sb.append(", batchFeeDetails=").append(batchFeeDetails);
        sb.append(", stateReason='").append(stateReason).append('\'');
        sb.append('}');
        return sb.toString();
    }
}