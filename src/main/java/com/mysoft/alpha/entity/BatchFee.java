package com.mysoft.alpha.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "batch_fee")
@ToString
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
	
	
	
}
