package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysoft.alpha.entity.CustomerProductExcelDetail;

public interface CustomerProductExcelDetailDAO extends JpaRepository<CustomerProductExcelDetail, Integer> {
	public void deleteAllByIdIn(Integer[] ids);
}
