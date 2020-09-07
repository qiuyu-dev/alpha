package com.mysoft.alpha.controller;

import com.mysoft.alpha.common.CustomStatus;
import com.mysoft.alpha.common.ProductType;
import com.mysoft.alpha.common.SourceType;
import com.mysoft.alpha.common.SubjectType;
import com.mysoft.alpha.config.AlphaConfig;
import com.mysoft.alpha.entity.*;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultCode;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.*;
import com.mysoft.alpha.util.DateUtil;
import com.mysoft.alpha.util.IdNumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 客户-产品Excel主表(CpExcelMst)表控制层
 *
 * @author makejava
 * @since 2020-08-02 16:14:07
 */

/**
 * @author qiuyu
 */
@RestController
@RequestMapping("/api/admin/v1/pri/cpExcel")
public class CpExcelController {
    @Autowired
    AlphaConfig alphaConfig;

    @Autowired
    AlphaSubjectService alphaSubjectService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    private CpExcelService cpExcelService;

    @Autowired
    private CustomerProductService customerProductService;

    @Autowired
    private BatchFeeService batchFeeService;

    /**
     * 采购单审核
     *
     * @param map
     * @return
     */
    @PostMapping("/detail/verify")
    @Transactional
    public Result updateCustomerEnterprise(@RequestBody Map<String, String> map) throws CustomException {
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(operator);

        String opt = map.get("opt");
        String id = map.get("id");
        String reson = map.get("reson");

        List<CpExcelDetail> cpExcelDetailList = cpExcelService.findDetailByCustomerSubjectId(Integer.parseInt(id));
        for (CpExcelDetail cpExcelDetail : cpExcelDetailList) {
            CpExcelMst cpExcelMst = cpExcelService.getMstById(cpExcelDetail.getCpExcelMstId());
            if (cpExcelMst.getChargeSubjectId().intValue() == user.getAlphaSubjectId().intValue()) {
                if (cpExcelDetail.getState().intValue() == CustomStatus.STATUS3.value() ||
                        cpExcelDetail.getState().intValue() == CustomStatus.STATUS4.value()) {
                    if ("1".equals(opt)) {// 1 通过
                        cpExcelDetail.setState(CustomStatus.STATUS5.value());// ，5 = 审核通过可付费
                    } else {// 2未通过
                        cpExcelDetail.setState(CustomStatus.STATUS_5.value());//
                    }
                    cpExcelDetail.setOperator(operator);
                    cpExcelDetail.setConfirmRemark(reson);
                }
            }
            cpExcelService.saveDetail(cpExcelDetail);
        }
        return ResultFactory.buildSuccessResult("操作成功");
    }

