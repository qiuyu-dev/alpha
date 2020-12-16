package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.CpExcelMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客户-产品Excel主表(CpExcelMst)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:14:04
 */
public interface CpExcelMstDao extends JpaRepository<CpExcelMst, Integer> {

    CpExcelMst findByFileName(String fileName);
    
    CpExcelMst findMstByChargeSubjectIdAndBatchNum(Integer chargeSubjectId, String batchNum);

    List<CpExcelMst> findByChargeSubjectIdOrderByIdDesc(Integer chargeSubjectId);

    List<CpExcelMst> findByPaySubjectIdOrderByIdDesc(Integer paySubjectId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update cp_excel_detail ced set ced.state =:status where ced.cp_excel_mst_id = :mstId and ced.state in(3)", nativeQuery = true)
	int updateDetailAll(@Param(value = "mstId") Integer mstId, @Param(value = "status") Integer status);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update cp_excel_detail ced set ced.state =:status where ced.cp_excel_mst_id = :mstId  and ced.id in(:ids)", nativeQuery = true)
	int updateDetail(@Param(value = "mstId") Integer mstId, @Param(value = "status") Integer status,
			@Param(value = "ids") List<Integer> ids);
}