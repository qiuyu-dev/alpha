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
@Table(name = "cp_excel_mst")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class CustomerProductExcelMst {
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
    
    @Column(name = "remark")
    private String remark;
    
    @Column(name = "operator")
    private String operator;
    
    @Column(name = "create_time")
    private Date createTime;
        

}
