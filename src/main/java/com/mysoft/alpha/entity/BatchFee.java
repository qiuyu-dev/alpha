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
@Table(name = "batch_fee")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class BatchFee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@Column(name = "batch_number")
	private String batchNumber;//批号 P
	
	@Column(name = "ftype")
	private int ftype;//类型 1付费 -1 扣费

	@Column(name = "pay_type")
	private int payType;//付费来源类型
	
	@Column(name = "pay_id")
	private int payId;//付费来源
	
	@Column(name = "charge_type")
	private int chargeType;//收费来源类型
	
	@Column(name = "chage_id")
	private int chargeId;//收费来源id
	
	@Column(name = "begin_time")
	private Date beginTime;
	
	@Column(name = "end_time")
	private Date endTime;
	
	@Column(name = "btype")
	private int btype;//付费类型 1客户-公司 2 客户-产品
	
	@Column(name = "effective_number")
	private int effectiveNumber;//有效数
	
	@Column(name = "price")
	private int price;//单价
	
	@Column(name = "prepayment")
	private int prepayment;//应付款
	
	@Column(name = "receivable")
	private int receivable;//应收款
	
	@Column(name = "pay_img")
	private String payImg;//付费凭证图片
	
	@Column(name = "pay_time")
	private Date payTime;//付费时间
	
	@Column(name = "remark")
	private String remark;//备注
	
	@Column(name = "seq_number")
	private int seqNumber;//顺序号
	
	@Column(name = "operator")
	private String operator;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "status")
	private int status;//状态
	
	@Column(name = "confirm_remark")
	private String confirmRemark;//确认备注

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public int getFtype() {
		return ftype;
	}

	public void setFtype(int ftype) {
		this.ftype = ftype;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public int getChargeType() {
		return chargeType;
	}

	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}

	public int getChargeId() {
		return chargeId;
	}

	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
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

	public int getBtype() {
		return btype;
	}

	public void setBtype(int btype) {
		this.btype = btype;
	}

	public int getEffectiveNumber() {
		return effectiveNumber;
	}

	public void setEffectiveNumber(int effectiveNumber) {
		this.effectiveNumber = effectiveNumber;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(int prepayment) {
		this.prepayment = prepayment;
	}

	public int getReceivable() {
		return receivable;
	}

	public void setReceivable(int receivable) {
		this.receivable = receivable;
	}

	public String getPayImg() {
		return payImg;
	}

	public void setPayImg(String payImg) {
		this.payImg = payImg;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getConfirmRemark() {
		return confirmRemark;
	}

	public void setConfirmRemark(String confirmRemark) {
		this.confirmRemark = confirmRemark;
	}

	@Override
	public String toString() {
		return "BatchFee [id=" + id + ", batchNumber=" + batchNumber + ", ftype=" + ftype + ", payType=" + payType
				+ ", payId=" + payId + ", chargeType=" + chargeType + ", chargeId=" + chargeId + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", btype=" + btype + ", effectiveNumber=" + effectiveNumber
				+ ", price=" + price + ", prepayment=" + prepayment + ", receivable=" + receivable + ", payImg="
				+ payImg + ", payTime=" + payTime + ", remark=" + remark + ", seqNumber=" + seqNumber + ", operator="
				+ operator + ", createTime=" + createTime + ", status=" + status + ", confirmRemark=" + confirmRemark
				+ "]";
	}
	
	
	
	
	
}
