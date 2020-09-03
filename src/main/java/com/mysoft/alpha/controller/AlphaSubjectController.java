package com.mysoft.alpha.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysoft.alpha.common.CustomStatus;
import com.mysoft.alpha.common.SubjectType;
import com.mysoft.alpha.entity.AlphaSubject;
import com.mysoft.alpha.entity.CpExcelDetail;
import com.mysoft.alpha.entity.CpExcelMst;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.AlphaSubjectService;
import com.mysoft.alpha.service.CpExcelService;
import com.mysoft.alpha.service.UserService;

/**
 * 主体(AlphaSubject)表控制层
 *
 * @author
 * @since 2020-08-06 10:33:08
 */
@RestController
@RequestMapping("/api/admin/v1/pub/alphaSubject")
public class AlphaSubjectController {
	@Autowired
	UserService userService;

	@Autowired
	private AlphaSubjectService alphaSubjectService;

	@Autowired
	private CpExcelService cpExcelService;

	@GetMapping("/detailList")
	public Result detailList(@RequestParam() Integer step, @RequestParam() String name,
			@RequestParam() String recordNumber, @RequestParam() String productName, @RequestParam() String outTradeNo)
			throws CustomException {
		String operator = SecurityUtils.getSubject().getPrincipal().toString();
		User user = userService.findByUsername(operator);
		// 根据付费方或收款方查询Excel主表
		List<CpExcelMst> cpExcelMstList = new ArrayList<>();
		if (operator.equals("admin")) {
			cpExcelMstList = cpExcelService.findMstAll();
		} else {
			switch (step) {
			case 2:// 采购单维护
				cpExcelMstList = cpExcelService.findMstByChargeSubjectIdOrderById(user.getAlphaSubjectId());
				break;
			case 3:// 采购单付费
				cpExcelMstList = cpExcelService.findMstByPaySubjectIdOrderById(user.getAlphaSubjectId());
				break;
			}
		}
		// 根据Excel主表状态查询Excel明细
		List<CpExcelDetail> cpExcelDetailList = new ArrayList<>();
		for (CpExcelMst cpExcelMst : cpExcelMstList) {
			switch (step) {
			case 2:
				cpExcelDetailList.addAll(cpExcelService.findDetailByParamsOrderByIdAsc(cpExcelMst.getId(),
						Arrays.asList(CustomStatus.STATUS3.value(), CustomStatus.STATUS4.value()), name, recordNumber,
						productName, outTradeNo));
				break;
			case 3:
				cpExcelDetailList.addAll(cpExcelService.findDetailByParamsOrderByIdAsc(cpExcelMst.getId(),
						Arrays.asList(CustomStatus.STATUS5.value()), name, recordNumber, productName, outTradeNo));
				break;
			}
		}
		List<AlphaSubject> returnList = new ArrayList<>();
		List<AlphaSubject> alphaSubjectList = alphaSubjectService.findAllById(
				cpExcelDetailList.stream().map(CpExcelDetail::getCustomerSubjectId).collect(Collectors.toList()));
		for (AlphaSubject alphaSubject : alphaSubjectList) {
			switch (step) {
			case 2:
				if (alphaSubject.getSubjectType().intValue() == SubjectType.TYPE1.value()) {
					List<CpExcelDetail> cpExcelDetails = cpExcelService
							.findDetailByCustomerSubjectId(alphaSubject.getId());
					List<CpExcelDetail> setDetails = new ArrayList<CpExcelDetail>();
					for (CpExcelDetail cpExcelDetail : cpExcelDetails) {
						if (cpExcelService.getMstById(cpExcelDetail.getCpExcelMstId()).getChargeSubjectId()
								.intValue() == user.getAlphaSubjectId().intValue()) {
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
							setDetails.add(cpExcelDetail);
						}
					}
					alphaSubject.setCpExcelDetails(setDetails);

					returnList.add(alphaSubject);
				}
				break;
				
			case 3:
				if (alphaSubject.getSubjectType().intValue() == SubjectType.TYPE1.value()) {
					List<CpExcelDetail> cpExcelDetails = cpExcelService
							.findDetailByCustomerSubjectId(alphaSubject.getId());
					List<CpExcelDetail> setDetails = new ArrayList<CpExcelDetail>();
					for (CpExcelDetail cpExcelDetail : cpExcelDetails) {
						if (cpExcelService.getMstById(cpExcelDetail.getCpExcelMstId()).getPaySubjectId()
								.intValue() == user.getAlphaSubjectId().intValue()) {
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
							setDetails.add(cpExcelDetail);
						}
					}
					alphaSubject.setCpExcelDetails(setDetails);

					returnList.add(alphaSubject);
				}
				break;
			}
		}
		return ResultFactory.buildSuccessResult(returnList);
	}

	@GetMapping("/getNameById")
	public Result getAlphaSubject(Integer id) throws CustomException {
		AlphaSubject alphaSubject = alphaSubjectService.getAlphaSubjectById(id);
		if (!alphaSubject.getRecordType().equals("组织机构代码")) {
			alphaSubject.setName(alphaSubject.getName() + "（性别：" + alphaSubject.getSex() + "，年龄："
					+ alphaSubject.getAge() + "，所在地：" + alphaSubject.getLocation() + "）");
		}
		return ResultFactory.buildSuccessResult(alphaSubject);
	}

	@GetMapping("/charge/list")
	public Result getAllCompanyService() throws CustomException {
		return ResultFactory.buildSuccessResult(alphaSubjectService.findAllBySubjectType(SubjectType.TYPE3.value()));
	}

}