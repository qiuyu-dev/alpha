package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.mysoft.alpha.entity.BatchFeeDetail;

public interface BatchFeeDetailDAO extends JpaRepository<BatchFeeDetail, Integer> {
	public List<BatchFeeDetail> findBybatchNumber(String batchNumber);
}
