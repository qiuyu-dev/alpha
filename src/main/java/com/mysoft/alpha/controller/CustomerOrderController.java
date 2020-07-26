package com.mysoft.alpha.controller;

import com.mysoft.alpha.entity.Company;
import com.mysoft.alpha.entity.CustomerProductExcelDetail;
import com.mysoft.alpha.entity.CustomerProductExcelMst;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.model.CPExcelForm;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultCode;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.CompanyService;
import com.mysoft.alpha.service.CpExcelService;
import com.mysoft.alpha.service.CustomerOrderService;
import com.mysoft.alpha.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.mysoft.alpha.util.DateUtil;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @RestController 控制器注解，⽤于标记这个类是⼀个控制器，返回JSON数据的时候使⽤，如果使⽤这
 * 个注解，则接⼝返回数据会被序列化为JSON =@Controller+@ResponseBody
 * @RequestMapping 请求路由映射注解，⽤于类上做1级路径；⽤于某个⽅法上做⼦路径，
 * 需要版本号 v1,全局访问pub ，返回对象才能序列化为JSON
 */

@RestController
@RequestMapping("/api/admin/v1/pri/co")
public class CustomerOrderController {
    @Autowired
    CustomerOrderService customerOrderService;

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CpExcelService cpExcelService;

    @GetMapping("/share/customerorder/list")
    public Result listCustomerOrders() {
        return ResultFactory.buildSuccessResult(customerOrderService.findCustomerProductExcelDetailList());
    }



    @GetMapping("/share/customerorder/listCpExcels")
    public Result listCpExcels() {
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        List<CustomerProductExcelMst> cPExcelMstsList = cpExcelService.findCpExcelByUser(operator);
        List<CPExcelForm> returnList = new ArrayList<>();
        for (CustomerProductExcelMst cPExcelMst : cPExcelMstsList) {
            //Excel 明细循环
            List<CustomerProductExcelDetail> cPExcelDetailsList = cPExcelMst.getCpExcelDetails();
            for (CustomerProductExcelDetail cPExcelDetail : cPExcelDetailsList) {
                 String status = cPExcelDetail.getStatus();
                //状态1、触发，2、已申请，3、重新触发，4、重新申请 、5、审核通过，6、确认，7、提供中，8、完成，9、评价，-1、失败，-5审核未通过（目前没有1，6，7）
                String statusZh = "";
                switch (status) {
                    case "2":
                        statusZh = "等待审核";
                    case "5":
                        statusZh = "审核通过";
                    case "-5":
                        statusZh = "审核未通过";
                    case "-2":
                        statusZh = "申请失败";
                    default:
                        statusZh = "其他";

                }

                returnList.add(new CPExcelForm(cPExcelMst.getId(), cPExcelDetail.getId(), cPExcelDetail.getSeqNumber(),
                        cPExcelDetail.getPolicyNumber(), cPExcelDetail.getProduct(), cPExcelDetail.getInsuredName(),
                        cPExcelDetail.getCertificateType(), cPExcelDetail.getPhonenum(), cPExcelDetail.getInsuredId(),
                        cPExcelDetail.getEffectiveDate(), cPExcelDetail.getClosingDate(), cPExcelMst.getRemark(),
                        cPExcelDetail.getExplanation(), statusZh, cPExcelMst.getCreateTime(),
                        cPExcelMst.getOperator(), cPExcelMst.getToType(), cPExcelMst.getToId(),
                        cPExcelMst.getFileName()));
            }
        }
        return ResultFactory.buildSuccessResult(returnList);
    }

    @PostMapping("/section/customerorder")
    @Transactional
    public Result addOrUpdateCustomerProductExcelDetail(
            @RequestBody CustomerProductExcelDetail customerProductExcelDetail) {
        customerOrderService.addOrUpdateCustomerProductExcelDetail(customerProductExcelDetail);
        return ResultFactory.buildSuccessResult("修改成功");
    }

    @PostMapping("/section/delete")
    @Transactional
    public Result deleteCustomerProductExcelDetail(
            @RequestBody @Valid CustomerProductExcelDetail customerProductExcelDetail) {
        customerOrderService.deleteCustomerProductExcelDetailById(customerProductExcelDetail.getId());
        return ResultFactory.buildSuccessResult("删除成功");
    }

