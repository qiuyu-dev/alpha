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
@Table(name = "batch_fee_detail")
//@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class BatchFeeDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
//	@Column(name = "batch_number")
//	private String batchNumber;//服务批号
	
	@Column(name = "ce_id")
	private int ceId;//客户企业id
	
	@Column(name = "cp_id")
	private String cpId;//客户_产品id数组
	
	@Column(name = "effective_number")
	private int effectiveNumber;//有效数
	
	@Column(name = "operator")
	private String operator;
	
	@Column(name = "create_time")
	private Date createTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "batch_fee_mst_id",insertable = false,updatable = false)
	private BatchFeeMst batchFeeMst;

	public BatchFeeDetail() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getBatchNumber() {
//		return batchNumber;
//	}
//
//	public void setBatchNumber(String batchNumber) {
//		this.batchNumber = batchNumber;
//	}

	public int getCeId() {
		return ceId;
	}

	public void setCeId(int ceId) {
		this.ceId = ceId;
	}

//	public String getCpIdArray() {
//		return cpIdArray;
//	}
//
//	public void setCpIdArray(String cpIdArray) {
//		this.cpIdArray = cpIdArray;
//	}

	public int getEffectiveNumber() {
		return effectiveNumber;
	}

	public void setEffectiveNumber(int effectiveNumber) {
		this.effectiveNumber = effectiveNumber;
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

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public BatchFeeMst getBatchFeeMst() {
		return batchFeeMst;
	}

	public void setBatchFeeMst(BatchFeeMst batchFeeMst) {
		this.batchFeeMst = batchFeeMst;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("BatchFeeDetail{");
		sb.append("id=").append(id);
		sb.append(", ceId=").append(ceId);
		sb.append(", cpId='").append(cpId).append('\'');
		sb.append(", effectiveNumber=").append(effectiveNumber);
		sb.append(", operator='").append(operator).append('\'');
		sb.append(", createTime=").append(createTime);
		sb.append(", batchFeeMst=").append(batchFeeMst);
		sb.append('}');
		return sb.toString();
	}
}
