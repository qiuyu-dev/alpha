package com.mysoft.alpha.controller;

import com.mysoft.alpha.dao.ProductDAO;
import com.mysoft.alpha.entity.Product;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.CustomerProductService;
import com.mysoft.alpha.service.ProductService;
import com.mysoft.alpha.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/v1/pri/sc")
public class ServiceController {
	@Autowired
	ProductDAO productDAO;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@Autowired
	CustomerProductService customerProductService;

	@PostMapping("/section/save/service")
	@Transactional
	public Result saveService(@RequestBody @Valid Product product) {
		String operator = SecurityUtils.getSubject().getPrincipal().toString();
		User currUser = userService.findByUsername(operator);
		product.setOperator(operator);
		product.setCompany(currUser.getCompany());
		productService.save(product);
		return ResultFactory.buildSuccessResult("保存成功");
	}

	@GetMapping("/share/service/list")
	public Result listServices() {
		String operator = SecurityUtils.getSubject().getPrincipal().toString();
		List<Product> productList = new ArrayList<>();
		if (operator.equals("admin")) {
			productList = productDAO.findAll();
		} else {
			User currUser = userService.findByUsername(operator);
			productList = productDAO.findByCompanyId(currUser.getCompany().getId());
		}
		return ResultFactory.buildSuccessResult(productList);
	}

	@GetMapping("/share/customer/service/list")
	public Result listCustomerServices() {
		String operator = SecurityUtils.getSubject().getPrincipal().toString();
		return ResultFactory.buildSuccessResult(customerProductService.findAllCustomerProductByUser(operator));
	}
}
