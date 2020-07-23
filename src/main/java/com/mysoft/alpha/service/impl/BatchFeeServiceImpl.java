package com.mysoft.alpha.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysoft.alpha.dao.BatchFeeDAO;
import com.mysoft.alpha.entity.BatchFee;
import com.mysoft.alpha.service.BatchFeeService;

@Service
public class BatchFeeServiceImpl implements BatchFeeService {
	@Autowired
	BatchFeeDAO batchFeeDAO;

	@Override
	public List<BatchFee> findAllBatchFee() {
		return batchFeeDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public BatchFee addOrUpdateBatchFee(BatchFee batchFee) {
		return batchFeeDAO.save(batchFee);
	}

	@Override
	public void deleteBatchFeeById(int id) {
		batchFeeDAO.deleteById(id);		
	}

	@Override
	public BatchFee findBatchFeeById(int id) {
		return batchFeeDAO.getOne(id);
	}

}
