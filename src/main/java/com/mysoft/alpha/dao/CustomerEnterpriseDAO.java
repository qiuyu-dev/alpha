package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.CustomerEnterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public interface CustomerEnterpriseDAO extends JpaRepository<CustomerEnterprise, Integer> {

    public List<CustomerEnterprise>  findByCompanyIdAndStatusInOrderByIdDesc(int eid,List<String> statusList);

    public List<CustomerEnterprise>  findByFromIdAndStatusInOrderByIdDesc(int fromId,List<String> statusLis);

    public List<CustomerEnterprise> findByCompanyIdOrderByIdDesc(int eid);

    public List<CustomerEnterprise> findByOperatorOrderByIdDesc(String operator);


    @Query(nativeQuery = true, value = "SELECT     `id`,    `certificate_type`,    `insured_id`,    `cname`,    `phone`,    `company_id`,\n" +
            "    `effective_date`,    `closing_date`,    `status`,    `from_type`,    `from_id`,    `cpem_id`,    `cped_id`,\n" +
            "    `remark`,    `confirm_remark`,    `operator`,    `create_time` \n" +
            "FROM  customer_enterprise\n" +
            "WHERE   insured_id = :insured_id AND company_id = :company_id  \n" +
            "        AND ((effective_date <= :begin_time \n" +
            "        AND closing_date >= :begin_time ) \n" +
            "        OR (effective_date >= :begin_time \n" +
            "        AND effective_date < :end_time )) \n" +
            "ORDER BY id DESC\n" +
            "LIMIT 1\n")
    public CustomerEnterprise findFirstByInsuredIdAndCompanyIdAndBeginTimeAndEndTimeOrderByIdIdDesc(
            @Param("insured_id") String insuredId
            , @Param("company_id") Integer companyId
            ,@Param("begin_time") Date beginTime
            ,@Param("end_time") Date endTime);

@Transactional
    public <S extends CustomerEnterprise> S save(S s);
}
