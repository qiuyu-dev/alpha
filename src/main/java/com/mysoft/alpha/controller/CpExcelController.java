package com.mysoft.alpha.controller;

import com.mysoft.alpha.config.AlphaConfig;
import com.mysoft.alpha.entity.*;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultCode;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.*;
import com.mysoft.alpha.util.DateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 客户-产品Excel主表(CpExcelMst)表控制层
 *
 * @author makejava
 * @since 2020-08-02 16:14:07
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

    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减1
            }
        }
        return age;
    }

    /**
     * 采购单审核
     *
     * @param map
     * @return
     */
    // @PostMapping("/api/admin/content/purchaseorder")
    @PostMapping("/detail/verify")
    @Transactional
    public Result updateCustomerEnterprise(@RequestBody Map<String, String> map) throws CustomException {
        System.out.println("opt=" + map.get("opt"));
        System.out.println("id=" + map.get("id"));
        System.out.println("reson=" + map.get("reson"));
        String opt = map.get("opt");
        String id = map.get("id");
        String reson = map.get("reson");

        CpExcelDetail cpExcelDetail = cpExcelService.getDetailById(Integer.parseInt(id));
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        cpExcelDetail.setOperator(operator);
        cpExcelDetail.setClosingDate(new Date());
        cpExcelDetail.setConfirmRemark(reson);

        if ("1".equals(opt)) {// 1 通过
            cpExcelDetail.setState(5);// ，5 = 审核通过可付费
        } else {// 2未通过
            cpExcelDetail.setState(-5);//

        }
        List<CustomerProduct> customerProductList =
                customerProductService.findBySourceDetailIdIsInOrderById(Arrays.asList(cpExcelDetail.getId()));
        for (CustomerProduct customerProduct : customerProductList) {
            customerProduct.setState(cpExcelDetail.getState());
        }
        cpExcelService.saveDetail(cpExcelDetail);
        customerProductService.saveAll(customerProductList);
        return ResultFactory.buildSuccessResult("操作成功");
    }

    /**
     * 明细list
     *
     * @return
     */
    @GetMapping("/detailList")
    public Result detailList(@RequestParam() Integer step) throws CustomException {
        //        System.out.println("----------------------CpExcelController:detailList,step:");
        //        Integer step = map.get("step");
        System.out.println("----------------------CpExcelController:detailList,step:" + step);
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(operator);
        //        List<Integer> statusList = Stream.of(3, 4).collect(Collectors.toList());
        //根据付费方或收款方查询Excel主表
        List<CpExcelMst> cpExcelMstList = new ArrayList<>();
        if (operator.equals("admin")) {
            cpExcelMstList = cpExcelService.findMstAll();
        } else {
            switch (step) {
                case 1:
                    cpExcelMstList = cpExcelService.findMstByPaySubjectIdOrderById(user.getAlphaSubjectId());
                    break;
                case 2:
                    cpExcelMstList = cpExcelService.findMstByChargeSubjectIdOrderById(user.getAlphaSubjectId());
                    break;
                default:
                    cpExcelMstList = cpExcelService.findMstByPaySubjectIdOrderById(user.getAlphaSubjectId());
                    break;

            }
        }
        //        System.out
        //                .println("----------------------CpExcelController:detailList,cpExcelMstList:" + cpExcelMstList.size());
        //根据Excel主表状态查询Excel明细
        List<CpExcelDetail> returnList = new ArrayList<>();

        for (CpExcelMst cpExcelMst : cpExcelMstList) {
            List<CpExcelDetail> cpExcelDetailList = new ArrayList<>();
            switch (step) {
                //                case 1:
                //                    cpExcelDetailList = cpExcelService.findDetailByCpExcelMstId(cpExcelMst.getId());
                //                    break;
                //
                //                case 2:
                //                    cpExcelDetailList = cpExcelService
                //                            .findDetailByCpExcelMstIdAndStateInOrderByIdAsc(cpExcelMst.getId(), Arrays.asList(3, 4));
                //                    break;
                case 3:

                    cpExcelDetailList = cpExcelService
                            .findDetailByCpExcelMstIdAndStateInOrderByIdAsc(cpExcelMst.getId(), Arrays.asList(5));
                    break;
                default:
                    cpExcelDetailList = cpExcelService
                            .findDetailByCpExcelMstIdAndStateInOrderByIdAsc(cpExcelMst.getId(), Arrays.asList(3, 4));
                    break;
            }
            System.out.println(
                    "----------------------CpExcelController:detailList,cpExcelDetailList:" + cpExcelDetailList.size());

            //1，2根据Excel 明细查询CustomerProduct
            //3根据Excel明细查询付费主表
            for (CpExcelDetail cpExcelDetail : cpExcelDetailList) {

                if (step == 1 || step == 2) {
                    //                    cpExcelDetail.setProduct(productService.getProductById(cpExcelDetail.getProductId()));
                    cpExcelDetail.setCustomerSubject(
                            alphaSubjectService.getAlphaSubjectById(cpExcelDetail.getCustomerSubjectId()));
                    //                    cpExcelDetail.setCpExcelMst(cpExcelMst);
                    cpExcelDetail.setCustomerProducts(customerProductService
                            .findBySourceDetailIdIsInOrderById(Arrays.asList(cpExcelDetail.getId())));
                    //                    returnList.add(cpExcelDetail);
                } else {
                    cpExcelDetail.setBatchFeeMsts(batchFeeService.findMstByCpExcelDetailId(cpExcelDetail.getId()));
                }
                cpExcelDetail.setCpExcelMst(cpExcelMst);
                returnList.add(cpExcelDetail);
            }
        }
        return ResultFactory.buildSuccessResult(returnList);
    }

    //    @GetMapping("/deleteMst/byId")
    @Transactional
    public Result deleteCpExcelMst(@RequestParam() Integer mstId) throws CustomException {

        alphaSubjectService.deleteBySourceTypeAndSourceId(1, mstId);
        productService.deleteBySourceTypeAndSourceId(1, mstId);
        customerProductService.deleteBySourceTypeAndSourceId(1, mstId);
        cpExcelService.deleteDetailByCpExcelMstId(mstId);
        cpExcelService.deleteMstById(mstId);
        return ResultFactory.buildSuccessResult("删除成功");
    }


    @GetMapping("/deleteDetail/byId")
    @Transactional
    public Result deleteCpExcelDetail(@RequestParam() Integer detailId) throws CustomException {
        CpExcelDetail cpExcelDetail = cpExcelService.getDetailById(detailId);
        if (cpExcelDetail.getState() > 4) {
            throw new CustomException(0, "已审核通过，不可删除");
        } else {
            System.out.println("deleteBySourceTypeAndSourceDetailId");
            productService.deleteBySourceTypeAndSourceDetailId(1, detailId);
            System.out.println("deleteBySourceTypeAndSourceDetailId");
            alphaSubjectService.deleteBySourceTypeAndSourceDetailId(1, detailId);
            System.out.println("deleteBySourceTypeAndSourceDetailId");
            customerProductService.deleteBySourceTypeAndSourceDetailId(1, detailId);
            System.out.println();
            cpExcelService.deleteDetailById(detailId);
            return ResultFactory.buildSuccessResult("删除成功");
        }
    }

    /**
     * 上传excel完成客户单上传
     *
     * @param map
     * @param file
     * @return
     */
    @PostMapping("/excelUpload")
    @Transactional //测试使用事务可以吗？
    public Result excelUpload(@RequestParam Map<String, String> map, @RequestParam("file") MultipartFile file) throws
    Exception
    {
        //操作员
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(operator);
        //付费企业
        AlphaSubject payAS = alphaSubjectService.getAlphaSubjectById(user.getAlphaSubjectId());
        //收费企业
        String chargeId = map.get("chargeid");
        AlphaSubject chargeAS = alphaSubjectService.getAlphaSubjectById(Integer.parseInt(chargeId));
        //文件
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
        if (!localFile.getParentFile().exists())
            localFile.getParentFile().mkdirs();

        CpExcelMst cpExcelMst = cpExcelService.saveMst(new CpExcelMst());
        cpExcelMst.setPaySubjectId(payAS.getId());
        cpExcelMst.setChargeSubjectId(chargeAS.getId());
        cpExcelMst.setFileName(file.getOriginalFilename());
        cpExcelMst.setOperator(operator);
        cpExcelMst.setCreateTime(new Date());
        cpExcelMst = cpExcelService.saveMst(cpExcelMst);
        System.out.println("----------------------saveCpExcelMst");
//        try {
            //存储文件明细
            saveCpExcelDetail(file, cpExcelMst);
            System.out.println("----------------------saveCpExcelDetail");
            //触发申请服务产品
            saveCustomerProduct(cpExcelMst);
            System.out.println("----------------------saveCustomerProduct");
            file.transferTo(localFile);
            fileURL += localFile.getName();
            return ResultFactory.buildResult(ResultCode.SUCCESS, "上传成功", fileURL);
//        } catch (Exception e) {
//            int mstId = cpExcelMst.getId();
//            System.out.println("----------------------Exception，mstId" + mstId);
//            alphaSubjectService.deleteBySourceTypeAndSourceId(1, mstId);
//            productService.deleteBySourceTypeAndSourceId(1, mstId);
//            cpExcelService.deleteDetailByCpExcelMstId(mstId);
//            cpExcelService.deleteMstById(mstId);
//            if (e instanceof CustomException) {
//                CustomException customException = (CustomException) e;
//                System.out.println("----------------------Exception:" + customException.getMsg());
//                return ResultFactory.buildFailResult(customException.getMsg());
//            } else {
//                return ResultFactory.buildFailResult(e.));
//            }
//
//        }
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

        //明细
        List<CpExcelDetail> cpExcelDetailList = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file.getInputStream());

            Sheet sheet = workbook.getSheetAt(0);
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();// 获取表单所有的行
            System.out.println("physicalNumberOfRows=" + physicalNumberOfRows);// 行数包括标题行
            //处理excel明细
            for (int i = 1; i < physicalNumberOfRows; i++) {
                CpExcelDetail cpExcelDetail = parseOneRow(cpExcelMst, sheet, i);
                cpExcelDetailList.add(cpExcelDetail);
            }
            //                    cpExcelService.saveAllDetails(cpExcelDetailList);
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

        String outTradeNo;//保单号
        String productName = null;//保险产品名称
        String customerName = null;//客户姓名
        String customerTypeName;//证件类型名称
        Integer recordType = 1;//证件类型，默认身份证
        String recordNumber = null;//证件号
        Date effectiveDate = null;//生效日
        Date closingDate = null;//截止日
        String sex;//性别
        String age;//年龄
        String location;//所在地
        AlphaSubject customer = null;
        Product product = new Product();
        System.out.println("for");// 行数包括标题行

        for (int icell = 0; icell <= 12; icell++) {
            Cell cell = row.getCell(icell);
            String str = null;

            switch (cell.getCellType()) {
                case STRING:
                    str = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    str = Double.valueOf(cell.getNumericCellValue()).intValue() + "";
                    break;
                default:
                    break;
            }
            System.out.println("icell:" + icell + ",type:" +cell.getCellType()+",DataFormat:"+ cell.getCellStyle().getDataFormatString()+",data:"+str
            );
            switch (icell) {
                case 0://序号
                    if (str != null) {
                        cpExcelDetail.setSeqNumber(str);
                    } else {
                        cpExcelDetail.setSeqNumber(irows + "");
                    }
                    break;
                case 1://保单号
                    if (str != null) {
                        if (cpExcelService.isExistOutTradeNoe(str)) {
                            throw new CustomException(0, "第" + irows + "行，第" + icell + "列，保单号已经存在");
                        }
                        outTradeNo = str;
                        cpExcelDetail.setOutTradeNo(outTradeNo);
                    } else {
                        throw new CustomException(0, "第" + irows + "行，第" + icell + "列，保单号不能为空");
                    }
                    break;
                case 2://产品名称
                    if (str != null) {
                        productName = str;
                        cpExcelDetail.setProductName(productName);
                    } else {
                        throw new CustomException(0, "第" + irows + "行，第" + icell + "列，产品名称不能为空");
                    }
                    break;
                case 3://客户姓名
                    if (str != null) {
                        customerName = str;
                        cpExcelDetail.setCustomerName(customerName);
                    } else {
                        throw new CustomException(0, "第" + irows + "行，第" + icell + "列，客户姓名不能为空");
                    }
                    break;
                case 4://证件类型
                    if (str != null) {
                        customerTypeName = str;
                        cpExcelDetail.setCustomerType(customerTypeName);
                        if (customerTypeName != null && customerTypeName.equals("身份证")) {
                            recordType = 1;
                        } else if (customerTypeName != null && customerTypeName.equals("护照")) {
                            recordType = 2;
                        }
                    }
                    break;
                case 5://证件号
                    if (str != null) {
                        recordNumber = str;
                    } else {
                        throw new CustomException(0, "第" + irows + "行，第" + icell + "列，证件号不能为空");
                    }
                    break;
                case 6://联系电话
                    if (str != null) {
                        cpExcelDetail.setCustomerPhone(str);
                    } else {
                        throw new CustomException(0, "第" + irows + "行，第" + icell + "列，联系电话不能为空");
                    }
                    break;
                case 7://生效日
                    if (str != null) {
                        try {
                            effectiveDate = DateUtil.convertExcelToDate(str, cell.getCellStyle().getDataFormatString());
                        } catch (Exception e) {
                            throw new CustomException(0, "第" + irows + "行，第" + icell + "列，生效日不符合格式要求");
                        }
                        cpExcelDetail.setEffectiveDate(effectiveDate);
                    } else {
                        throw new CustomException(0, "第" + irows + "行，第" + icell + "列，生效日不能为空");
                    }
                    break;
                case 8://截止日
                    if (str != null) {
                        try {
                            closingDate = DateUtil.convertExcelToDate(str, cell.getCellStyle().getDataFormatString());
                        } catch (Exception e) {
                            throw new CustomException(0, "第" + irows + "行，第" + icell + "列，截止日不符合格式要求");
                        }
                        cpExcelDetail.setClosingDate(closingDate);
                    } else {
                        throw new CustomException(0, "第" + irows + "行，第" + icell + "列，截止日不能为空");
                    }
                    break;
                case 9://性别
                    if (str != null) {
                        sex = str;
                        cpExcelDetail.setSex(sex);
                    }
                    break;
                case 10://年龄
                    if (str != null) {
                        age = str;
                        cpExcelDetail.setAge(age);
                    }
                    break;
                case 11://所在地
                    if (str != null) {
                        location = str;
                        cpExcelDetail.setLocation(location);
                    }
                    break;
                case 12://备注
                    if (str != null) {
                        cpExcelDetail.setRemark(str);
                    }
                    break;
                default:
                    break;
            }
        }

         //先存一次产生detailid
        cpExcelDetail = cpExcelService.saveDetail(cpExcelDetail);
        if (productService.isExistProduct(productName)) {
            product = productService.findByName(productName);
        } else {
            Product pnew = new Product();
            pnew.setName(productName);
            pnew.setAlphaSubjectId(cpExcelMst.getPaySubjectId());
            pnew.setProductType(2);
            pnew.setSourceType(1);
            pnew.setSourceId(cpExcelMst.getId());
            pnew.setSourceDetailId(cpExcelDetail.getId());
            pnew.setEnabled(1);
            pnew.setOperator(cpExcelMst.getOperator());
            pnew.setCreateTime(new Date());
            product = productService.save(pnew);
        }

