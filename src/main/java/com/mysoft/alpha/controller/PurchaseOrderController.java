package com.mysoft.alpha.controller;

import com.mysoft.alpha.config.AlphaConfig;
import com.mysoft.alpha.entity.BatchFeeDetail;
import com.mysoft.alpha.entity.BatchFeeMst;
import com.mysoft.alpha.entity.CustomerEnterprise;
import com.mysoft.alpha.entity.CustomerProduct;
import com.mysoft.alpha.entity.Product;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.BatchFeeDetailService;
import com.mysoft.alpha.service.BatchFeeMstService;
import com.mysoft.alpha.service.CustomerEnterpriseService;
import com.mysoft.alpha.service.CustomerProductService;
import com.mysoft.alpha.service.ProductService;
import com.mysoft.alpha.service.PurchaseOrderService;
import com.mysoft.alpha.service.UserService;
import com.mysoft.alpha.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/admin/v1/pri/po")
public class PurchaseOrderController {
    @Autowired
    CustomerEnterpriseService customerEnterpriseService;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    BatchFeeMstService batchFeeMstService;
    
    @Autowired
    BatchFeeDetailService batchFeeDetailService;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    CustomerProductService customerProductService;

    @Autowired
    UserService userService;
    
    @Autowired
    AlphaConfig alphaConfig;

    //    @GetMapping("/api/customerenterprise/list")
    @GetMapping("/share/customerenterprise/list")
    public Result listCustomerEnterprise() {
        return ResultFactory.buildSuccessResult(customerEnterpriseService.findAllCustomerEnterprise());
    }
//todo 未完成
    @GetMapping("/share/purchaseservice/list")
    public Result listPurchaseServices() {
        return ResultFactory.buildSuccessResult(customerEnterpriseService.findAllCustomerEnterprise());
    }
    //    @PostMapping("/api/admin/content/purchaseorder")
    @PostMapping("/section/purchaseorder")
    @Transactional
    public Result updateCustomerEnterprise(@RequestBody Map<String, String> map) {
        System.out.println("opt=" + map.get("opt"));
        System.out.println("id=" + map.get("id"));
        System.out.println("reson=" + map.get("reson"));
        String opt = map.get("opt");
        String id = map.get("id");
        String reson = map.get("reson");

        CustomerEnterprise customerEnterprise =
                customerEnterpriseService.findCustomerEnterpriseById(Integer.parseInt(id));
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        customerEnterprise.setOperator(operator);
        customerEnterprise.setCreateTime(new Date());
        if ("1".equals(opt)) {//1 通过
            customerEnterprise.setCestatus(2);//已核实
        } else if ("2".equals(opt)) {//2未通过
            customerEnterprise.setCestatus(3);//未通过
        }
        customerEnterprise.setReson(reson);
        customerEnterpriseService.addOrUpdateCustomerEnterprise(customerEnterprise);
        return ResultFactory.buildSuccessResult("修改成功");
    }

    //    @PostMapping("/api/admin/content/customerenterprise/delete")
    @PostMapping("/section/customerenterprise/delete")
    @Transactional
    public Result deleteCustomerEnterprise(@RequestBody @Valid CustomerEnterprise customerEnterprise) {
        customerEnterpriseService.deleteCustomerEnterpriseById(customerEnterprise.getId());
        return ResultFactory.buildSuccessResult("删除成功");
    }

    //    @GetMapping("/api/admin/content/purchaseorderpay/BatchFeeMstData")
    @GetMapping("/section/purchaseorderpay/BatchFeeMstData")
    public Result BatchFeeMstFormData() {

        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(operator);
        String cname = user.getCompany().getName();
        String cid = user.getCompany().getId() + "";
        String batchNumber =
                "P_" + user.getCompany().getCode() + "_" + DateUtil.getCurrentDate() + "_" + new Random().nextInt(1000);
        Map<String, String> map = new HashMap<String, String>();
        map.put("cname", cname);
        map.put("cid", cid);
        map.put("batchNumber", batchNumber);

        return ResultFactory.buildSuccessResult(map);
    }

