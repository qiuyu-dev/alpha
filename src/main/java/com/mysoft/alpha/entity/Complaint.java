package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "complaint")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Complaint implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "cp_id")
    private int cpId;

    @Column(name = "ctype")
    private int ctype;

    @Column(name = "remark")
    private String remark;

    @Column(name = "seq_number")
    private String seqNumber;

    @Column(name = "operator")
    private String operator;

    @Column(name = "create_time")
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCpId() {
        return cpId;
    }

    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    public int getCtype() {
        return ctype;
    }

    public void setCtype(int ctype) {
        this.ctype = ctype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(String seqNumber) {
        this.seqNumber = seqNumber;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Complaint{");
        sb.append("id=").append(id);
        sb.append(", cpId=").append(cpId);
        sb.append(", ctype=").append(ctype);
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", seqNumber='").append(seqNumber).append('\'');
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
