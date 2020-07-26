package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.entity.BatchFeeDetail;

public interface BatchFeeDetailService {
	
	public List<BatchFeeDetail> findAllBatchFeeDetail();
	
	public BatchFeeDetail addOrUpdateBatchFeeDetail(BatchFeeDetail batchFeeDetail);
	
	public void deleteBatchFeeDetailById(int id);
	
	public BatchFeeDetail findBatchFeeDetailById(int id);

	public List<BatchFeeDetail> findBybatchNumber(String batchNumber);
	
}
