package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysoft.alpha.entity.Company;

public interface CompanyDAO extends JpaRepository<Company, Integer> {
	public Company findByCode(String code);

}
