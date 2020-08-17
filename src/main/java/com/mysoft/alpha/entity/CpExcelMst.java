package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 客户-产品Excel主表(CpExcelMst)实体类
 *
 * @author makejava
 * @since 2020-08-02 16:14:04
 */
@Entity
@Table(name = "cp_excel_mst")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class CpExcelMst implements Serializable {
    private static final long serialVersionUID = -77178677743563069L;
    /**
     * 客户-产品Excel主表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
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
     * 文件名，唯一
     */
    @Column(name = "file_name")
    private String fileName;
    /**
     * 文件存储url
     */
    @Column(name = "url")
    private String url;
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
     * 客户-企业-产品订单明细
     */
    @Transient
    private List<CpExcelDetail> cpExcelDetails;

    public CpExcelMst() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaySubjectId() {
        return paySubjectId;
    }

    public void setPaySubjectId(Integer paySubjectId) {
        this.paySubjectId = paySubjectId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public AlphaSubject getPaySubject() {
        return paySubject;
    }

    public void setPaySubject(AlphaSubject paySubject) {
        this.paySubject = paySubject;
    }

    public Integer getChargeSubjectId() {
        return chargeSubjectId;
    }

    public void setChargeSubjectId(Integer chargeSubjectId) {
        this.chargeSubjectId = chargeSubjectId;
    }

    public AlphaSubject getChargeSubject() {
        return chargeSubject;
    }

    public void setChargeSubject(AlphaSubject chargeSubject) {
        this.chargeSubject = chargeSubject;
    }

    public List<CpExcelDetail> getCpExcelDetails() {
        return cpExcelDetails;
    }

    public void setCpExcelDetails(List<CpExcelDetail> cpExcelDetails) {
        this.cpExcelDetails = cpExcelDetails;
    }




}