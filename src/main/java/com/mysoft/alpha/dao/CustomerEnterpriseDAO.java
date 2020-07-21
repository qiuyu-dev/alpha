package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysoft.alpha.entity.CustomerEnterprise;

public interface CustomerEnterpriseDAO extends JpaRepository<CustomerEnterprise, Integer> {

}