    @GetMapping("/section/deleteByIds")
    @Transactional
    public Result deleteCustomerOrders(@RequestParam() Integer[] ids) {
        customerOrderService.deleteCustomerProductExcelDetailByIds(ids);
        return ResultFactory.buildSuccessResult("删除成功");
    }

    @GetMapping("/share/company/list")
    public Result getAllCompany() {
        return ResultFactory.buildSuccessResult(companyService.findAllCompany());
    }

    /**
     * 上传excel完成客户单上传
     *
     * @param map
     * @param file
     * @return
     */

    @PostMapping("/share/uploadFile")
    @Transactional
    public Result excelUpload(@RequestParam Map<String, String> map, @RequestParam("file") MultipartFile file) {
        System.out.println("map=" + map);
        String cid = map.get("cid");
        String folder = "upload/file";
        String fileURL = "http://localhost:8443/api/file/";
        String oriFileName = file.getOriginalFilename();
        String suffix = oriFileName.substring(oriFileName.lastIndexOf('.'));
        String prefix = oriFileName.substring(0, oriFileName.lastIndexOf('.'));
        //        System.out.println(
        //                "file =" + file.getOriginalFilename() + " ,len =" + file.getOriginalFilename().length() + ",prefix=" +
        //                        prefix + ",suffix=" + suffix);
        File uploadFileFolder = new File(folder);
        File localFile = new File(uploadFileFolder, prefix + System.currentTimeMillis() + suffix);
        if (!localFile.getParentFile().exists())
            localFile.getParentFile().mkdirs();
        try {
            Result result = parseUploadFile(cid, file);
            file.transferTo(localFile);
            fileURL += localFile.getName();
            ResultFactory.buildResult(ResultCode.SUCCESS, "上传成功", fileURL);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Result parseUploadFile(String cid, MultipartFile file) throws IOException {
        Company company = companyService.findById(Integer.parseInt(cid));
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(operator);
        int companyType = user.getCompany().getCtype();
        int companyId = user.getCompany().getId();
        CustomerProductExcelMst cpExcelMst = new CustomerProductExcelMst();
        System.out.println("-----cpExcelMst.setFileName(file.getOriginalFilename()):" + file.getOriginalFilename());
        cpExcelMst.setFileName(file.getOriginalFilename());
        cpExcelMst.setFromType(companyType);
        cpExcelMst.setFromId(companyId);
        cpExcelMst.setToId(company.getId());
        cpExcelMst.setToType(company.getCtype());
        cpExcelMst.setCtype(1);
        cpExcelMst.setOperator(operator);
        cpExcelMst.setCreateTime(new Date());
        System.out.println("-----cpExcelMst.setFileName(file.getOriginalFilename()):" + cpExcelMst.getFileName());

        CustomerProductExcelMst customerProductExcelMst =
                customerOrderService.addOrUpdateCustomerProductExcelMst(cpExcelMst);
        List<CustomerProductExcelDetail> customerProductExcelDetailList = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();//获取表单所有的行
        System.out.println("physicalNumberOfRows=" + physicalNumberOfRows);//行数包括标题行
        for (int i = 1; i < physicalNumberOfRows; i++) {
            CustomerProductExcelDetail cpExcelDetail = parseOneRow(customerProductExcelMst, companyId, sheet, i);
            customerProductExcelDetailList.add(cpExcelDetail);
        }
        customerOrderService.saveAllCustomerProductExcelDetail(customerProductExcelDetailList);
        workbook.close();

        return null;
    }

    private CustomerProductExcelDetail parseOneRow(CustomerProductExcelMst cpExcelMst, int companyId, Sheet sheet,
                                                   int i) throws CustomException {
        CustomerProductExcelDetail cpExcelDetail = new CustomerProductExcelDetail();
//        cpExcelDetail.setCpExcelMst(cpExcelMst);
        cpExcelDetail.setCpExcelMstId(cpExcelMst.getId());
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(0, "第" + i + "行有异常:" + e.getMessage());
        }

        return cpExcelDetail;
    }

}
