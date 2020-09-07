package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 产品(Product)实体类
 *
 * @author makejava
 * @since 2020-08-02 16:14:08
 */
@Entity
@Table(name = "product")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Product implements Serializable {
    private static final long serialVersionUID = -49157486065965398L;
    /**
     * 产品主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 产品全称
     */
    @Column(name = "name")
    private String name;
    /**
     * 企业主体id
     */
    @Column(name = "alpha_subject_id")
    private Integer alphaSubjectId;
    /**
     * 1、普通，2、保险，3、保险配套服务
     */
    @Column(name = "product_type")
    private Integer productType;
    /**
     * 备案编号，唯一
     */
    @Column(name = "record_number")
    private String recordNumber;
    /**
     * 顺序号，排序用
     */
    @Column(name = "ordered")
    private Integer ordered;
    /**
     * 触发类型，1付费企业excel，2，系统维护 3.API上传
     */
    @Column(name = "source_type")
    private Integer sourceType;
    /**
     * 触发id，1、cp_excel_mst_id，2null
     */
    @Column(name = "source_id")
    private Integer sourceId;
    /**
     * 触发id，1、cp_excel_detail_id，2null
     */
    @Column(name = "source_detail_id")
    private Integer sourceDetailId;
    /**
     * 是否可用，0不可用，1可用
     */
    @Column(name = "enabled")
    private Integer enabled;
    /**
     * 操作员
     */
    @Column(name = "operator")
    private String operator;
    /**
     * 创建时间
     */
    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Transient
    private AlphaSubject alphaSubject;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAlphaSubjectId() {
        return alphaSubjectId;
    }

    public void setAlphaSubjectId(Integer alphaSubjectId) {
        this.alphaSubjectId = alphaSubjectId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
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

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
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

    public AlphaSubject getAlphaSubject() {
        return alphaSubject;
    }

    public void setAlphaSubject(AlphaSubject alphaSubject) {
        this.alphaSubject = alphaSubject;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", alphaSubjectId=").append(alphaSubjectId);
        sb.append(", productType=").append(productType);
        sb.append(", recordNumber='").append(recordNumber).append('\'');
        sb.append(", ordered=").append(ordered);
        sb.append(", sourceType=").append(sourceType);
        sb.append(", sourceId=").append(sourceId);
        sb.append(", sourceDetailId=").append(sourceDetailId);
        sb.append(", enabled=").append(enabled);
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", alphaSubject=").append(alphaSubject);
        sb.append('}');
        return sb.toString();
    }
}