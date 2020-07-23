package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.entity.BatchFee;

public interface BatchFeeService {
	public List<BatchFee> findAllBatchFee();
	
	public BatchFee addOrUpdateBatchFee(BatchFee batchFee);
	
	public void deleteBatchFeeById(int id);
	
	public BatchFee findBatchFeeById(int id);
	

}
