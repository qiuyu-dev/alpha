package com.mysoft.alpha.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mysoft.alpha.entity.CpExcelDetail;

/**
 * 客户-产品excle明细(CpExcelDetail)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:14:01
 */
public interface CpExcelDetailDao extends JpaRepository<CpExcelDetail, Integer> {
    CpExcelDetail findByOutTradeNo(String outTradeNo);

    List<CpExcelDetail> findByCustomerSubjectIdAndProductId(Integer customerSubjectId, Integer productId);

    @Query(value = "select * from cp_excel_detail ced where ced.cp_excel_mst_id =:cpExcelMstId and ced.state in(:status)" +
            " and (ced.customer_name like %:cname% and ced.product_name like %:productName%  and ced.out_trade_no " +
            "like %:outTradeNo%) order by ced.id asc", nativeQuery = true)
    List<CpExcelDetail> findByParamsAndSort(@Param(value = "cpExcelMstId") Integer cpExcelMstId,
                                            @Param(value = "status") List<Integer> status,
                                            @Param(value = "cname") String name,
                                            @Param(value = "productName") String productName,
                                            @Param(value = "outTradeNo") String outTradeNo);
    
    @Query(value = "select * from cp_excel_detail ced where ced.cp_excel_mst_id =:cpExcelMstId and ced.state in(:status)" +
            " and (ced.customer_name like %:cname% and ced.product_name like %:productName%  and ced.out_trade_no " +
            "like %:outTradeNo%) order by ced.id asc", 
            countQuery = "select count(*) from cp_excel_detail ced where ced.cp_excel_mst_id =:cpExcelMstId and ced.state in(:status)" +
                    " and (ced.customer_name like %:cname% and ced.product_name like %:productName%  and ced.out_trade_no " +
                    "like %:outTradeNo%) order by ced.id asc", nativeQuery = true)
    Page<CpExcelDetail> findPageByParamsAndSort(@Param(value = "cpExcelMstId") Integer cpExcelMstId,
            @Param(value = "status") List<Integer> status,
            @Param(value = "cname") String name,
            @Param(value = "productName") String productName,
            @Param(value = "outTradeNo") String outTradeNo, Pageable pageable);

    
    @Query(value = "select * from cp_excel_detail ced where ced.cp_excel_mst_id in "
    		+ "( select id from cp_excel_mst cem where cem.pay_subject_id = :paySubjectId   ) and ced.state in(:status)" +
            " and (ced.customer_name like %:cname% and ced.product_name like %:productName%  and ced.out_trade_no " +
            "like %:outTradeNo%) order by ced.id asc", 
            countQuery = "select count(*) from cp_excel_detail ced where ced.cp_excel_mst_id in " 
            		 + "( select id from cp_excel_mst cem where cem.pay_subject_id = :paySubjectId   ) and ced.state in(:status)" +
                    " and (ced.customer_name like %:cname% and ced.product_name like %:productName%  and ced.out_trade_no " +
                    "like %:outTradeNo%) order by ced.id asc", nativeQuery = true)
    Page<CpExcelDetail> findDetailPageByParamsAndSort(@Param(value = "paySubjectId") Integer paySubjectId,
            @Param(value = "status") List<Integer> status,
            @Param(value = "cname") String name,
            @Param(value = "productName") String productName,
            @Param(value = "outTradeNo") String outTradeNo, Pageable pageable);

    @Query(value = "select * from cp_excel_detail ced where ced.cp_excel_mst_id in "
    		+ "( select id from cp_excel_mst cem where cem.pay_subject_id = :paySubjectId ) and ced.state in(:status)", 
            countQuery = "select count(*) from cp_excel_detail ced where ced.cp_excel_mst_id in " 
            		 + "( select id from cp_excel_mst cem where cem.pay_subject_id = :paySubjectId ) and ced.state in(:status)", nativeQuery = true)
    Page<CpExcelDetail> findDetailPageByParams(@Param(value = "paySubjectId") Integer paySubjectId,
            @Param(value = "status") List<Integer> status, Pageable pageable);
    
    
    void deleteByCpExcelMstId(Integer cpExcelMstId);

    List<CpExcelDetail> findByCpExcelMstId(Integer cpExcelMstId);

    List<CpExcelDetail> findByCpExcelMstIdAndStateInOrderByIdAsc(Integer cpExcelMstId, List<Integer> status);


}