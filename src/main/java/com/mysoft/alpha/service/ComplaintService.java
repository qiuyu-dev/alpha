package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.Complaint;

import java.util.List;

public interface ComplaintService {
	
	public List<Complaint> findAllComplaint();
	
	public Complaint findById(Integer id);

	public void save(Complaint complaint);

}
