package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


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
@Table(name = "cp_excel_mst")
//@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class CustomerProductExcelMst implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "from_type")
    private int fromType;
    
    @Column(name = "from_id")
    private int fromId;
    
    @Column(name = "to_type")
    private int toType;
    
    @Column(name = "to_id")
    private int toId;
    
    @Column(name = "file_Name")
    private String fileName; 
    
    @Column(name = "ctype")
    private int ctype;
    
    @Column(name = "seq_number")
    private int seqNumber;

    @Column(name="status")
    private String status;//状态

    @Column(name = "remark")
    private String remark;
    
    @Column(name = "operator")
    private String operator;
    
    @Column(name = "create_time")
    private Date createTime;

    @OneToMany(cascade = CascadeType.ALL,
               mappedBy = "cpExcelMst", orphanRemoval = true)
    private List<CustomerProductExcelDetail> cpExcelDetails;


    public CustomerProductExcelMst() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromType() {
        return fromType;
    }

    public void setFromType(int fromType) {
        this.fromType = fromType;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToType() {
        return toType;
    }

    public void setToType(int toType) {
        this.toType = toType;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getCtype() {
        return ctype;
    }

    public void setCtype(int ctype) {
        this.ctype = ctype;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<CustomerProductExcelDetail> getCpExcelDetails() {
        return cpExcelDetails;
    }

    public void setCpExcelDetails(List<CustomerProductExcelDetail> cpExcelDetails) {
        this.cpExcelDetails = cpExcelDetails;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomerProductExcelMst{");
        sb.append("id=").append(id);
        sb.append(", fromType=").append(fromType);
        sb.append(", fromId=").append(fromId);
        sb.append(", toType=").append(toType);
        sb.append(", toId=").append(toId);
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", ctype=").append(ctype);
        sb.append(", seqNumber=").append(seqNumber);
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", cpExcelDetails=").append(cpExcelDetails);
        sb.append('}');
        return sb.toString();
    }
}
