package com.mysoft.alpha.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysoft.alpha.dao.CompanyDAO;
import com.mysoft.alpha.entity.Company;
import com.mysoft.alpha.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	CompanyDAO companyDAO;

	public boolean isExistCode(String code) {
		Company company = companyDAO.findByCode(code);
		return null != company;
	}

}
