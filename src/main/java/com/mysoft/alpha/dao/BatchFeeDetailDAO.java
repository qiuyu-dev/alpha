package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.BatchFeeDetail;
import com.mysoft.alpha.entity.BatchFeeMst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchFeeDetailDAO extends JpaRepository<BatchFeeDetail, Integer> {
//	public List<BatchFeeDetail> findBybatchNumber(String batchNumber);
public List<BatchFeeDetail> findByBatchFeeMstOrderByIdAsc(BatchFeeMst batchFeeMst);
}
