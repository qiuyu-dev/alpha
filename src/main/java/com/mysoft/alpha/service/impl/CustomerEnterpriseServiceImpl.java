package com.mysoft.alpha.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysoft.alpha.dao.CustomerEnterpriseDAO;
import com.mysoft.alpha.entity.CustomerEnterprise;
import com.mysoft.alpha.service.CustomerEnterpriseService;

@Service
public class CustomerEnterpriseServiceImpl implements CustomerEnterpriseService {
	@Autowired
	CustomerEnterpriseDAO customerEnterpriseDAO;
	
	@Override
	public List<CustomerEnterprise> findAllCustomerEnterprise() {
		return customerEnterpriseDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public CustomerEnterprise addOrUpdateCustomerEnterprise(CustomerEnterprise customerEnterprise) {
		
		return customerEnterpriseDAO.save(customerEnterprise);
	}

	@Override
	public void deleteCustomerEnterpriseById(int id) {
		customerEnterpriseDAO.deleteById(id);
		
	}

	@Override
	public CustomerEnterprise findCustomerEnterpriseById(int id) {
		return customerEnterpriseDAO.getOne(id);
	}

}
