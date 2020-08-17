package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.CpExcelMst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 客户-产品Excel主表(CpExcelMst)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:14:04
 */
public interface CpExcelMstDao extends JpaRepository<CpExcelMst, Integer> {


    CpExcelMst findByFileName(String fileName);

    List<CpExcelMst> findByOperatorOrderById(String operator);
//    List<CpExcelMst> findByPaySubjectIdOrderById(Integer paySubjectId);
//    List<CpExcelMst> findByChargeSubjectIdOrderById(Integer chargeSubjectId);
    List<CpExcelMst> findByChargeSubjectIdOrderByIdDesc(Integer chargeSubjectId);
    List<CpExcelMst> findByPaySubjectIdOrderByIdDesc(Integer chargeSubjectId);
}