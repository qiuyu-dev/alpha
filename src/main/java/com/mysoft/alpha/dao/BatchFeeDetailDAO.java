package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.BatchFeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchFeeDetailDAO extends JpaRepository<BatchFeeDetail, Integer> {
//	public List<BatchFeeDetail> findBybatchNumber(String batchNumber);
//public List<BatchFeeDetail> findByBatchFeeMstOrderByIdAsc(BatchFeeMst batchFeeMst);
}
