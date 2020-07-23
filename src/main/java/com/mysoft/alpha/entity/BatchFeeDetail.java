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
@Table(name = "batch_fee_detail")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class BatchFeeDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@Column(name = "batch_number")
	private String batchNumber;//服务批号
	
	@Column(name = "ce_id")
	private int ceId;//客户企业id
	
	@Column(name = "cp_id_array")
	private String cpIdArray;//客户_产品id数组
	
	@Column(name = "effective_number")
	private int effectiveNumber;//有效数
	
	@Column(name = "operator")
	private String operator;
	
	@Column(name = "create_time")
	private Date createTime;

}
