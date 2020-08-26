package com.mysoft.alpha.controller;

import com.mysoft.alpha.common.ComplaintType;
import com.mysoft.alpha.common.CustomStatus;
import com.mysoft.alpha.common.SubjectType;
import com.mysoft.alpha.entity.*;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 客户-企业-产品订单(CustomerProduct)表控制层
 *
 * @author makejava
 * @since 2020-08-02 16:13:56
 */
@RestController
@RequestMapping("/api/admin/v1/pri/customerProduct")
public class CustomerProductController {
    @Autowired
    UserService userService;
    
    @Autowired
    private CustomerProductService customerProductService;
    
    @Autowired
    private CpExcelService cpExcelService;
    
    @Autowired
    private AlphaSubjectService alphaSubjectService;
    
    @Autowired
    private ComplaintService complaintService;
    
    @Autowired
    private ProductService productService;


    @PostMapping("/complaint")
    @Transactional
    public Result confirm(@RequestBody Map<String, String> map) throws CustomException {
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        String opt = map.get("opt");
        String remark = map.get("reson");
        String opt_zh = "投诉";

        if(opt.equals("1")){
            //投诉
            Complaint complaint= new Complaint();
            complaint.setCustomerProductId(Integer.valueOf(map.get("id")));
            complaint.setComplaintType(ComplaintType.TYPE1.value());//不减扣费用
            complaint.setRemark(opt_zh+"："+remark);
            complaint.setOperator(operator);
            complaint.setCreateTime(new Date());
            complaintService.save(complaint);
        }else{
            //            评价
            opt_zh = "评价";
            CustomerProduct customerProduct =  customerProductService.getOneById(Integer.valueOf(map.get("id")));
            customerProduct.setRemark(opt_zh+"："+remark);
            customerProduct.setState(CustomStatus.STATUS9.value());
            customerProduct.setOperator(operator);
            customerProductService.save(customerProduct);
            CpExcelDetail cpExcelDetail = cpExcelService.getDetailById(customerProduct.getSourceDetailId());
            List<CustomerProduct> customerProductList =
                    customerProductService.findBySourceDetailIdIsInOrderById(Arrays.asList(cpExcelDetail.getId()));
            boolean isAll = true;
            for(CustomerProduct customerProduct1:customerProductList){
                 if(customerProduct1.getState()<CustomStatus.STATUS8.value()){
                     isAll= false;
                     break;
                 }
            }
            if(isAll){
                cpExcelDetail.setState(CustomStatus.STATUS8.value());
                cpExcelService.saveDetail(cpExcelDetail);
            }

        }
        return ResultFactory.buildSuccessResult(opt_zh+"成功");
    }


    @GetMapping("/list")
    public Result listCustomerServices()  throws CustomException {
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        List<CustomerProduct> returnList = new ArrayList<>();

        List<CpExcelMst> cpExcelMstList = new ArrayList<>();
        if (operator.equals("admin")) {
            returnList = customerProductService.findAll();
        } else {
            User user = userService.findByUsername(operator);
            AlphaSubject alphaSubject = alphaSubjectService.getAlphaSubjectById(user.getAlphaSubjectId());
            if (alphaSubject.getSubjectType() == SubjectType.TYPE2.value()) {
                cpExcelMstList = cpExcelService.findMstByPaySubjectIdOrderById(user.getAlphaSubjectId());
            } else {
                cpExcelMstList = cpExcelService.findMstByChargeSubjectIdOrderById(user.getAlphaSubjectId());
            }
            returnList = customerProductService.findBySourceMstIdInAndStatusIn(
                    cpExcelMstList.stream().map(CpExcelMst::getId).collect(Collectors.toList()), Arrays.asList(7));
        }
        for(CustomerProduct customerProduct:returnList){
            CpExcelMst cpExcelMst = cpExcelService.getMstById(customerProduct.getSourceId());
            cpExcelMst.setPaySubject(alphaSubjectService.getAlphaSubjectById(cpExcelMst.getPaySubjectId()));
            cpExcelMst.setChargeSubject(alphaSubjectService.getAlphaSubjectById(cpExcelMst.getChargeSubjectId()));
            customerProduct.setSourceMst(cpExcelMst);
            customerProduct.setCustomerSubject(alphaSubjectService.getAlphaSubjectById(customerProduct.getCustomerSubjectId()));
            customerProduct.setProduct(productService.getProductById(customerProduct.getProductId()));
            switch (customerProduct.getState()) {
                case 7:
                    customerProduct.setStateReason(CustomStatus.STATUS7.getReasonPhrase());
                    break;
                default:
                    customerProduct.setStateReason(CustomStatus.STATUS_1.getReasonPhrase());
                    break;
            }
            customerProduct.setComplaints(complaintService.findByCustomerProductId(customerProduct.getId()));
        }
        return ResultFactory.buildSuccessResult(returnList);
    }

}