package com.mysoft.alpha.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mysoft.alpha.entity.BatchFee;
import com.mysoft.alpha.entity.CustomerEnterprise;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.CustomerEnterpriseService;
import com.mysoft.alpha.service.PurchaseOrderService;
import com.mysoft.alpha.service.UserService;
import com.mysoft.alpha.util.DateUtil;

@RestController
public class PurchaseOrderController {
	@Autowired
	CustomerEnterpriseService customerEnterpriseService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
    UserService userService;
	
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
    
    @GetMapping("/api/admin/content/purchaseorderpay/batchfeeData")
    public Result batchFeeFormData() {
    	String operator = SecurityUtils.getSubject().getPrincipal().toString();
    	User user = userService.findByUsername(operator);
    	String cname = user.getCompany().getName();
    	String cid = user.getCompany().getId() +"";
    	String batchNumber = "P_"+ user.getCompany().getCode() + "_"+ DateUtil.getCurrentDate() + "_"  + new Random().nextInt(1000);
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("cname",cname);
    	map.put("cid",cid);
    	map.put("batchNumber",batchNumber);
    	
    	return ResultFactory.buildSuccessResult(map);
    }
    
    @PostMapping("/api/admin/content/purchaseorder/pay")
    public Result batchFeeFormPay(@RequestBody Map<String,String> map) {
    	String operator = SecurityUtils.getSubject().getPrincipal().toString();
    	System.out.println("map="+map);
    	String batchNumber = map.get("batchNumber").toString();
    	String effectiveNumber = map.get("effectiveNumber").toString();
    	String cid = map.get("cid").toString();
    	String ids = map.get("ids").toString();
    	System.out.println("ids="+ ids);
    	String price = map.get("price").toString();
    	String prepayment = map.get("prepayment").toString();
    	String beginTime = map.get("beginTime").toString();
    	String endTime = map.get("endTime").toString();
    	String payTime = map.get("payTime").toString();
    	String remark = map.get("remark").toString();
    	String payImg = map.get("payImg").toString();
    	
    	BatchFee batchFee = new BatchFee();
    	batchFee.setBatchNumber(batchNumber);
    	batchFee.setBtype(1);//付费类型1，客户-公司，2客户-产品
    	batchFee.setFtype(1);//1付费，-1扣费
    	batchFee.setPayType(2);//2企业
    	batchFee.setPayId(Integer.parseInt(cid));//付费来源id  
    	batchFee.setBeginTime(DateUtil.convertToDate(beginTime));
    	batchFee.setEndTime(DateUtil.convertToDate(endTime));
    	batchFee.setEffectiveNumber(Integer.parseInt(effectiveNumber));//有效数
    	batchFee.setPrice(Integer.parseInt(price));
    	batchFee.setPrepayment(Integer.parseInt(prepayment));
    	batchFee.setPayImg(payImg);
    	batchFee.setRemark(remark);
    	batchFee.setPayTime(DateUtil.convertToDate(payTime));
    	batchFee.setOperator(operator);
    	batchFee.setCreateTime(new Date());
    	
    	purchaseOrderService.batchFeeFormProcess(batchFee, ids);
    	
   
    	return ResultFactory.buildSuccessResult("付费成功");
    }
    
    @PostMapping("/api/admin/content/purchaseorder/uploadFile")
    public String payImgUpload(@RequestParam("file") MultipartFile file) {
        String folder = "D:/upload/file/pay";
        String fileURL = "http://localhost:8443/api/file/";
        String oriFileName = file.getOriginalFilename();
        String suffix = oriFileName.substring(oriFileName.lastIndexOf('.'));
        String prefix = oriFileName.substring(0,oriFileName.lastIndexOf('.'));
        System.out.println("file =" + file.getOriginalFilename() +
        		" ,len ="  + file.getOriginalFilename().length() +
        		",prefix=" + prefix +
        		",suffix=" + suffix);
        File uploadFileFolder = new File(folder);
        File localFile = new File(uploadFileFolder, prefix + System.currentTimeMillis() + suffix);
        if (!localFile.getParentFile().exists())
            localFile.getParentFile().mkdirs();
        try {
            file.transferTo(localFile);
            fileURL += localFile.getName();            
            return fileURL;
        } catch (IOException e) {
            e.printStackTrace();            
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return null;
    }    
}
