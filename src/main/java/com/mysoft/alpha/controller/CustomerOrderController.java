package com.mysoft.alpha.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mysoft.alpha.entity.CustomerOrder;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.CustomerOrderService;
import com.mysoft.alpha.util.DateUtil;

@RestController
public class CustomerOrderController {
	@Autowired
	CustomerOrderService customerOrderService;
	
    @GetMapping("/api/customerorder/list")
    public Result listCustomerOrders() {
        return ResultFactory.buildSuccessResult(customerOrderService.list());
    }
    
    @PostMapping("/api/admin/content/customerorder")
    public Result addOrUpdateCustomerOrder(@RequestBody CustomerOrder customerOrder) {
    	customerOrderService.addOrUpdate(customerOrder);
        return ResultFactory.buildSuccessResult("修改成功");
    }
    @PostMapping("/api/admin/content/customerorder/delete")
    public Result deleteCustomerOrder(@RequestBody @Valid CustomerOrder customerOrder) {
    	customerOrderService.deleteById(customerOrder.getId());
        return ResultFactory.buildSuccessResult("删除成功");
    }
    
    @GetMapping("/api/admin/content/customerorder/deleteByIds")
    public Result deleteCustomerOrders(@RequestParam() Integer[] ids) {
    	customerOrderService.deleteByIds(ids);
        return ResultFactory.buildSuccessResult("删除成功");
    }    
    
    @PostMapping("/api/admin/content/uploadFile")
    public String excelUpload(MultipartFile file) {
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
            List<CustomerOrder> list = parseFile2List(file);
            customerOrderService.saveAll(list);
            file.transferTo(localFile);
            fileURL += localFile.getName();            
            return fileURL;
        } catch (IOException e) {
            e.printStackTrace();            
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return "";
    }
    

    private List<CustomerOrder> parseFile2List(MultipartFile file) throws IOException {
         List<CustomerOrder> coList = new ArrayList<>();
         Workbook  workbook = WorkbookFactory.create(file.getInputStream());
         Sheet sheet = workbook.getSheetAt(0);
         int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();//获取表单所有的行
         System.out.println("physicalNumberOfRows="+ physicalNumberOfRows);//行数包括标题行
         for (int i = 1; i < physicalNumberOfRows; i++) {
             Row row = sheet.getRow(i);
             CustomerOrder customerOrder = new CustomerOrder();

             Cell c0 = row.getCell(0);
             customerOrder.setSeqNumber(c0.getStringCellValue());

             Cell c1 = row.getCell(1);
             customerOrder.setPolicyNumber(c1.getStringCellValue());

             Cell c2 = row.getCell(2);
             customerOrder.setProduct(c2.getStringCellValue());

             Cell c3 = row.getCell(3);
             customerOrder.setInsuredName(c3.getStringCellValue());
             
             Cell c4 = row.getCell(4);
             String cType = c4.getStringCellValue();
             if(cType != null && cType.equals("身份证")) {
            	 cType = "1";
             }else if(cType != null && cType.equals("护照")) {
            	 cType = "2";
             }
             customerOrder.setCertificateType(cType);
             
             Cell c5 = row.getCell(5);
             customerOrder.setInsuredId(c5.getStringCellValue());

             Cell c6 = row.getCell(6);
             customerOrder.setPhonenum(c6.getStringCellValue());
             
             Cell c7 = row.getCell(7);
             String effectiveDate = c7.getStringCellValue();           
             customerOrder.setEffectiveDate(DateUtil.convertToDate(effectiveDate));
             
             Cell c8 = row.getCell(8);
             String closingDate = c8.getStringCellValue();   
             customerOrder.setClosingDate(DateUtil.convertToDate(closingDate));

             Cell c9 = row.getCell(9);
             String sex = c9.getStringCellValue();
             if(sex != null && sex.equals("男")) {
            	 sex = "1";
             }else if(cType != null && sex.equals("女")) {
            	 sex = "2";
             }
             customerOrder.setSex(sex);
             
             Cell c10 = row.getCell(10);
             String age = c10.getStringCellValue();
             if(StringUtils.isNotEmpty(age)) {
            	 customerOrder.setAge(Integer.parseInt(age));
             }   
             
             Cell c11 = row.getCell(11);
             customerOrder.setLocation(c11.getStringCellValue());
             
             Cell c12 = row.getCell(12);
             customerOrder.setRemark(c12.getStringCellValue());
             
             customerOrder.setState("1");//默认状态
             coList.add(customerOrder);
         }
         workbook.close();
         
         return coList;
     }    
    
}
