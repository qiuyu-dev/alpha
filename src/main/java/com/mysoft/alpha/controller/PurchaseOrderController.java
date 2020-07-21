package com.mysoft.alpha.controller;

import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysoft.alpha.entity.CustomerEnterprise;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.CustomerEnterpriseService;

@RestController
public class PurchaseOrderController {
	@Autowired
	CustomerEnterpriseService customerEnterpriseService;
	
    @GetMapping("/api/customerenterprise/list")
    public Result listCustomerEnterprise() {
        return ResultFactory.buildSuccessResult(customerEnterpriseService.findAllCustomerEnterprise());
    }
    
    @PostMapping("/api/admin/content/purchaseorder")
    public Result updateCustomerEnterprise(@RequestBody Map<String,String> map) {
    	System.out.println("opt="+map.get("opt"));
    	System.out.println("id="+map.get("id"));
    	System.out.println("reson="+map.get("reson"));
    	String opt = map.get("opt").toString();
    	String id = map.get("id").toString();
    	String reson = map.get("reson").toString();

    	CustomerEnterprise customerEnterprise = customerEnterpriseService.findCustomerEnterpriseById(Integer.parseInt(id));
    	String operator = SecurityUtils.getSubject().getPrincipal().toString();
    	customerEnterprise.setOperator(operator);
    	customerEnterprise.setCreateTime(new Date());
    	if ("1".equals(opt)) {//1 通过 
    		customerEnterprise.setCestatus(2);//已核实
    	}else if ("2".equals(opt)) {//2未通过
    		customerEnterprise.setCestatus(3);//未通过
    	}
    	customerEnterprise.setReson(reson);
    	customerEnterpriseService.addOrUpdateCustomerEnterprise(customerEnterprise);
        return ResultFactory.buildSuccessResult("修改成功");
    }
    
    @PostMapping("/api/admin/content/customerenterprise/delete")
    public Result deleteCustomerEnterprise(@RequestBody @Valid CustomerEnterprise customerEnterprise) {
    	customerEnterpriseService.deleteCustomerEnterpriseById(customerEnterprise.getId());
        return ResultFactory.buildSuccessResult("删除成功");
    }

}
