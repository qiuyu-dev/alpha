package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.entity.CustomerProduct;

public interface CustomerProductService {
	
	public List<CustomerProduct> findAllCustomerProduct();
	
	public CustomerProduct findById(Integer id);
	
	public void saveAllCustomerProduct(List<CustomerProduct> list);

}
