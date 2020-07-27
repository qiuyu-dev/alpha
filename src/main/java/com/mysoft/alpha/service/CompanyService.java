package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.Company;

import java.util.List;

public interface CompanyService {
	public boolean isExistCode(String code);
	
	public List<Company> findAllCompany();
	
	public Company findById(Integer id);

	public List<Company> findAllServiceCompany();

}
