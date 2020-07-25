package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.entity.Product;

public interface ProductService {
	
	public List<Product> findAllProduct();
	
	public Product findById(Integer id);
	
	public List<Product> findByCompanyId(Integer id);

}
