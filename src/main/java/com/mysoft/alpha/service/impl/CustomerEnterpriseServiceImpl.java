package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AdminRoleDAO;
import com.mysoft.alpha.dao.AdminUserRoleDAO;
import com.mysoft.alpha.dao.CustomerEnterpriseDAO;
import com.mysoft.alpha.dao.UserDAO;
import com.mysoft.alpha.entity.CustomerEnterprise;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.service.CustomerEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerEnterpriseServiceImpl implements CustomerEnterpriseService {
	@Autowired
	CustomerEnterpriseDAO customerEnterpriseDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AdminUserRoleDAO adminUserRoleDAO;
	@Autowired
	private AdminRoleDAO adminRoleDAO;

	@Override
	public List<CustomerEnterprise> findAllCustomerEnterpriseByFromUserAndStatus(String username,
																				 List<Integer> statusList) {
		List<CustomerEnterprise>  retrunList = new ArrayList<>();
		if (username.equals("admin")) {
			retrunList = customerEnterpriseDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));

		} else {
			User user = userDAO.findByUsername(username);
			retrunList = customerEnterpriseDAO.findByFromIdAndCestatusInOrderByIdDesc(user.getCompany().getId(),
					statusList);

		}
		return retrunList;
	}

	@Override
	public List<CustomerEnterprise> findAllCustomerEnterpriseByToUserAndStatus(String username,
																			  List<Integer> statusList) {
		List<CustomerEnterprise>  retrunList = new ArrayList<>();
		if (username.equals("admin")) {
			retrunList = customerEnterpriseDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));

		} else {
			User user = userDAO.findByUsername(username);
//			int roleId = adminUserRoleDAO.findAllByUid(user.getId()).get(0).getRid();
//
//			AdminRole role = adminRoleDAO.findById(roleId);
//			if (role.getNameZh().contains("管理")) {
				retrunList = customerEnterpriseDAO.findByEidAndCestatusInOrderByIdDesc(user.getCompany().getId(),
						statusList);
//			} else {
//				retrunList = customerEnterpriseDAO.findByOperatorOrderByIdDesc(user.getUsername());
//			}
		}
		return retrunList;
	}

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
