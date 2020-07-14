package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysoft.alpha.entity.CustomerOrder;

public interface CustomerOrderDAO extends JpaRepository<CustomerOrder,Integer>{
	void deleteAllByIdIn(Integer[] ids);

}