    //    @PostMapping("/api/admin/content/purchaseorder/pay")
    @PostMapping("/section/purchaseorder/pay")
    @Transactional
    public Result BatchFeeMstFormPay(@RequestBody Map<String, String> map) {
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println("map=" + map);
        String batchNumber = map.get("batchNumber");
        String effectiveNumber = map.get("effectiveNumber");
        String cid = map.get("cid");
        String ids = map.get("ids");
        System.out.println("ids=" + ids);
        String price = map.get("price");
        String prepayment = map.get("prepayment");
        String effectiveDate = map.get("effectiveDate");
        String closingDate = map.get("closingDate");
        String payTime = map.get("payTime");
        String remark = map.get("remark");
        String payImg = map.get("payImg");

        BatchFeeMst batchFeeMst = new BatchFeeMst();
        batchFeeMst.setBatchNumber(batchNumber);
        batchFeeMst.setBtype(1);//付费类型1，客户-公司，2客户-产品
        batchFeeMst.setFtype(1);//1付费，-1扣费
        batchFeeMst.setPayType(2);//2企业
        batchFeeMst.setPayId(Integer.parseInt(cid));//付费来源id
        batchFeeMst.setEffectiveDate(DateUtil.convertToDate(effectiveDate));
        batchFeeMst.setClosingDate(DateUtil.convertToDate(closingDate));
        batchFeeMst.setEffectiveNumber(Integer.parseInt(effectiveNumber));//有效数
        batchFeeMst.setPrice(Integer.parseInt(price));
        batchFeeMst.setPrepayment(Integer.parseInt(prepayment));
        batchFeeMst.setPayImg(payImg);
        batchFeeMst.setRemark(remark);
        batchFeeMst.setPayTime(DateUtil.convertToDate(payTime));
        batchFeeMst.setOperator(operator);
        batchFeeMst.setCreateTime(new Date());

        purchaseOrderService.batchFeeMstFormProcess(batchFeeMst, ids);


        return ResultFactory.buildSuccessResult("付费成功");
    }

    /**
     * 上传文件，完成采购付费
     * @param file
     * @return
     */
    //    @PostMapping("/api/admin/content/purchaseorder/uploadFile")
    @PostMapping("/section/purchaseorder/uploadFile")
    public String payImgUpload(@RequestParam("file") MultipartFile file) {
        String folder = alphaConfig.getUploadFolder() +"/pay";
        String fileURL = alphaConfig.getFileUrl();
        String oriFileName = file.getOriginalFilename();
        String suffix = oriFileName.substring(oriFileName.lastIndexOf('.'));
        String prefix = oriFileName.substring(0, oriFileName.lastIndexOf('.'));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //    @GetMapping("/api/BatchFeeMst/list")
    @GetMapping("/share/batchFeeMst/list")
    public Result listBatchFeeMst() {
        return ResultFactory.buildSuccessResult(batchFeeMstService.findAllBatchFeeMst());
    }

    //    @PostMapping("/api/admin/content/purchaseorder/payconfirm")
    @PostMapping("/section/purchaseorder/payconfirm")
    @Transactional
    public Result payConfirm(@RequestBody Map<String, String> map) {
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        String id = map.get("id");
        String receivable = map.get("receivable") != null ? map.get("receivable") : "";
        String confirmRemark = map.get("confirmRemark") != null ? map.get("confirmRemark") : "";
        BatchFeeMst batchFeeMst = batchFeeMstService.findBatchFeeMstById(Integer.parseInt(id));
        batchFeeMst.setReceivable(Integer.parseInt(receivable));
        batchFeeMst.setConfirmRemark(confirmRemark);
        batchFeeMst.setCreateTime(new Date());
        batchFeeMst.setStatus(1);//1已确认
        batchFeeMst.setOperator(operator);
        batchFeeMstService.addOrUpdateBatchFeeMst(batchFeeMst);
        
        List<Product> productList = productService.findByCompanyId(batchFeeMst.getPayId());
    	List<BatchFeeDetail> batchFeeDetailList = batchFeeDetailService.findBybatchNumber(batchFeeMst.getBatchNumber());
    	List<CustomerProduct> customerProductList = new ArrayList<CustomerProduct>();
    	for(BatchFeeDetail batchFeeDetail: batchFeeDetailList) {
    		CustomerProduct customerProduct = new CustomerProduct();
    		customerProduct.setCcId(batchFeeDetail.getCeId());
    		customerProduct.setCompanyId(batchFeeMst.getPayId());
    		customerProduct.setEffectiveDate(batchFeeMst.getEffectiveDate());
    		customerProduct.setClosingDate(batchFeeMst.getClosingDate());
    		customerProduct.setStatus(6);
    		customerProduct.setOperator(operator);
    		customerProduct.setCreateTime(new Date());
    		for(Product product : productList) {
    			customerProduct.setProductId(product.getId());
    			customerProductList.add(customerProduct);
    		}   		
    	}  	
    	customerProductService.saveAllCustomerProduct(customerProductList);

        return ResultFactory.buildSuccessResult("确认成功");
    }
}
