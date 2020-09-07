package com.mysoft.alpha.controller;

import com.mysoft.alpha.common.ChargeType;
import com.mysoft.alpha.common.CustomStatus;
import com.mysoft.alpha.common.PayType;
import com.mysoft.alpha.common.SourceType;
import com.mysoft.alpha.config.AlphaConfig;
import com.mysoft.alpha.entity.*;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.*;
import com.mysoft.alpha.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 批次付费主表(BatchFeeMst)表控制层
 *
 * @author makejava
 * @since 2020-08-02 16:13:51
 */
@RestController
@RequestMapping("/api/admin/v1/pri/batchFee")
public class BatchFeeController {
    @Autowired
    UserService userService;
    @Autowired
    AlphaSubjectService alphaSubjectService;
    @Autowired
    ProductService productService;
    @Autowired
    AlphaConfig alphaConfig;
    @Autowired
    private CpExcelService cpExcelService;
    @Autowired
    private CustomerProductService customerProductService;
    @Autowired
    private BatchFeeService batchFeeService;

    @PostMapping("/payconfirm")
    @Transactional
    public Result payConfirm(@RequestBody Map<String, String> map) throws CustomException {
        String opt = map.get("opt");
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        String id = map.get("id");
        String receive = map.get("receivable") != null ? map.get("receivable") : "0";
        Double receivable = Double.valueOf(receive) * 100;
        if (receivable > 999999999 || receivable < 0) {
            throw new CustomException(0, "预付款在[0-9999999]之间");
        }
        String confirmRemark = map.get("confirmRemark") != null ? map.get("confirmRemark") : "";
        BatchFeeMst batchFeeMst = batchFeeService.getMstById(Integer.parseInt(id));
        batchFeeMst.setReceivable(receivable.intValue());
        batchFeeMst.setConfirmRemark(confirmRemark);
        batchFeeMst.setCreateTime(new Date());
        batchFeeMst.setCashier(operator);
        if (opt.equals("1")) {
            batchFeeMst.setState(CustomStatus.STATUS7.value());// 7 = "确认收款服务中";//服务方-客户或采购方

            List<BatchFeeDetail> batchFeeDetailList = batchFeeService.findDetailsByBatchFeeMstId(batchFeeMst.getId());
            for (BatchFeeDetail batchFeeDetail : batchFeeDetailList) {
                CpExcelDetail cpExcelDetail = cpExcelService.getDetailById(batchFeeDetail.getSourceDetailId());
                saveCustomerProduct(cpExcelDetail, batchFeeMst.getEffectiveDate(), batchFeeMst.getClosingDate());
            }
        } else {
            batchFeeMst.setState(CustomStatus.STATUS_7.value());// -7未收款
        }
        batchFeeService.saveBatchFeeMst(batchFeeMst);
        return ResultFactory.buildSuccessResult("付费确认成功");
    }

    @GetMapping("/list")
    public Result listBatchFeeMstbyUser() throws CustomException {
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        List<BatchFeeMst> batchFeeMstList = batchFeeService
                .findAllBatchFeeMstByUserAndStateIn(operator, Arrays.asList(CustomStatus.STATUS6.value()));
        for (BatchFeeMst batchFeeMst : batchFeeMstList) {
            List<BatchFeeDetail> batchFeeDetailList = batchFeeService.findDetailsByBatchFeeMstId(batchFeeMst.getId());
            for (BatchFeeDetail batchFeeDetail : batchFeeDetailList) {
                //				batchFeeDetail.setCpExcelDetail(cpExcelService.getDetailById(batchFeeDetail.getSourceDetailId()));
                batchFeeDetail.setCustomerSubject(
                        alphaSubjectService.getAlphaSubjectById(batchFeeDetail.getCustomerSubjectId()));
            }
            batchFeeMst.setBatchFeeDetails(batchFeeDetailList);
            batchFeeMst.setPaySubject(alphaSubjectService.getAlphaSubjectById(batchFeeMst.getPaySubjectId()));
            batchFeeMst.setChargeSubject(alphaSubjectService.getAlphaSubjectById(batchFeeMst.getChargeSubjectId()));
            switch (batchFeeMst.getState()) {
                case 5:
                    batchFeeMst.setStateReason(CustomStatus.STATUS5.getReasonPhrase());
                    break;
                case 6:
                    batchFeeMst.setStateReason(CustomStatus.STATUS6.getReasonPhrase());
                    break;
                case 7:
                    batchFeeMst.setStateReason(CustomStatus.STATUS7.getReasonPhrase());
                    break;
                case -7:
                    batchFeeMst.setStateReason(CustomStatus.STATUS_7.getReasonPhrase());
                    break;
                default:
                    batchFeeMst.setStateReason(CustomStatus.STATUS_1.getReasonPhrase());
                    break;
            }
        }
        return ResultFactory.buildSuccessResult(batchFeeMstList);
    }

