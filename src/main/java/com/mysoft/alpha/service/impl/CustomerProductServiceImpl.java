package com.mysoft.alpha.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysoft.alpha.dao.CustomerProductDAO;
import com.mysoft.alpha.entity.CustomerProduct;
import com.mysoft.alpha.service.CustomerProductService;

@Service
public class CustomerProductServiceImpl implements CustomerProductService {

	@Autowired
	CustomerProductDAO customerProductDAO;
	
	@Override
	public List<CustomerProduct> findAllCustomerProduct() {
		return customerProductDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public CustomerProduct findById(Integer id) {
		return customerProductDAO.getOne(id);
	}
    
	@Transactional
	@Override
	public void saveAllCustomerProduct(List<CustomerProduct> list) {
		customerProductDAO.saveAll(list);
		
	}

}
