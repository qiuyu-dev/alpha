package com.mysoft.alpha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysoft.alpha.entity.Company;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.CompanyService;

@RestController
@RequestMapping("/api/admin/v1/pub/company")
public class CompanyController {
	@Autowired
	CompanyService companyService;
	
	@GetMapping("/findById")
	public Result findCompanyById(String id) {
		Company company = companyService.findById(Integer.parseInt(id));
		return ResultFactory.buildSuccessResult(company);
	}

}