    /**
     * 明细list
     *
     * @return
     */
    @GetMapping("/detailList")
    public Result detailList(// @RequestBody Map<String, String> map
                             @RequestParam() Integer step, @RequestParam() String name,
                             @RequestParam() String recordNumber, @RequestParam() String productName,
                             @RequestParam() String outTradeNo) throws CustomException {

        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(operator);
        // 根据付费方或收款方查询Excel主表
        List<CpExcelMst> cpExcelMstList = new ArrayList<>();
        if (operator.equals("admin")) {
            cpExcelMstList = cpExcelService.findMstAll();
        } else {
            switch (step) {
                case 1:// 客户单维护
                    cpExcelMstList = cpExcelService.findMstByPaySubjectIdOrderById(user.getAlphaSubjectId());
                    break;
                //                case 2://采购单维护-审核,改到AlphaSubject中
                //                    cpExcelMstList = cpExcelService.findMstByChargeSubjectIdOrderById(user.getAlphaSubjectId());
                //                    break;
                case 3:// 采购单维护-付费
                    cpExcelMstList = cpExcelService.findMstByPaySubjectIdOrderById(user.getAlphaSubjectId());
                    break;
            }
        }
        // 根据Excel主表状态查询Excel明细
        List<CpExcelDetail> returnList = new ArrayList<>();
        for (CpExcelMst cpExcelMst : cpExcelMstList) {
            List<CpExcelDetail> cpExcelDetailList = new ArrayList<>();
            switch (step) {
                case 3:
                    cpExcelDetailList = cpExcelService.findDetailByParamsOrderByIdAsc(cpExcelMst.getId(),
                            Arrays.asList(CustomStatus.STATUS5.value()), name, recordNumber, productName, outTradeNo);
                    break;
                case 2:
                    cpExcelDetailList = cpExcelService.findDetailByParamsOrderByIdAsc(cpExcelMst.getId(),
                            Arrays.asList(CustomStatus.STATUS3.value(), CustomStatus.STATUS4.value()), name,
                            recordNumber, productName, outTradeNo);
                    break;
                case 1:
                    cpExcelDetailList = cpExcelService.findDetailByParamsOrderByIdAsc(cpExcelMst.getId(),
                            Arrays.asList(CustomStatus.STATUS3.value(), CustomStatus.STATUS4.value(),
                                    CustomStatus.STATUS_5.value()), name, recordNumber, productName, outTradeNo);
                    break;
            }
            // 1，2根据Excel 明细查询CustomerProduct
            // 3根据Excel明细查询付费主表
            for (CpExcelDetail cpExcelDetail : cpExcelDetailList) {
                if (step == 1 || step == 2) {
                    cpExcelDetail.setCustomerSubject(
                            alphaSubjectService.getAlphaSubjectById(cpExcelDetail.getCustomerSubjectId()));
                    cpExcelDetail.setCustomerProducts(customerProductService
                            .findBySourceDetailIdIsInOrderById(Arrays.asList(cpExcelDetail.getId())));
                } else {
                    List<BatchFeeMst> batchFeeMstList = batchFeeService.findMstByCpExcelDetailId(cpExcelDetail.getId());
                    for (BatchFeeMst batchFeeMst : batchFeeMstList) {
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
                    cpExcelDetail.setBatchFeeMsts(batchFeeMstList);
                }
                cpExcelMst.setPaySubject(alphaSubjectService.getAlphaSubjectById(cpExcelMst.getPaySubjectId()));
                cpExcelMst.setChargeSubject(alphaSubjectService.getAlphaSubjectById(cpExcelMst.getChargeSubjectId()));
                cpExcelDetail.setCpExcelMst(cpExcelMst);
                cpExcelDetail.setProduct(productService.getProductById(cpExcelDetail.getProductId()));
                switch (cpExcelDetail.getState()) {
                    case 3:
                        cpExcelDetail.setStateReason(CustomStatus.STATUS3.getReasonPhrase());
                        break;
                    case 4:
                        cpExcelDetail.setStateReason(CustomStatus.STATUS4.getReasonPhrase());
                        break;
                    case 5:
                        cpExcelDetail.setStateReason(CustomStatus.STATUS5.getReasonPhrase());
                        break;
                    case -5:
                        cpExcelDetail.setStateReason(CustomStatus.STATUS_5.getReasonPhrase());
                        break;
                    default:
                        cpExcelDetail.setStateReason(CustomStatus.STATUS_1.getReasonPhrase());
                        break;
                }
                returnList.add(cpExcelDetail);
            }
        }
        return ResultFactory.buildSuccessResult(returnList);
    }

    @GetMapping("/deleteDetail/byId")
    @Transactional
    public Result deleteCpExcelDetail(@RequestParam() Integer detailId) throws CustomException {
        CpExcelDetail cpExcelDetail = cpExcelService.getDetailById(detailId);
        if (cpExcelDetail.getState() > CustomStatus.STATUS4.value()) {
            throw new CustomException(0, "已审核通过，不可删除");
        } else {
            // 因为可能还有其他记录使用产品和客户信息，所以不删除因为导入明细而增加的产品记录和客户记录
            //            productService.deleteBySourceTypeAndSourceDetailId(SubjectType.TYPE1.value(), detailId);
            //            alphaSubjectService.deleteBySourceTypeAndSourceDetailId(SubjectType.TYPE1.value(), detailId);
            customerProductService.deleteBySourceTypeAndSourceDetailId(SubjectType.TYPE1.value(), detailId);
            cpExcelService.deleteDetailById(detailId);
            return ResultFactory.buildSuccessResult("删除成功");
        }
    }

    /**
     * 客户单维护-【客户单excel上传】
     *
     * @param map
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/excelUpload")
    @Transactional
    public Result excelUpload(@RequestParam Map<String, String> map, @RequestParam("file") MultipartFile file)
            throws Exception {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 操作员
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(operator);
        // 付费企业-登录用户企业
        AlphaSubject payAS = alphaSubjectService.getAlphaSubjectById(user.getAlphaSubjectId());
        // 收费企业-页面选择企业
        String chargeId = map.get("chargeid");
        AlphaSubject chargeAS = alphaSubjectService.getAlphaSubjectById(Integer.parseInt(chargeId));
        // 文件上传目录
        String folder = alphaConfig.getUploadFolder();
        String fileURL = alphaConfig.getFileUrl();
        String oriFileName = file.getOriginalFilename();
        if (cpExcelService.isExistFileName(oriFileName, chargeId)) {
            return ResultFactory.buildFailResult(oriFileName + "文件名已经存在");
        }
        String suffix = oriFileName.substring(oriFileName.lastIndexOf('.'));
        String prefix = oriFileName.substring(0, oriFileName.lastIndexOf('.'));
        File uploadFileFolder = new File(folder);
        File localFile = new File(uploadFileFolder, prefix + System.currentTimeMillis() + suffix);
        if (!localFile.getParentFile().exists()) {
            localFile.getParentFile().mkdirs();
        }
        fileURL += localFile.getName();
        // 存储文件主表
        CpExcelMst cpExcelMst = new CpExcelMst();
        cpExcelMst.setPaySubjectId(payAS.getId());
        cpExcelMst.setChargeSubjectId(chargeAS.getId());
        cpExcelMst.setFileName(file.getOriginalFilename());
        cpExcelMst.setUrl(fileURL);
        cpExcelMst.setIp(request.getRemoteAddr());
        cpExcelMst.setSourceType(SourceType.TYPE1.value());
        cpExcelMst.setOperator(operator);
        cpExcelMst.setCreateTime(new Date());
        cpExcelMst = cpExcelService.saveMst(cpExcelMst);
        System.out.println("----------------------saveCpExcelMst");
        // 存储文件明细
        saveCpExcelDetail(file, cpExcelMst);
        System.out.println("----------------------saveCpExcelDetail");
        //        //触发申请服务产品,改为付费时申请服务产品
        //        saveCustomerProduct(cpExcelMst);
        //        System.out.println("----------------------saveCustomerProduct");

        file.transferTo(localFile);

        return ResultFactory.buildResult(ResultCode.SUCCESS, "上传成功", fileURL);

    }

    /**
     * 处理文件
     *
     * @param cpExcelMst
     * @param file
     * @return
     * @throws IOException
     */
    private void saveCpExcelDetail(MultipartFile file, CpExcelMst cpExcelMst) throws Exception {

        // 明细
        List<CpExcelDetail> cpExcelDetailList = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();// 获取表单所有的行
            System.out.println("physicalNumberOfRows=" + physicalNumberOfRows);// 行数包括标题行
            // 处理excel明细
            for (int i = 1; i < physicalNumberOfRows; i++) {
                CpExcelDetail cpExcelDetail = parseOneRow(cpExcelMst, sheet, i);
                cpExcelDetailList.add(cpExcelDetail);
            }
            // cpExcelService.saveAllDetails(cpExcelDetailList);
            workbook.close();
        } catch (IOException e) {
            throw new CustomException(0, "文件：" + file.getOriginalFilename() + "，存储异常");
        }

    }

