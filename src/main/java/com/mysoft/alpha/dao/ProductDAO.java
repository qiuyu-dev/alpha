package com.mysoft.alpha.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysoft.alpha.entity.Company;
import com.mysoft.alpha.entity.Product;

public interface ProductDAO extends JpaRepository <Product, Integer>{
	
	public List<Product> findByCompanyId(Integer companyId);

}