    @PostMapping("/pay")
    @Transactional
    public Result BatchFeeMstFormPay(@RequestBody Map<String, String> map) throws CustomException {
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        String batchNumber = map.get("batchNumber");
        String effectiveNumber = map.get("effectiveNumber");
        String ids = map.get("ids");
        String price = map.get("price");
        String prepayment = map.get("prepayment");
        String effective = map.get("effectiveDate");
        String closing = map.get("closingDate");
        String remark = map.get("remark");
        String payImg = map.get("payImg");

        String[] detailIdArray = ids.split(",");
        Date closingDate = new Date();
        Date effectiveDate = new Date();
        try {
            closingDate = DateUtil.convertToDate(closing);
            effectiveDate = DateUtil.convertToDate(effective);
            if (closingDate.before(effectiveDate)) {
                throw new CustomException(0, "结束日期早于开始日期");
            }
        } catch (Exception e) {
            throw new CustomException(0, "日期格式问题");
        }

        Integer chargeId1 = null;
        Integer chargeId2 = null;
        CpExcelMst cpExcelMst = new CpExcelMst();
        List<CpExcelDetail> cpExcelDetailList = new ArrayList<>();
        for (String string : detailIdArray) {
            // 验证客户-服务
            CpExcelDetail cpExcelDetail = cpExcelService.getDetailById(Integer.valueOf(string));
            cpExcelDetailList.add(cpExcelDetail);
            if (cpExcelDetail.getState() != CustomStatus.STATUS5.value()) {
                throw new CustomException(0, "请确认采购单状态");
            }
            //			if (effectiveDate.before(cpExcelDetail.getEffectiveDate())) {
            //				throw new CustomException(0, "付费开始日期早于服务开始日期");
            //			}
            //			if (closingDate.after(DateUtil.addTime(DateUtil.addTime(cpExcelDetail.getEffectiveDate(), Calendar.YEAR, 1),
            //					Calendar.DATE, -1))) {
            //				throw new CustomException(0, "付费结束日期晚于服务结束日期");
            //			}
            List<BatchFeeDetail> batchFeeDetailList =
                    batchFeeService.findDetailByCustomerSubjectId(cpExcelDetail.getCustomerSubjectId());
            for (BatchFeeDetail batchFeeDetail : batchFeeDetailList) {
                BatchFeeMst batchFeeMst = batchFeeService.getMstById(batchFeeDetail.getBatchFeeMstId());
                if (!(batchFeeMst.getEffectiveDate().after(closingDate) ||
                        batchFeeMst.getClosingDate().before(effectiveDate))) {
                    if (batchFeeMst.getState().intValue() >= 7) {
                        throw new CustomException(0, "客户已有付费记录：" + batchFeeMst.getBatchNumber());
                    }
                }
            }
            cpExcelMst = cpExcelService.getMstById(cpExcelDetail.getCpExcelMstId());
            if (chargeId1 == null) {
                chargeId1 = cpExcelMst.getChargeSubjectId();
            } else {
                if (chargeId2 == null) {
                    chargeId2 = cpExcelMst.getChargeSubjectId();
                }
                if (!(chargeId1.equals(chargeId2))) {
                    throw new CustomException(0, "请选择同一服务商");
                }
            }

        }

        BatchFeeMst batchFeeMst = new BatchFeeMst();
        batchFeeMst.setBatchNumber(batchNumber);
        User user = userService.findByUsername(operator);
        batchFeeMst.setPaySubjectId(user.getAlphaSubjectId());
        batchFeeMst.setChargeSubjectId(cpExcelMst.getChargeSubjectId());
        batchFeeMst.setEffectiveDate(effectiveDate);
        batchFeeMst.setClosingDate(closingDate);
        batchFeeMst.setPayType(PayType.TYPE1.value());// 1、按客户付费
        batchFeeMst.setChargeType(ChargeType.TYPE1.value());// 1收款
        batchFeeMst.setEffectiveNumber(Integer.parseInt(effectiveNumber));// 有效数
        Double price1 = Double.valueOf(price) * 100;
        if (price1 > 999999999 || price1 < 0) {
            throw new CustomException(0, "单价在[0-9999999]之间");
        }
        batchFeeMst.setPrice(price1.intValue());
        Double prepayment1 = Double.valueOf(prepayment) * 100;
        if (prepayment1 > 999999999 || prepayment1 < 0) {
            throw new CustomException(0, "预付款在[0-9999999]之间");
        }
        batchFeeMst.setPrepayment(prepayment1.intValue());
        batchFeeMst.setReceivable(prepayment1.intValue());
        batchFeeMst.setImg(payImg);
        batchFeeMst.setUrl(payImg);// 返回payImg已包含url,此处可以存成一样
        batchFeeMst.setRemark(remark);
        batchFeeMst.setOperator(operator);
        batchFeeMst.setCreateTime(new Date());
        batchFeeMst.setState(CustomStatus.STATUS6.value());// 6 = 付费完成待收款
        batchFeeMst = batchFeeService.saveBatchFeeMst(batchFeeMst);

        for (CpExcelDetail cpExcelDetail : cpExcelDetailList) {
            BatchFeeDetail batchFeeDetail = new BatchFeeDetail();
            batchFeeDetail.setBatchFeeMstId(batchFeeMst.getId());
            batchFeeDetail.setCustomerSubjectId(cpExcelDetail.getCustomerSubjectId());
            batchFeeDetail.setSourceType(SourceType.TYPE1.value());
            batchFeeDetail.setSourceDetailId(cpExcelDetail.getId());
            batchFeeDetail.setEffectiveNumber(1);
            batchFeeDetail.setOperator(operator);
            batchFeeDetail.setCreateTime(new Date());
            batchFeeDetail = batchFeeService.saveBatchFeeDetail(batchFeeDetail);

        }

        return ResultFactory.buildSuccessResult("付费成功");
    }

