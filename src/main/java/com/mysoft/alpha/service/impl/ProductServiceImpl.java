package com.mysoft.alpha.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysoft.alpha.dao.ProductDAO;
import com.mysoft.alpha.entity.Product;
import com.mysoft.alpha.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;

	@Override
	public List<Product> findAllProduct() {
		return productDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public Product findById(Integer id) {
		return productDAO.getOne(id);
	}

	@Override
	public List<Product> findByCompanyId(Integer id) {		
		return productDAO.findByCompanyId(id);
	}

}
