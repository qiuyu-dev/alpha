package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 客户-产品excle明细(CpExcelDetail)实体类
 *
 * @author makejava
 * @since 2020-08-02 16:14:01
 */
@Entity
@Table(name = "cp_excel_detail")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class CpExcelDetail implements Serializable {
    private static final long serialVersionUID = -57962639973242440L;
    /**
     * 客户-产品excle明细主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 客户-产品excle主表ID
     */
    @Column(name = "cp_excel_mst_id")
    private Integer cpExcelMstId;
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
     * 订单标识,唯一，可能是保单号，服务单据号等，对应客户+产品+时间范围
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 行号，excel从第2行记录开始,排序
     */
    @Column(name = "ordered")
    private Integer ordered;
    /**
     * 序号，文件内容
     */
    @Column(name = "seq_number")
    private String seqNumber;
    /**
     * 证件类型名称,1、身份证，2、军官证,驾驶证，3、新生儿出生证明，4、社会保险卡号，冗余
     */
    @Column(name = "customer_type")
    private String customerType;

    /**
     * 客户姓名，冗余信息
     */
    @Column(name = "customer_name")
    private String customerName;
    /**
     * 联系电话
     */
    @Column(name = "customer_phone")
    private String customerPhone;
    /**
     * 产品名称，目前录入，验证生成产品表
     */
    @Column(name = "product_name")
    private String productName;
    /**
     * 所在地,可以录入或来源于身份证号
     */
    @Column(name = "location")
    private String location;
    /**
     * 年龄,可以录入或来源于身份证号
     */
    @Column(name = "age")
    private String age;
    /**
     * 性别,1男，2女，可以录入或来源于身份证号
     */
    @Column(name = "sex")
    private String sex;
    /**
     * 状态：3 = 申请通过待审核，4=重新申请待审核，5 = 审核通过可付费，-3=审核不通过，整体作废
     */
    @Column(name = "state")
    private Integer state;
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
     * 客户主体
     */

    @Transient
    private AlphaSubject customerSubject;

    /**
     * 产品
     */

    @Transient
    private Product product;


    /**
     * 主表
     */

    @Transient
    private CpExcelMst cpExcelMst;

    public CpExcelDetail() {
    }

    /**
     * 触发的服务
     */

    @Transient
    private List<CustomerProduct> customerProducts;


    /**
     * 付费主表
     */

    @Transient
    private List<BatchFeeMst> batchFeeMsts;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCpExcelMstId() {
        return cpExcelMstId;
    }

    public void setCpExcelMstId(Integer cpExcelMstId) {
        this.cpExcelMstId = cpExcelMstId;
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

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public String getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(String seqNumber) {
        this.seqNumber = seqNumber;
    }

  public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CpExcelMst getCpExcelMst() {
        return cpExcelMst;
    }

    public void setCpExcelMst(CpExcelMst cpExcelMst) {
        this.cpExcelMst = cpExcelMst;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCustomerSubjectId() {
        return customerSubjectId;
    }

    public void setCustomerSubjectId(Integer customerSubjectId) {
        this.customerSubjectId = customerSubjectId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public AlphaSubject getCustomerSubject() {
        return customerSubject;
    }

    public void setCustomerSubject(AlphaSubject customerSubject) {
        this.customerSubject = customerSubject;
    }

    public List<CustomerProduct> getCustomerProducts() {
        return customerProducts;
    }

    public void setCustomerProducts(List<CustomerProduct> customerProducts) {
        this.customerProducts = customerProducts;
    }


    public List<BatchFeeMst> getBatchFeeMsts() {
        return batchFeeMsts;
    }

    public void setBatchFeeMsts(List<BatchFeeMst> batchFeeMsts) {
        this.batchFeeMsts = batchFeeMsts;
    }
}