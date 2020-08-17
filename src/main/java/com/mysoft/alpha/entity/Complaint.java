package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 投诉(Complaint)实体类
 *
 * @author makejava
 * @since 2020-08-02 16:13:56
 */
@Entity
@Table(name = "complaint")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Complaint implements Serializable {
    private static final long serialVersionUID = -71526186669814520L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 客户-企业-产品订单ID
     */
    @Column(name = "customer_product_id")
    private Integer customerProductId;
    /**
     * 类型,1、不减扣服务费、2、减扣服务费
     */
    @Column(name = "complaint_type")
    private Integer complaintType;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
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
     * 所属企业
     */

    @Transient
    private CustomerProduct customerProduct;

    public Complaint() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(Integer complaintType) {
        this.complaintType = complaintType;
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

    public Integer getCustomerProductId() {
        return customerProductId;
    }

    public void setCustomerProductId(Integer customerProductId) {
        this.customerProductId = customerProductId;
    }

    public CustomerProduct getCustomerProduct() {
        return customerProduct;
    }

    public void setCustomerProduct(CustomerProduct customerProduct) {
        this.customerProduct = customerProduct;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Complaint{");
        sb.append("id=").append(id);
        sb.append(", customerProductId=").append(customerProductId);
        sb.append(", complaintType=").append(complaintType);
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", customerProduct=").append(customerProduct);
        sb.append('}');
        return sb.toString();
    }
}