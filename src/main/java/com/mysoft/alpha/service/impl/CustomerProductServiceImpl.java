package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.CustomerProductDAO;
import com.mysoft.alpha.dao.UserDAO;
import com.mysoft.alpha.entity.CustomerProduct;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.service.CustomerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerProductServiceImpl implements CustomerProductService {

	@Autowired
	CustomerProductDAO customerProductDAO;

	@Autowired
	private UserDAO userDAO;
	
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


	@Override
	public List<CustomerProduct> findAllCustomerProductByUser(String username) {
		List<CustomerProduct>  retrunList = new ArrayList<>();
		if (username.equals("admin")) {
			retrunList = customerProductDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
		} else {
			User user = userDAO.findByUsername(username);
			if (user.getCompany().getCtype()==1){
				retrunList = customerProductDAO.findByFromIdOrderByIdDesc(user.getCompany().getId());
			}else{
				retrunList = customerProductDAO.findByCompanyIdOrderByIdDesc(user.getCompany().getId());
			}


		}
		return retrunList;

	}
}
