package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.CustomerProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerProductDAO extends JpaRepository<CustomerProduct, Integer> {
	
	public List<CustomerProduct> findByFromIdOrderByIdDesc(int fromId);

	public List<CustomerProduct> findByCompanyIdOrderByIdDesc(int companyId);

}
