package com.mysoft.alpha.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysoft.alpha.entity.BatchFeeDetail;

public interface BatchFeeDetailDAO extends JpaRepository<BatchFeeDetail, Integer> {

	public List<BatchFeeDetail> findBybatchNumber(String batchNumber);
}
