package com.mysoft.alpha.controller;

import com.mysoft.alpha.config.MyWebConfigurer;
import com.mysoft.alpha.dao.ProductDAO;
import com.mysoft.alpha.entity.Complaint;
import com.mysoft.alpha.entity.CustomerProduct;
import com.mysoft.alpha.entity.Product;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.model.CPExcelForm;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    CompanyService companyService;

    @Autowired
    CustomerProductService customerProductService;
    @Autowired
    ComplaintService complaintService;

    @PostMapping("/section/customer/complaint")
    @Transactional
    public Result payConfirm(@RequestBody Map<String, String> map) {
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        String opt = map.get("opt");
        String remark = map.get("reson");
        String opt_zh = "投诉";

        if(opt.equals("1")){
            //投诉
            Complaint complaint= new Complaint();
            complaint.setCpId(Integer.valueOf(map.get("id")));
            complaint.setCtype(1);//不减扣费用
            complaint.setRemark(opt_zh+"："+remark);
            complaint.setOperator(operator);
            complaintService.save(complaint);
        }else{
            //            评价
            opt_zh = "评价";
            CustomerProduct customerProduct =  customerProductService.findById(Integer.valueOf(map.get("id")));
            customerProduct.setRemark(opt_zh+"："+remark);
            customerProduct.setStatus("9");
            customerProduct.setOperator(operator);
            customerProduct.setClosingDate(new Date());
        }
        return ResultFactory.buildSuccessResult(opt_zh+"成功");
    }

    @PostMapping("/section/save/service")
    @Transactional
    public Result saveService(@RequestBody @Valid Product product) {
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        User currUser = userService.findByUsername(operator);
        product.setOperator(operator);
        product.setCompany(currUser.getCompany());
        product.setCreateTime(new Date());
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

        List<CustomerProduct> customerProductList = customerProductService.findAllCustomerProductByUser(operator);
        List<CPExcelForm> returnList = new ArrayList<>();

        for (CustomerProduct customerProduct : customerProductList) {

            String status = customerProduct.getStatus();
            String statusZh = "";
            switch (status) {
                case "6":
                    statusZh = MyWebConfigurer.STATUS6;
                    break;
                case "7":
                    statusZh = MyWebConfigurer.STATUS7;
                    break;
                case "-7":
                    statusZh = MyWebConfigurer.STATUS_7;
                    break;
                case "8":
                    statusZh = MyWebConfigurer.STATUS8;
                    break;
                case "9":
                    statusZh = MyWebConfigurer.STATUS9;
                    break;
                case "5":
                    statusZh = MyWebConfigurer.STATUS5;
                    break;
                case "-5":
                    statusZh = MyWebConfigurer.STATUS_5;
                    break;
                default:
                    statusZh = "其他";
            }


            returnList.add(new CPExcelForm(customerProduct.getId(), customerProduct.getId(), null,
                    customerProduct.getPolicyNumber(),
                    productService.findById(customerProduct.getProductId()).getProduct(), customerProduct.getName(),
                    customerProduct.getCertificateType(), customerProduct.getPhone(), customerProduct.getInsuredId(),
                    customerProduct.getEffectiveDate(), customerProduct.getClosingDate(), customerProduct.getRemark(),
                    customerProduct.getRemark(), statusZh, customerProduct.getCreateTime(),
                    customerProduct.getOperator(), 0, customerProduct.getFromId(),
                    companyService.findById(customerProduct.getFromId()).getName(), customerProduct.getToType(),
                    customerProduct.getCompanyId(),
                    companyService.findById(customerProduct.getCompanyId()).getName() , null));

        }
        return ResultFactory.buildSuccessResult(returnList);
    }
}
