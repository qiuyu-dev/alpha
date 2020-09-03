package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 主体(AlphaSubject)实体类
 *
 * @author makejava
 * @since 2020-08-02 16:13:35
 */
@Entity
@Table(name = "alpha_subject")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AlphaSubject implements Serializable {
    private static final long serialVersionUID = 973360386887013534L;
    /**
     * 主体主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 1、客户，2、保险企业，3、服务企业
     */
    @Column(name = "subject_type")
    private Integer subjectType;
    /**
     * 证件类型,1、身份证，2、军官证,驾驶证，3、新生儿出生证明，4、社会保险卡号，,10企业机构代码，
     */
    @Column(name = "record_type")
    private String recordType;
    /**
     * 组织机构代码，验证，唯一，或者身份证或其他证件好
     */
    @Column(name = "record_number")
    private String recordNumber;
    /**
     * 企业名称，或客户姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 所在地,可以录入或来源于身份证号
     */
    @Column(name = "location")
    private String location;
    /**
     * 年龄,可以录入或来源于身份证号
     */
    @Column(name = "age")
    private Integer age;
    /**
     * 性别,1男，2女，可以录入或来源于身份证号
     */
    @Column(name = "sex")
    private String sex;
    /**
     * 联系电话
     */
    @Column(name = "phone")
    private String phone;
    /**
     * 触发类型，1.付费企业excel，2.系统维护, 3.API上传
     */
    @Column(name = "source_type")
    private Integer sourceType;
    /**
     * 触发id，1、cp_excel_detail_id，2null
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
    
    /**
     * 客户-企业-产品订单明细
     */
    @Transient
    private List<CpExcelDetail> cpExcelDetails;


    public AlphaSubject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(Integer subjectType) {
        this.subjectType = subjectType;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

	public List<CpExcelDetail> getCpExcelDetails() {
		return cpExcelDetails;
	}

	public void setCpExcelDetails(List<CpExcelDetail> cpExcelDetails) {
		this.cpExcelDetails = cpExcelDetails;
	}
    
    
}