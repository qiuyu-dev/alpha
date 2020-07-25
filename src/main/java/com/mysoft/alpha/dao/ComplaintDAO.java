package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysoft.alpha.entity.Complaint;

public interface ComplaintDAO extends JpaRepository<Complaint, Integer>{

}
