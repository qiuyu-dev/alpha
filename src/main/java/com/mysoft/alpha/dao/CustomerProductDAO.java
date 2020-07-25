package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysoft.alpha.entity.CustomerProduct;

public interface CustomerProductDAO extends JpaRepository <CustomerProduct, Integer>{

}
