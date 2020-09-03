package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 批次付费明细(BatchFeeDetail)实体类
 *
 * @author makejava
 * @since 2020-08-09 15:38:34
 */
@Entity
@Table(name = "batch_fee_detail")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class BatchFeeDetail implements Serializable {
	private static final long serialVersionUID = 101413331465179121L;
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	/**
	 * 批次付费主表id
	 */
	@Column(name = "batch_fee_mst_id")
	private Integer batchFeeMstId;
	/**
	 * 客户主体ID，这里是客户id
	 */
	@Column(name = "customer_subject_id")
	private Integer customerSubjectId;
	/**
	 * 申请类型，1.申请（付费）企业excel，2.系统维护, 3.API上传
	 */
	@Column(name = "source_type")
	private Integer sourceType;
	/**
	 * 触发id，客户-产品excle明细ID, 之一
	 */
	@Column(name = "source_detail_id")
	private Integer sourceDetailId;
	/**
	 * 有效数，一般是1，有多次投诉可能是多个，按客户_企业计费时取一个客户_产品ID为1，其他为0
	 */
	@Column(name = "effective_number")
	private Integer effectiveNumber;
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
	 * 保险明细
	 */
	@Transient
	private CpExcelDetail cpExcelDetail;

	public BatchFeeDetail() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBatchFeeMstId() {
		return batchFeeMstId;
	}

	public void setBatchFeeMstId(Integer batchFeeMstId) {
		this.batchFeeMstId = batchFeeMstId;
	}

	public Integer getCustomerSubjectId() {
		return customerSubjectId;
	}

	public void setCustomerSubjectId(Integer customerSubjectId) {
		this.customerSubjectId = customerSubjectId;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getSourceDetailId() {
		return sourceDetailId;
	}

	public void setSourceDetailId(Integer sourceDetailId) {
		this.sourceDetailId = sourceDetailId;
	}

	public Integer getEffectiveNumber() {
		return effectiveNumber;
	}

	public void setEffectiveNumber(Integer effectiveNumber) {
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

	public CpExcelDetail getCpExcelDetail() {
		return cpExcelDetail;
	}

	public void setCpExcelDetail(CpExcelDetail cpExcelDetail) {
		this.cpExcelDetail = cpExcelDetail;
	}
}