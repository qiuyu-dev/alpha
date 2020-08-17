package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.BatchFeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 批次付费明细(BatchFeeDetail)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-09 15:38:41
 */
public interface BatchFeeDetailDao extends JpaRepository<BatchFeeDetail, Integer> {

     List<BatchFeeDetail> findBySourceDetailIdOrderByIdAsc(Integer cpExcelDetailId);

     List<BatchFeeDetail> findByBatchFeeMstIdOrderByIdAsc(Integer batchFeeMstId);

     List<BatchFeeDetail> findBySourceDetailIdOrderByIdDesc(Integer sourceDetailId);

}