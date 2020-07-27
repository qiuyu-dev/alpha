package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.CustomerEnterprise;

import java.util.List;

public interface CustomerEnterpriseService {

	public List<CustomerEnterprise> findAllCustomerEnterpriseByToUserAndStatus(String username,List<Integer> statusList) ;
	public List<CustomerEnterprise> findAllCustomerEnterpriseByFromUserAndStatus(String username,
																			  List<Integer> statusList) ;


	public List<CustomerEnterprise> findAllCustomerEnterprise();

	public CustomerEnterprise addOrUpdateCustomerEnterprise(CustomerEnterprise customerEnterprise);

	public void deleteCustomerEnterpriseById(int id);
	
	public CustomerEnterprise findCustomerEnterpriseById(int id);

}
