package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.BatchFeeDetail;
import com.mysoft.alpha.entity.BatchFeeMst;

import java.util.List;

/**
 * 批次付费主表(BatchFeeMst)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:13:49
 */
public interface BatchFeeService {
//    BatchFeeMst saveBatchFee(BatchFeeMst batchFeeMst);

    List<BatchFeeDetail> findDetailBySourceDetailIdOrderByIdDesc(Integer cpExcelDetailId);

    BatchFeeMst getMstById(Integer id);

    BatchFeeMst saveBatchFeeMst(BatchFeeMst batchFeeMst);

    BatchFeeDetail saveBatchFeeDetail(BatchFeeDetail batchFeeDetail);

    List<BatchFeeMst> findAllBatchFeeMstByUserAndStateIn(String username ,List<Integer> status);

    List<BatchFeeDetail> findDetailsByBatchFeeMstId(Integer batchFeeMstId);

    List<BatchFeeMst> findMstByCpExcelDetailId(Integer cpExcelDetailId);

}