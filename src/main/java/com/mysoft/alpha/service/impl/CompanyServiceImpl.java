package com.mysoft.alpha.service.impl;

import java.util.List;

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

	@Override
	public List<Company> findAllCompany() {
		return companyDAO.findAll();
	}

	@Override
	public Company findById(Integer id) {
		return companyDAO.getOne(id);
	}

}