//        System.out.println("alphaSubjectService:");// 行数包括标题行
        if (alphaSubjectService.isExistAlphaSubject(1, recordType, recordNumber)) {
            customer = alphaSubjectService.findBySubjectTypeAndRecordTypeAndRecordNumber(1, recordType, recordNumber);
        } else {
            AlphaSubject cnew = new AlphaSubject();
            cnew.setSubjectType(1);
            cnew.setRecordType(recordType);
            cnew.setRecordNumber(recordNumber);
            cnew.setName(customerName);
            cnew.setPhone(cpExcelDetail.getCustomerPhone());
            cnew.setSourceType(1);
            cnew.setSourceId(cpExcelMst.getId());
            cnew.setSourceDetailId(cpExcelDetail.getId());
            cnew.setEnabled(1);
            cnew.setOperator(cpExcelMst.getOperator());
            cnew.setCreateTime(new Date());
            customer = alphaSubjectService.save(cnew);
        }
       if (cpExcelService.isExistOutTradeNoe(customer.getId(), product.getId(), effectiveDate, closingDate)) {
            throw new CustomException(0, "第" + irows + "行，保单已经存在");
        }
        cpExcelDetail.setProductId(product.getId());
        cpExcelDetail.setCustomerSubjectId(customer.getId());
        if (cpExcelDetail.getSex() == null && customer.getRecordType() == 1) {
            if (Integer.valueOf(customer.getRecordNumber().substring(16, 17)) % 2 == 0) {
                cpExcelDetail.setSex("女");
            } else {
                cpExcelDetail.setSex("男");
            }
        }
        if (cpExcelDetail.getAge() == null && customer.getRecordType() == 1) {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Date dt = null;
            try {
                dt = df.parse(customer.getRecordNumber().substring(6, 14));
                cpExcelDetail.setAge(getAge(dt) + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (cpExcelDetail.getLocation() == null && customer.getRecordType() == 1) {
            cpExcelDetail.setLocation(getProvince(customer.getRecordNumber()));
        }

        if (cpExcelDetail.getClosingDate().before(cpExcelDetail.getEffectiveDate())) {
            throw new CustomException(0, "结束日期早于开始日期");
        }
        cpExcelDetail.setState(3);//3 = 申请通过待审核
        return cpExcelService.saveDetail(cpExcelDetail);
    }

    private void saveCustomerProduct(CpExcelMst cpExcelMst) throws CustomException {

        List<CpExcelDetail> cpExcelDetailList = cpExcelService.findDetailByCpExcelMstId(cpExcelMst.getId());
        for (CpExcelDetail cpExcelDetail : cpExcelDetailList) {
            List<CustomerProduct> customerProductList = new ArrayList<>();
            List<Product> productList = productService.findByAlphaSubjectId(cpExcelMst.getChargeSubjectId());
            if (productList.size() < 1) {
                throw new CustomException(0, "没有服务产品");
            }
            for (Product product : productList) {
                if (!(customerProductService.isExistOutTradeNoe(cpExcelMst.getChargeSubjectId(), product.getId(),
                        cpExcelDetail.getEffectiveDate(),
                        DateUtil.addTime(cpExcelDetail.getEffectiveDate(), Calendar.YEAR, 1)))) {
                    CustomerProduct customerProduct = new CustomerProduct();
                    customerProduct.setCustomerSubjectId(cpExcelDetail.getCustomerSubjectId());
                    customerProduct.setProductId(product.getId());
                    customerProduct.setEffectiveDate(cpExcelDetail.getEffectiveDate());
                    customerProduct
                            .setClosingDate(DateUtil.addTime(cpExcelDetail.getEffectiveDate(), Calendar.YEAR, 1));
                    customerProduct.setSourceType(1);
                    customerProduct.setSourceId(cpExcelDetail.getCpExcelMstId());
                    customerProduct.setSourceDetailId(cpExcelDetail.getId());
                    customerProduct.setOperator(cpExcelDetail.getOperator());
                    customerProduct.setCreateTime(new Date());
                    customerProduct.setState(cpExcelDetail.getState());//3 = 申请通过待审核
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

    public String getProvince(String id) {
        String[] a =
                {"11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42",
                        "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81",
                        "82"};

        String[] b =
                {"北京市", "天津市", "河北省", "山西省", "内蒙古自治区", "辽宁省", "吉林省", "黑龙江省", "上海市", " 江苏省", "浙江省", "安徽省", "福建省", " 江西省",
                        "山东省", " 河南省", "湖北省", " 湖南省", "广东省", " 广西壮族自治区", "海南省", "重庆市", "四川省", "贵州省", "云南省", " 西藏自治区",
                        "陕西省", "甘肃省", "青海省", "宁夏回族自治区", "新疆维吾尔自治区", "台湾省", "香港特别行政区", "澳门特别行政区"};       //将省份全部放进数组b;
        String pos = (id.substring(0, 2));      //id.substring(0, 2)获取身份证的前两位；
        int i;
        for (i = 0; i < a.length; i++) {
            if (pos.equals(a[i])) {
                break;
            }
        }
        return b[i];  //获取b数组中的省份信息且输出省份;
    }


}