package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.BatchFeeMst;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchFeeMstDAO extends JpaRepository<BatchFeeMst, Integer> {
    public BatchFeeMst findByBatchNumber(String batchNumber);

}
