package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.Product;

import java.util.List;

public interface ProductService {
	
	public List<Product> findAllProduct();
	
	public Product findById(Integer id);
	
	public List<Product> findByCompanyId(Integer id);

	public void save(Product product);

}
