package com.mysoft.alpha.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Table(name = "tabletest")
@Entity
public class Tabletest {
    @Column(name ="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "inttest",columnDefinition = " INT(11) NULL DEFAULT 1  COMMENT '测试数值'")
    private Integer inttest;
    @Column(name = "booleantest" )//bool 在数据库中更新有问题
        private Boolean booleantest;
    @Column(name ="sttest",columnDefinition = " VARCHAR(255) NULL DEFAULT '备注' COMMENT '测试文本'")
    @NotBlank()//只用于String,不能为null且trim()之后size>0
    private String sttest;
    @Column(name ="datetest" ,columnDefinition = " datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '测试时间'")
    private Date datetest;
}
