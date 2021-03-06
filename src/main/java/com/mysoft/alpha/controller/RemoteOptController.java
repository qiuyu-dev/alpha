package com.mysoft.alpha.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysoft.alpha.common.CustomStatus;
import com.mysoft.alpha.common.ProductType;
import com.mysoft.alpha.common.SourceType;
import com.mysoft.alpha.common.SubjectType;
import com.mysoft.alpha.entity.AlphaSubject;
import com.mysoft.alpha.entity.CpExcelDetail;
import com.mysoft.alpha.entity.CpExcelMst;
import com.mysoft.alpha.entity.Product;
import com.mysoft.alpha.entity.RemoteOptLog;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.AlphaSubjectService;
import com.mysoft.alpha.service.CpExcelService;
import com.mysoft.alpha.service.ProductService;
import com.mysoft.alpha.service.RemoteOptLogService;
import com.mysoft.alpha.service.UserService;
import com.mysoft.alpha.util.DateUtil;
import com.mysoft.alpha.util.IdNumUtils;
import com.mysoft.alpha.util.MyPage;

@RestController
@RequestMapping("/api/v1/remote/opt")
public class RemoteOptController {
	private static final Logger log = LoggerFactory.getLogger(RemoteOptController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private AlphaSubjectService alphaSubjectService;

	@Autowired
	private CpExcelService cpExcelService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private RemoteOptLogService remoteOptLogService;


	@GetMapping("/urlupload")
	@Transactional
	public Result urlUpload(@RequestParam Map<String, String> map, HttpServletRequest request) throws Exception {
		String username = map.get("username");
		String email = map.get("email");
		String outTradeNo = map.get("outTradeNo");// 保单号
		String productName = map.get("pName");// 保险产品名称
		String customerName = map.get("cName");// 客户姓名
		String customerTypeName = map.get("ctName");// 证件类型
		String recordNumber = map.get("recordNumber");// 证件号
		String phone = map.get("phone");// 联系电话
		String eDate = map.get("effectiveDate");//生效日
		String cDate = map.get("closingDate");//截止日
		String sex = map.get("sex");// 性别
		String age = map.get("age");// 年龄
		String location = map.get("location");// 所在地
		String remark = map.get("remark");//备注
		Date effectiveDate = null;// 生效日
		Date closingDate = null;// 截止日
		AlphaSubject customer = null;
		Product product = new Product();
		User user = userService.findByUsernameAndEmail(username, email);
		if (user == null) {
			throw new CustomException(0, "用户名或Email错误");
		}
		
		if (StringUtils.isEmpty(outTradeNo)) {
			throw new CustomException(0, "保单号不能为空");
		}
		
		if (StringUtils.isEmpty(productName)) {
			throw new CustomException(0, "保险产品不能为空");
		}
		
		if (StringUtils.isEmpty(customerName)) {
			throw new CustomException(0, "客户姓名不能为空");
		}
		
		if (StringUtils.isEmpty(customerTypeName)) {
			throw new CustomException(0, "证件类型不能为空");
		}		
		
		if (StringUtils.isEmpty(recordNumber)) {
			throw new CustomException(0, "证件号不能为空");
		}
		
		if (StringUtils.isEmpty(phone)) {
			throw new CustomException(0, "联系电话不能为空");
		}		
		
		if (StringUtils.isEmpty(eDate)) {
			throw new CustomException(0, "生效日期不能为空");
		} else {
			effectiveDate = DateUtil.convertToDate(eDate);// 生效日
		}		
		
		if (StringUtils.isEmpty(cDate)) {
			throw new CustomException(0, "截止日期不能为空");
		} else {
			closingDate = DateUtil.convertToDate(cDate);// 截止日
		}
		//判断日期有效性
        if (closingDate.before(effectiveDate)) {
            throw new CustomException(0, "结束日期早于开始日期");
        }

		// 付费企业
		AlphaSubject payAS = alphaSubjectService.getAlphaSubjectById(user.getAlphaSubjectId());
		// 存储文件主表
		CpExcelMst cpExcelMst = new CpExcelMst();
		cpExcelMst.setPaySubjectId(payAS.getId());
		cpExcelMst.setOperator(user.getUsername());
		cpExcelMst.setCreateTime(new Date());
		cpExcelMst.setIp(request.getRemoteAddr());//存ip
		cpExcelMst.setChargeSubjectId(1);//为不限
		cpExcelMst.setSourceType(SourceType.TYPE3.value());
		cpExcelMst = cpExcelService.saveMst(cpExcelMst);
		// 明细表
		CpExcelDetail cpExcelDetail = new CpExcelDetail();
		cpExcelDetail.setCpExcelMstId(cpExcelMst.getId());
		cpExcelDetail.setOperator(cpExcelMst.getOperator());
		cpExcelDetail.setCreateTime(new Date());

		if (cpExcelService.isExistOutTradeNo(outTradeNo, cpExcelMst.getChargeSubjectId())) {
			throw new CustomException(0, "保单号已经存在");
		}
		cpExcelDetail.setOutTradeNo(outTradeNo);
		cpExcelDetail.setProductName(productName);
		cpExcelDetail.setCustomerName(customerName);
		cpExcelDetail.setCustomerType(customerTypeName);
		cpExcelDetail.setEffectiveDate(effectiveDate);
		cpExcelDetail.setClosingDate(closingDate);
		cpExcelDetail.setCustomerPhone(phone);
		cpExcelDetail.setAge(age);
		cpExcelDetail.setSex(sex);
		cpExcelDetail.setLocation(location);		
		cpExcelDetail.setRemark(remark);        
		// 先存一次产生detailid
		cpExcelDetail = cpExcelService.saveDetail(cpExcelDetail);
		
		if (productService.isExistProduct(productName)) {
			product = productService.findByName(productName);
		} else {
			Product pnew = new Product();
			pnew.setName(productName);
			pnew.setAlphaSubjectId(cpExcelMst.getPaySubjectId());
			pnew.setProductType(ProductType.TYPE2.value());
			pnew.setSourceType(SourceType.TYPE3.value());
			pnew.setSourceId(cpExcelMst.getId());
			pnew.setSourceDetailId(cpExcelDetail.getId());
			pnew.setEnabled(1);
			pnew.setOperator(cpExcelMst.getOperator());
			pnew.setCreateTime(new Date());
			product = productService.save(pnew);
		}

		if (alphaSubjectService.isExistCustomerSubject(customerTypeName, recordNumber,customerName,  sex,
	     		cpExcelDetail.getCustomerPhone())) {
				 customer =
                 alphaSubjectService.findByParams(customerTypeName, recordNumber, customerName, sex, cpExcelDetail.getCustomerPhone());
        } else {
            AlphaSubject cnew = new AlphaSubject();
            cnew.setSubjectType(SubjectType.TYPE1.value());
            cnew.setRecordType(customerTypeName);
            cnew.setRecordNumber(recordNumber);
            cnew.setName(customerName);
            cnew.setPhone(cpExcelDetail.getCustomerPhone());
            cnew.setSourceType(SourceType.TYPE3.value());
            cnew.setSourceId(cpExcelMst.getId());
            cnew.setSourceDetailId(cpExcelDetail.getId());
            cnew.setEnabled(1);
            cnew.setOperator(cpExcelMst.getOperator());
            cnew.setCreateTime(new Date());
            cnew.setAge(Integer.parseInt(age));
            cnew.setSex(sex);
            cnew.setLocation(location);
            if(customerTypeName.equals("身份证")) {//根据身份号获取性别,年龄,所在省份
            	cnew.setAge(IdNumUtils.getAge(recordNumber));
            	cnew.setSex(IdNumUtils.getSex(recordNumber));
            	cnew.setLocation(IdNumUtils.getProvince(recordNumber));
            }
            customer = alphaSubjectService.save(cnew);
        }
        //存入productId和 customerId
        cpExcelDetail.setProductId(product.getId());
        cpExcelDetail.setCustomerSubjectId(customer.getId());
        cpExcelDetail.setState(CustomStatus.STATUS3.value());//3 = 申请通过待审核
        cpExcelService.saveDetail(cpExcelDetail);
		return ResultFactory.buildSuccessResult("保存成功");
	}
	
	/**
	 * 建保维护
	 * @param map
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/prodserv")
	public Result productService(@RequestParam Map<String, String> map, HttpServletRequest request) throws Exception {
		String username = map.get("username");
		String email = map.get("email");
		String productName = map.get("pName");// 产品名称
		String recordNumber = map.get("recordNumber");// 备案编号
		User user = userService.findByUsernameAndEmail(username, email);
		if (user == null) {
			throw new CustomException(0, "用户名或Email错误");
		}
		
		if (StringUtils.isEmpty(productName)) {
			throw new CustomException(0, "服务名不能为空");
		}
		
		if (StringUtils.isEmpty(recordNumber)) {
			throw new CustomException(0, "备案不能为空");
		}
		
		if (productService.isExistProduct(productName)) {
			throw new CustomException(0, "服务名已存在");
		} 
		
		if (productService.isExistRecordNumber(recordNumber)) {
			throw new CustomException(0, "备案编号已存在");
		}
		log.info(""+username + "调用接口"+request.getRequestURI());
		Product product = new Product();
		product.setName(productName);
		product.setRecordNumber(recordNumber);
        product.setAlphaSubjectId(user.getAlphaSubjectId());
        product.setProductType(ProductType.TYPE3.value());
        product.setSourceType(SourceType.TYPE3.value());
        product.setEnabled(1);
        product.setOperator(user.getUsername());
        productService.save(product);		
		
		return ResultFactory.buildSuccessResult("保存成功");
	}
	
	@GetMapping("/cpExcelList/{size}/{page}")
	public Result cpExcelList(@PathVariable("size") int size,
			                               @PathVariable("page") int page,
			                               @RequestParam(value = "username", required = true) String username,
					                       @RequestParam(value = "email", required = true) String email,
					                       @RequestParam(value = "pnum", required = true) String pnum,
					                       @RequestParam Map<String, String> map, HttpServletRequest request) throws Exception {
//		String username = map.get("username");
//		String email = map.get("email");
//		String pnum = map.get("pnum");
		User user = userService.findByUsernameAndEmail(username, email);
		if (user == null) {
			throw new CustomException(0, "用户名或Email错误");
		}
		if(size < 0 || size > 1000 || page < 0) {
			throw new CustomException(0, "请检查参数");
		}
		if(StringUtils.isEmpty(pnum)) {
			throw new CustomException(0, "请检查参数");
		}		
		CpExcelMst mst = cpExcelService.findMstByChargeSubjectIdAndBatchNum(user.getAlphaSubjectId(), pnum);
		if(mst == null) {
			throw new CustomException(0, "无此批号记录");
		}
		long begin = System.currentTimeMillis();
		log.info(" "+username + " 调用接口 "+request.getRequestURI());
    	MyPage<Map> myPage = new MyPage<Map>();
        Pageable pageable = PageRequest.of(page-1,size,Sort.by(Sort.Direction.ASC,"id"));
        Page<Map> pageCpExcelDetail = cpExcelService.findDetailDTOPageByParams(mst.getId(),
             Arrays.asList(CustomStatus.STATUS3.value(), CustomStatus.STATUS4.value()), pageable);
        if (pageCpExcelDetail != null && pageCpExcelDetail.getContent().size() > 0) {
        	myPage = new MyPage<Map>(pageCpExcelDetail);
        }
        log.info("耗时:"+(System.currentTimeMillis() - begin) /1000+ "s 获取"+myPage.getNumberOfElements() +"条记录");
        RemoteOptLog remoteOptLog = new RemoteOptLog();
        remoteOptLog.setUsername(username);
        remoteOptLog.setIp(request.getRemoteHost());
        remoteOptLog.setUrl(request.getRequestURI());
        remoteOptLog.setOperate("2");
        remoteOptLog.setCreateTime(new Date());
        remoteOptLogService.save(remoteOptLog);
		return ResultFactory.buildSuccessResult(myPage);
	}
	
	/**
	 * 
	 * @param username 登录用户名
	 * @param email        注册邮箱
	 * @param pnum       批号
	 * @param flag          是否审核通过
	 * @param ids           excel明细表id
	 * @param map     
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/updateExcelList")
	public Result updateExcelList(@RequestParam(value = "username", required = true) String username,
			                         @RequestParam(value = "email", required = true) String email,
			                         @RequestParam(value = "pnum", required = true) String pnum,
			                         @RequestParam(value = "flag", required = true) Boolean flag,
			                         @RequestParam(value = "ids", required = true) String ids,
			                         @RequestParam Map<String, Object> map, HttpServletRequest request) throws Exception {
		User user = userService.findByUsernameAndEmail(username, email);
		int count = 0;
		if (user == null) {
			throw new CustomException(0, "用户名或Email错误");
		}
		
		if(StringUtils.isEmpty(pnum)) {
			throw new CustomException(0, "请检查参数");
		}
		
		CpExcelMst mst = cpExcelService.findMstByChargeSubjectIdAndBatchNum(user.getAlphaSubjectId(), pnum);
		if(mst == null) {
			throw new CustomException(0, "无此批号记录");
		}
		
		List<Integer> idList = new ArrayList<Integer>();
		if (StringUtils.isEmpty(ids)) {
			throw new CustomException(0, "请检查参数");
		}
		
		if(ids.indexOf(",")>0) {
			String[] sids = ids.split(",");
			for (int i = 0; i < sids.length; i++) {
				idList.add(Integer.valueOf(sids[i]));
			}
		}
		
		long begin = System.currentTimeMillis();
		log.info("" + username + " 调用接口 " + request.getRequestURI());
		if (flag) {
			if (ids.equalsIgnoreCase("all")) {
				count = cpExcelService.updateDetailAll(mst.getId(), CustomStatus.STATUS5.value());
			} else {
				count = cpExcelService.updateDetail(mst.getId(), CustomStatus.STATUS5.value(), idList);
			}
		} else {
			if (ids.equalsIgnoreCase("all")) {
				count = cpExcelService.updateDetailAll(mst.getId(), CustomStatus.STATUS_5.value());
			} else {
				count = cpExcelService.updateDetail(mst.getId(), CustomStatus.STATUS_5.value(), idList);
			}
		}
		
		log.info("耗时:" + (System.currentTimeMillis() - begin) / 1000 + "s 更新"+count +"条记录");
		RemoteOptLog remoteOptLog = new RemoteOptLog();
		remoteOptLog.setUsername(username);
		remoteOptLog.setIp(request.getRemoteHost());
		remoteOptLog.setUrl(request.getRequestURI());
		remoteOptLog.setOperate("1");
		remoteOptLog.setCreateTime(new Date());
		remoteOptLogService.save(remoteOptLog);
		return ResultFactory.buildSuccessResult("更新"+count +"条记录");
	}

}
