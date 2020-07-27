package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.BatchFeeDetailDAO;
import com.mysoft.alpha.dao.BatchFeeMstDAO;
import com.mysoft.alpha.entity.BatchFeeDetail;
import com.mysoft.alpha.service.BatchFeeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchFeeDetailServiceImpl implements BatchFeeDetailService {
	@Autowired
	BatchFeeDetailDAO batchFeeDetailDAO;
	@Autowired
	BatchFeeMstDAO batchFeeMstDAO;

	
	@Override
	public List<BatchFeeDetail> findAllBatchFeeDetail() {
		return batchFeeDetailDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public BatchFeeDetail addOrUpdateBatchFeeDetail(BatchFeeDetail batchFeeDetail) {
		return batchFeeDetailDAO.save(batchFeeDetail);
	}

	@Override
	public void deleteBatchFeeDetailById(int id) {
		batchFeeDetailDAO.deleteById(id);		
	}

	@Override
	public BatchFeeDetail findBatchFeeDetailById(int id) {
		return batchFeeDetailDAO.getOne(id);
	}
	
	@Override
	public List<BatchFeeDetail> findBybatchNumber(String batchNumber) {
		return batchFeeDetailDAO.findByBatchFeeMstOrderByIdAsc(batchFeeMstDAO.findByBatchNumber(batchNumber));
	}



}
