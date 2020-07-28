package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.CustomerProduct;

import java.util.List;

public interface CustomerProductService {
	
	public List<CustomerProduct> findAllCustomerProduct();
	
	public CustomerProduct findById(Integer id);
	
	public void saveAllCustomerProduct(List<CustomerProduct> list);

	public List<CustomerProduct> findAllCustomerProductByUser(String username);

}
