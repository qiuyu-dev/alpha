package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.BatchFeeMstDAO;
import com.mysoft.alpha.entity.BatchFeeMst;
import com.mysoft.alpha.service.BatchFeeMstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchFeeMstServiceImpl implements BatchFeeMstService {
	@Autowired
	BatchFeeMstDAO batchFeeMstDAO;

	@Override
	public List<BatchFeeMst> findAllBatchFeeMst() {
		return batchFeeMstDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public BatchFeeMst addOrUpdateBatchFeeMst(BatchFeeMst batchFeeMst) {
		return batchFeeMstDAO.save(batchFeeMst);
	}

	@Override
	public void deleteBatchFeeMstById(int id) {
		batchFeeMstDAO.deleteById(id);
	}

	@Override
	public BatchFeeMst findBatchFeeMstById(int id) {
		return batchFeeMstDAO.getOne(id);
	}

}
