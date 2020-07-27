package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyDAO extends JpaRepository<Company, Integer> {
	public Company findByCode(String code);

	public List<Company> findByCtypeOrderByIdAsc(int ctype);
}
