package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.CpExcelDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 客户-产品excle明细(CpExcelDetail)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:14:01
 */
public interface CpExcelDetailDao extends JpaRepository<CpExcelDetail, Integer> {
    CpExcelDetail findByOutTradeNo(String outTradeNo);

    List<CpExcelDetail> findByCustomerSubjectIdAndProductId(Integer customerSubjectId, Integer productId);

    void deleteByCpExcelMstId(Integer cpExcelMstId);

    List<CpExcelDetail> findByCpExcelMstId(Integer cpExcelMstId);

    List<CpExcelDetail> findByCpExcelMstIdAndStateInOrderByIdAsc(Integer cpExcelMstId,List<Integer> status);

}