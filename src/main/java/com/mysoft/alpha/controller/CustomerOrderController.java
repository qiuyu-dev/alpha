package com.mysoft.alpha.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mysoft.alpha.entity.Company;
import com.mysoft.alpha.entity.CustomerProductExcelDetail;
import com.mysoft.alpha.entity.CustomerProductExcelMst;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultCode;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.CompanyService;
import com.mysoft.alpha.service.CustomerOrderService;
import com.mysoft.alpha.service.UserService;
import com.mysoft.alpha.util.DateUtil;

@RestController
public class CustomerOrderController {
	@Autowired
	CustomerOrderService customerOrderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CompanyService companyService;
	
    @GetMapping("/api/customerorder/list")
    public Result listCustomerOrders() {
        return ResultFactory.buildSuccessResult(customerOrderService.findCustomerProductExcelDetailList());
    }
    
    @PostMapping("/api/admin/content/customerorder")
    public Result addOrUpdateCustomerProductExcelDetail(@RequestBody CustomerProductExcelDetail customerProductExcelDetail) {
    	customerOrderService.addOrUpdateCustomerProductExcelDetail(customerProductExcelDetail);
        return ResultFactory.buildSuccessResult("修改成功");
    }
    
    @PostMapping("/api/admin/content/customerorder/delete")
    public Result deleteCustomerProductExcelDetail(@RequestBody @Valid CustomerProductExcelDetail customerProductExcelDetail) {
    	customerOrderService.deleteCustomerProductExcelDetailById(customerProductExcelDetail.getId());
        return ResultFactory.buildSuccessResult("删除成功");
    }
    
    @GetMapping("/api/admin/content/customerorder/deleteByIds")
    public Result deleteCustomerOrders(@RequestParam() Integer[] ids) {
    	customerOrderService.deleteCustomerProductExcelDetailByIds(ids);
        return ResultFactory.buildSuccessResult("删除成功");
    }    
    
    @GetMapping("/api/admin/content/companylist")
    public Result getAllCompany() {
    	return ResultFactory.buildSuccessResult(companyService.findAllCompany());
    }
    
    @PostMapping("/api/admin/content/uploadFile")
    public Result excelUpload(@RequestParam Map<String,String> map,@RequestParam("file") MultipartFile file) {
    	System.out.println("map="+map);
    	String cid = map.get("cid").toString();
        String folder = "D:/upload/file";
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
        	Result result = parseUploadFile(cid,file);
            file.transferTo(localFile);
            fileURL += localFile.getName();            
            ResultFactory.buildResult(ResultCode.SUCCESS, "上传成功", fileURL);
            return result;
        } catch (IOException e) {
            e.printStackTrace();            
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return null;
    }    

    private Result parseUploadFile(String cid,MultipartFile file) throws IOException {
    	Company company = companyService.findById(Integer.parseInt(cid));
    	String operator = SecurityUtils.getSubject().getPrincipal().toString();
    	User user = userService.findByUsername(operator);
    	int companyType = user.getCompany().getCtype();
    	int companyId = user.getCompany().getId();
    	CustomerProductExcelMst cpExcelMst = new  CustomerProductExcelMst();
    	cpExcelMst.setFileName(file.getOriginalFilename());
    	cpExcelMst.setFromType(companyType);
    	cpExcelMst.setFromId(companyId);
    	cpExcelMst.setToId(company.getId());
    	cpExcelMst.setToType(company.getCtype());
    	cpExcelMst.setCtype(1);
    	cpExcelMst.setOperator(operator);
    	cpExcelMst.setCreateTime(new Date());
    	CustomerProductExcelMst customerProductExcelMst = customerOrderService.addOrUpdateCustomerProductExcelMst(cpExcelMst);
    	List<CustomerProductExcelDetail> customerProductExcelDetailList = new ArrayList<>();
         Workbook  workbook = WorkbookFactory.create(file.getInputStream());
         Sheet sheet = workbook.getSheetAt(0);
         int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();//获取表单所有的行
         System.out.println("physicalNumberOfRows="+ physicalNumberOfRows);//行数包括标题行
         for (int i = 1; i < physicalNumberOfRows; i++) {
        	 CustomerProductExcelDetail cpExcelDetail = parseOneRow(customerProductExcelMst,companyId, sheet, i);
        	 customerProductExcelDetailList.add(cpExcelDetail);
         }
         customerOrderService.saveAllCustomerProductExcelDetail(customerProductExcelDetailList);
         workbook.close();
         
         return null;
     }

	private CustomerProductExcelDetail parseOneRow(CustomerProductExcelMst cpExcelMst, int companyId, Sheet sheet, int i) throws CustomException {
		CustomerProductExcelDetail cpExcelDetail = new CustomerProductExcelDetail();
		cpExcelDetail.setCpExcelMst(cpExcelMst);
		cpExcelDetail.setOperator(cpExcelMst.getOperator());
		cpExcelDetail.setCreateTime(new Date());
		cpExcelDetail.setStatus("1");
		cpExcelDetail.setCompanyId(companyId);
		try {			
			 Row row = sheet.getRow(i);
			 cpExcelDetail.setRowNum(row.getRowNum());

			 Cell c0 = row.getCell(0);
			 cpExcelDetail.setSeqNumber(c0.getStringCellValue());
			 Cell c1 = row.getCell(1);
			 cpExcelDetail.setPolicyNumber(c1.getStringCellValue());
			 Cell c2 = row.getCell(2);
			 cpExcelDetail.setProduct(c2.getStringCellValue());
			 Cell c3 = row.getCell(3);
			 cpExcelDetail.setInsuredName(c3.getStringCellValue());
			 Cell c4 = row.getCell(4);
			 String cType = c4.getStringCellValue();
			 if(cType != null && cType.equals("身份证")) {
				 cType = "1";
			 }else if(cType != null && cType.equals("护照")) {
				 cType = "2";
			 }
			 cpExcelDetail.setCertificateType(cType);
			 
			 Cell c5 = row.getCell(5);
			 cpExcelDetail.setInsuredId(c5.getStringCellValue());
			 Cell c6 = row.getCell(6);
			 cpExcelDetail.setPhonenum(c6.getStringCellValue());
			 Cell c7 = row.getCell(7);
			 String effectiveDate = c7.getStringCellValue();           
			 cpExcelDetail.setEffectiveDate(DateUtil.convertToDate(effectiveDate));
			 Cell c8 = row.getCell(8);
			 String closingDate = c8.getStringCellValue();   
			 cpExcelDetail.setClosingDate(DateUtil.convertToDate(closingDate));
			 Cell c9 = row.getCell(9);
			 String sex = c9.getStringCellValue();
			 if(sex != null && sex.equals("男")) {
				 sex = "1";
			 }else if(cType != null && sex.equals("女")) {
				 sex = "2";
			 }
			 cpExcelDetail.setSex(sex);
			 Cell c10 = row.getCell(10);
			 String age = c10.getStringCellValue();
			 if(StringUtils.isNotEmpty(age)) {
				 cpExcelDetail.setAge(Integer.parseInt(age));
			 }   
			 
			 Cell c11 = row.getCell(11);
			 cpExcelDetail.setLocation(c11.getStringCellValue());
			 Cell c12 = row.getCell(12);
			 cpExcelDetail.setRemark(c12.getStringCellValue());
			 
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(0,"第" + i + "行有异常:"+e.getMessage());			
		}

		return cpExcelDetail;
	}    
    
}
