package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Product implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "record_number")
    private String recordNumber;

    @Column(name = "product")
    private String product;

    @Column(name = "ptype")
    private int ptype;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPtype() {
        return ptype;
    }

    public void setPtype(int ptype) {
        this.ptype = ptype;
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
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("id=").append(id);
        sb.append(", company=").append(company);
        sb.append(", recordNumber='").append(recordNumber).append('\'');
        sb.append(", product='").append(product).append('\'');
        sb.append(", ptype=").append(ptype);
        sb.append(", seqNumber='").append(seqNumber).append('\'');
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
