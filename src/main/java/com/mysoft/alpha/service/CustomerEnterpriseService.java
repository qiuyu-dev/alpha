package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.entity.CustomerEnterprise;

public interface CustomerEnterpriseService {
	public List<CustomerEnterprise> findAllCustomerEnterprise();

	public CustomerEnterprise addOrUpdateCustomerEnterprise(CustomerEnterprise customerEnterprise);

	public void deleteCustomerEnterpriseById(int id);
	
	public CustomerEnterprise findCustomerEnterpriseById(int id);

}