    private CpExcelDetail parseOneRow(CpExcelMst cpExcelMst, Sheet sheet, int irows) throws Exception {
        CpExcelDetail cpExcelDetail = new CpExcelDetail();
        cpExcelDetail.setCpExcelMstId(cpExcelMst.getId());
        cpExcelDetail.setOperator(cpExcelMst.getOperator());
        cpExcelDetail.setCreateTime(new Date());
        Row row = sheet.getRow(irows);
        cpExcelDetail.setOrdered(row.getRowNum() + 1);// 记录数据从第二行开始

        String outTradeNo;// 保单号
        String productName = null;// 保险产品名称
        String customerName = null;// 客户姓名
        String customerTypeName = null;// 证件类型名称
        // Integer recordType = 1;//证件类型，默认身份证
        String recordNumber = null;// 证件号
        Date effectiveDate = null;// 生效日
        Date closingDate = null;// 截止日
        String sex = null;// 性别
        Integer age = null;// 年龄
        String location = null;// 所在地
        AlphaSubject customer = null;
        Product product = new Product();

        for (int icell = 0; icell <= 12; icell++) {
            Cell cell = row.getCell(icell);
            String str = null;

            switch (cell.getCellType()) {
                case STRING:
                    str = cell.getStringCellValue().trim();
                    break;
                case NUMERIC:
                    str = Double.valueOf(cell.getNumericCellValue()).intValue() + "";
                    break;
                default:
                    break;
            }
            System.out.println("icell:" + icell + ",type:" + cell.getCellType() + ",DataFormat:" +
                    cell.getCellStyle().getDataFormatString() + ",data:" + str);
            switch (icell) {
                case 0:// 序号
                    if (StringUtils.isEmpty(str)) {
                        cpExcelDetail.setSeqNumber(irows + "");
                    } else {
                        cpExcelDetail.setSeqNumber(str);
                    }
                    break;
                case 1:// 保单号
                    if (StringUtils.isEmpty(str)) {
                        throw new CustomException(0, "第" + (irows + 1) + "行，第" + (icell + 1) + "列，保单号不能为空");
                    } else {
                        if (cpExcelService.isExistOutTradeNo(str, cpExcelMst.getChargeSubjectId())) {
                            throw new CustomException(0, "第" + (irows + 1) + "行，第" + (icell + 1) + "列，保单号已经存在");
                        }
                        outTradeNo = str;
                        cpExcelDetail.setOutTradeNo(outTradeNo);
                    }
                    break;
                case 2:// 产品名称
                    if (StringUtils.isEmpty(str)) {
                        throw new CustomException(0, "第" + (irows + 1) + "行，第" + (icell + 1) + "列，产品名称不能为空");
                    } else {
                        productName = str.trim();
                        cpExcelDetail.setProductName(productName);
                    }
                    break;
                case 3:// 客户姓名
                    if (StringUtils.isEmpty(str)) {
                        throw new CustomException(0, "第" + (irows + 1) + "行，第" + (icell + 1) + "列，客户姓名不能为空");
                    } else {
                        customerName = str.trim();
                        cpExcelDetail.setCustomerName(customerName);
                    }
                    break;
                case 4:// 证件类型
                    if (StringUtils.isEmpty(str)) {
                        customerTypeName = "空";
                    } else {
                        customerTypeName = str.trim();
                    }
                    cpExcelDetail.setCustomerType(customerTypeName);
                    break;
                case 5:// 证件号
                    if (StringUtils.isEmpty(str)) {
                        recordNumber = "空";
                    } else {
                        recordNumber = str.trim();
                    }
                    break;
                case 6:// 联系电话
                    if (StringUtils.isEmpty(str)) {
                        throw new CustomException(0, "第" + (irows + 1) + "行，第" + (icell + 1) + "列，联系电话不能为空");
                    } else {
                        cpExcelDetail.setCustomerPhone(str.trim());
                    }
                    break;
                case 7:// 生效日
                    if (StringUtils.isEmpty(str)) {
                        throw new CustomException(0, "第" + (irows + 1) + "行，第" + (icell + 1) + "列，生效日不能为空");
                    } else {
                        try {
                            effectiveDate = DateUtil.convertExcelToDate(str, cell.getCellStyle().getDataFormatString());
                        } catch (Exception e) {
                            throw new CustomException(0, "第" + (irows + 1) + "行，第" + (icell + 1) + "列，生效日不符合格式要求");
                        }
                        cpExcelDetail.setEffectiveDate(effectiveDate);
                    }
                    break;
                case 8:// 截止日
                    if (StringUtils.isEmpty(str)) {
                        throw new CustomException(0, "第" + (irows + 1) + "行，第" + (icell + 1) + "列，截止日不能为空");
                    } else {
                        try {
                            closingDate = DateUtil.convertExcelToDate(str, cell.getCellStyle().getDataFormatString());
                        } catch (Exception e) {
                            throw new CustomException(0, "第" + (irows + 1) + "行，第" + (icell + 1) + "列，截止日不符合格式要求");
                        }
                        cpExcelDetail.setClosingDate(closingDate);
                    }
                    break;
                case 9:// 性别
                    if (StringUtils.isEmpty(str)) {
                        if ((!customerTypeName.equals("身份证")) || recordNumber.equals("空") ||
                                StringUtils.isEmpty(recordNumber)) {
                            throw new CustomException(0, "第" + irows + "行，无法获取性别信息");
                        } else {
                            sex = IdNumUtils.getSex(recordNumber);
                        }
                    } else {
                        sex = str.trim();
                    }
                    cpExcelDetail.setSex(sex);
                    break;
                case 10:// 年龄
                    if (str == null || str.equals("")) {
                        if ((!customerTypeName.equals("身份证")) || recordNumber.equals("空") ||
                                StringUtils.isEmpty(recordNumber)) {
                            throw new CustomException(0, "第" + (irows + 1) + "行，无法获取年龄信息");
                        } else {
                            age = IdNumUtils.getAge(recordNumber);
                        }
                    } else {
                        age = Integer.valueOf(str.trim());
                    }
                    cpExcelDetail.setAge(age.toString());
                    break;
                case 11:// 所在地
                    if (str == null || str.equals("")) {
                        if ((!customerTypeName.equals("身份证")) || recordNumber.equals("空") ||
                                StringUtils.isEmpty(recordNumber)) {
                            throw new CustomException(0, "第" + irows + 1 + "行，无法获取所在地信息");
                        } else {
                            location = IdNumUtils.getProvince(recordNumber);
                        }
                    } else {
                        location = str.trim();
                    }
                    cpExcelDetail.setLocation(location);
                    break;
                case 12:// 备注
                    if (!StringUtils.isEmpty(str)) {
                        cpExcelDetail.setRemark(str);
                    }
                    break;
                default:
                    break;
            }
        }
        // 先存一次产生detailid
        cpExcelDetail = cpExcelService.saveDetail(cpExcelDetail);
        if (productService.isExistProduct(productName)) {
            product = productService.findByName(productName);
        } else {
            Product pnew = new Product();
            pnew.setName(productName);
            pnew.setAlphaSubjectId(cpExcelMst.getPaySubjectId());
            pnew.setProductType(ProductType.TYPE2.value());
            pnew.setSourceType(SourceType.TYPE1.value());
            pnew.setSourceId(cpExcelMst.getId());
            pnew.setSourceDetailId(cpExcelDetail.getId());
            pnew.setEnabled(1);
            pnew.setOperator(cpExcelMst.getOperator());
            pnew.setCreateTime(new Date());
            product = productService.save(pnew);
        }

        if (alphaSubjectService.isExistCustomerSubject(customerTypeName, recordNumber, customerName, sex,
                cpExcelDetail.getCustomerPhone())) {
            customer = alphaSubjectService
                    .findByParams(customerTypeName, recordNumber, customerName, sex, cpExcelDetail.getCustomerPhone());
        } else {
            AlphaSubject cnew = new AlphaSubject();
            cnew.setSubjectType(SubjectType.TYPE1.value());
            cnew.setRecordType(customerTypeName);
            cnew.setRecordNumber(recordNumber);
            cnew.setName(customerName);
            cnew.setPhone(cpExcelDetail.getCustomerPhone());
            cnew.setSourceType(SourceType.TYPE1.value());
            cnew.setSourceId(cpExcelMst.getId());
            cnew.setSourceDetailId(cpExcelDetail.getId());
            cnew.setEnabled(1);
            cnew.setOperator(cpExcelMst.getOperator());
            cnew.setCreateTime(new Date());
            cnew.setAge(age);
            cnew.setSex(sex);
            cnew.setLocation(location);
            customer = alphaSubjectService.save(cnew);
        }
        if (cpExcelService.isExistOutTradeNo(customer.getId(), product.getId(), effectiveDate, closingDate)) {
            throw new CustomException(0, "第" + irows + "行，保单已经存在");
        }
        cpExcelDetail.setProductId(product.getId());
        cpExcelDetail.setCustomerSubjectId(customer.getId());
        if (cpExcelDetail.getClosingDate().before(cpExcelDetail.getEffectiveDate())) {
            throw new CustomException(0, "结束日期早于开始日期");
        }
        cpExcelDetail.setState(CustomStatus.STATUS3.value());// 3 = 申请通过待审核
        return cpExcelService.saveDetail(cpExcelDetail);
    }


}