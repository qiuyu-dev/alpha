package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.ComplaintDAO;
import com.mysoft.alpha.entity.Complaint;
import com.mysoft.alpha.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {
	@Autowired
	ComplaintDAO complaintDAO;

	@Override
	public List<Complaint> findAllComplaint() {
		return complaintDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public Complaint findById(Integer id) {
		return complaintDAO.getOne(id);
	}

	@Override
	public void save(Complaint complaint) {
		complaintDAO.save(complaint);

	}
}