    /**
     * 创建付费批号
     *
     * @return
     * @throws CustomException
     */
    @GetMapping("/BatchFeeMstData")
    public Result BatchFeeMstFormData() throws CustomException {
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(operator);
        String cname = alphaSubjectService.getAlphaSubjectById(user.getAlphaSubjectId()).getName();
        if (cname == null || "".equals(cname)) {
            throw new CustomException(0, "系统用户不能付费");
        }
        String cid = user.getAlphaSubjectId() + "";
        String batchNumber = "P_" + cid + "_" + DateUtil.getCurrentDate() + "_" + new Random().nextInt(1000);
        Map<String, String> map = new HashMap<String, String>();
        map.put("cname", cname);
        map.put("cid", cid);
        map.put("batchNumber", batchNumber);
        return ResultFactory.buildSuccessResult(map);
    }

    /**
     * 上传文件，完成采购付费
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    public Result payImgUpload(@RequestParam("file") MultipartFile file) throws CustomException {
        String folder = alphaConfig.getUploadFolder();// + "/pay";
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
            return ResultFactory.buildSuccessResult(fileURL);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("上传异常" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("系统异常");
        }
    }

    private void saveCustomerProduct(CpExcelDetail cpExcelDetail, Date effectiveDate, Date closingDate)
            throws CustomException {
        List<CustomerProduct> customerProductList = new ArrayList<>();
        CpExcelMst cpExcelMst = cpExcelService.getMstById(cpExcelDetail.getCpExcelMstId());
        List<Product> productList = productService.findByAlphaSubjectId(cpExcelMst.getChargeSubjectId());
        if (productList.size() < 1) {
            throw new CustomException(0, "没有服务产品");
        }
        for (Product product : productList) {
            if (!(customerProductService
                    .isExistOutTradeNoe(cpExcelDetail.getCustomerSubjectId(), product.getId(), effectiveDate,
                            closingDate))) {
                CustomerProduct customerProduct = new CustomerProduct();
                customerProduct.setCustomerSubjectId(cpExcelDetail.getCustomerSubjectId());
                customerProduct.setProductId(product.getId());
                customerProduct.setEffectiveDate(effectiveDate);
                customerProduct.setClosingDate(closingDate);
                customerProduct.setSourceType(SourceType.TYPE1.value());
                customerProduct.setSourceId(cpExcelDetail.getCpExcelMstId());
                customerProduct.setSourceDetailId(cpExcelDetail.getId());
                customerProduct.setOperator(cpExcelDetail.getOperator());
                customerProduct.setCreateTime(new Date());
                customerProduct.setState(CustomStatus.STATUS7.getValue());
                customerProductList.add(customerProduct);
            }
        }
        if (customerProductList.size() > 0) {
            customerProductService.saveAll(customerProductList);
        } else {
            AlphaSubject customer = alphaSubjectService.getAlphaSubjectById(cpExcelDetail.getCustomerSubjectId());
            throw new CustomException(0, "客户：" + customer.getName() + ",已有服务");
        }
    }
}