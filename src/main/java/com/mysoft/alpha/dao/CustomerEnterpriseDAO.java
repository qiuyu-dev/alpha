package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.CustomerEnterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public interface CustomerEnterpriseDAO extends JpaRepository<CustomerEnterprise, Integer> {

    public List<CustomerEnterprise>  findByEidAndCestatusInOrderByIdDesc(int eid,List<Integer> statusLis);

    public List<CustomerEnterprise>  findByFromIdAndCestatusInOrderByIdDesc(int fromId,List<Integer> statusLis);




//    public List<CustomerEnterprise> findByEidBeforeCestatusInOrderByIdDesc(int eid,List<Integer> statusList);
//    public List<CustomerEnterprise> findByFromIdBeforeCestatusInOrderByIdDesc(int eid,List<Integer> statusList);

    public List<CustomerEnterprise> findByEidOrderByIdDesc(int eid);

    public List<CustomerEnterprise> findByOperatorOrderByIdDesc(String operator);


    @Query(nativeQuery = true, value = "SELECT     `id`,    `cp_excel_mst_id`,    `row_num`,    `certificate_type`,\n" +
            "    `insured_id`,    `insured_name`,    `phonenum`,    `location`,\n" +
            "    `age`,    `sex`,    `company_id`,    `customer_id`,\n" +
            "    `product_id`,    `product`,    `policy_number`,    `effective_date`,\n" +
            "    `closing_date`,    `status`,    `seq_number`,    `remark`,\n" +
            "    `explanation`,    `operator`,    `create_time` \n" +
            "FROM\n" +
            "    cp_excel_detail\n" +
            "WHERE\n" +
            "    insured_id = :insured_id AND company_id = :company_id  \n" +
            "        AND ((effective_date >= :begin_time \n" +
            "        AND effective_date <= :end_time ) \n" +
            "        OR (closing_date >= :begin_time \n" +
            "        AND closing_date <= :end_time )) \n" +
            "ORDER BY id DESC\n" +
            "LIMIT 1\n")
    public CustomerEnterprise findFirstByInsuredIdAndEidAndBeginTimeAndEndTimeOrderByIdIdDesc(
            @Param("insured_id") String insuredId
            , @Param("company_id") Integer companyId
            ,@Param("begin_time") Date beginTime
            ,@Param("end_time") Date endTime);

@Transactional
    public <S extends CustomerEnterprise> S save(S s);
}
