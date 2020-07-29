package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.BatchFeeMst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchFeeMstDAO extends JpaRepository<BatchFeeMst, Integer> {
	
	public BatchFeeMst findByBatchNumber(String batchNumber);

	public List<BatchFeeMst> findByToIdOrderByIdAsc(int chargeId);

}
