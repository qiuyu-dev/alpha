package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.entity.Company;

public interface CompanyService {
	public boolean isExistCode(String code);
	
	public List<Company> findAllCompany();
	
	public Company findById(Integer id);

}
