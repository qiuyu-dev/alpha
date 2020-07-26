package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.BatchFeeMst;

import java.util.List;

public interface BatchFeeMstService {
	public List<BatchFeeMst> findAllBatchFeeMst();
	
	public BatchFeeMst addOrUpdateBatchFeeMst(BatchFeeMst batchFeeMst);
	
	public void deleteBatchFeeMstById(int id);
	
	public BatchFeeMst findBatchFeeMstById(int id);
	

}
