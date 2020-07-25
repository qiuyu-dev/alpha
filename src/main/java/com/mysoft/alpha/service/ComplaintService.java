package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.entity.Complaint;

public interface ComplaintService {
	
	public List<Complaint> findAllComplaint();
	
	public Complaint findById(Integer id);

}
