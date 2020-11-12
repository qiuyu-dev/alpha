package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.*;
import com.mysoft.alpha.entity.CpExcelDetail;
import com.mysoft.alpha.entity.CpExcelMst;
import com.mysoft.alpha.service.CpExcelService;
import com.mysoft.alpha.util.MyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 客户-产品Excel主表(CpExcelMst)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:14:06
 */
@Service
public class CpExcelServiceImpl implements CpExcelService {

	/**
	 * excel主表服务对象
	 */
	@Autowired
	private CpExcelMstDao cpExcelMstDao;

	/**
	 * excel明细服务对象
	 */
	@Autowired
	private CpExcelDetailDao cpExcelDetailDao;
	
	@Override
	public List<CpExcelDetail> findDetailByCustomerSubjectId(Integer customerSubjectId) {
		CpExcelDetail cpExcelDetail = new CpExcelDetail();
		cpExcelDetail.setCustomerSubjectId(customerSubjectId);
		Example<CpExcelDetail> example = Example.of(cpExcelDetail);
		return cpExcelDetailDao.findAll(example);

	}

	@Override
	public List<CpExcelDetail> findDetailByParamsOrderByIdAsc(Integer cpExcelMstId, List<Integer> status, String name,
			String recordNumber, String productName, String outTradeNo) {
		return cpExcelDetailDao.findByParamsAndSort(cpExcelMstId, status, name, productName, outTradeNo);
 
	}
	
	@Override
	public Page<CpExcelDetail> findPageByParamsAndSort(Integer cpExcelMstId, List<Integer> status, String name,
			String recordNumber, String productName, String outTradeNo,Pageable pageable){
		Page<CpExcelDetail> page = cpExcelDetailDao.findPageByParamsAndSort(cpExcelMstId, status, name, productName, outTradeNo, pageable);
		return page;
	}
	
//
//	@Override
//	public boolean isExistProductId(Integer productId) {
//		CpExcelDetail cpExcelDetail = new CpExcelDetail();
//		cpExcelDetail.setProductId(productId);
//		Example<CpExcelDetail> example = Example.of(cpExcelDetail);
//		return cpExcelDetailDao.exists(example);
//	}
@Override
	public CpExcelMst saveMst(CpExcelMst cpExcelMst) {
		return cpExcelMstDao.save(cpExcelMst);
	}
	@Override
	public CpExcelDetail saveDetail(CpExcelDetail cpExceldetail) {
		return cpExcelDetailDao.save(cpExceldetail);
	}
//	@Override
//	public void saveAllDetails(List<CpExcelDetail> cpExcelDetails) {
//		cpExcelDetailDao.saveAll(cpExcelDetails);
//	}

	@Override
	public boolean isExistOutTradeNo(String outTradeNo, Integer chargeId) {
		CpExcelDetail cpExcelDetail = new CpExcelDetail();
		cpExcelDetail.setOutTradeNo(outTradeNo);
		Example<CpExcelDetail> example = Example.of(cpExcelDetail);
		List<CpExcelDetail> cpExcelDetailList = cpExcelDetailDao.findAll(example);
		for (CpExcelDetail cpExcelDetail1 : cpExcelDetailList) {
			CpExcelMst cpExcelMst = cpExcelMstDao.getOne(cpExcelDetail1.getCpExcelMstId());
			if (cpExcelMst.getChargeSubjectId() != null && cpExcelMst.getChargeSubjectId().compareTo(chargeId) == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isExistOutTradeNo(Integer customerId, Integer productId, Date effectiveDate, Date closingDate) {
		List<CpExcelDetail> cpExcelDetails = cpExcelDetailDao.findByCustomerSubjectIdAndProductId(customerId,
				productId);
		for (CpExcelDetail cpExcelDetail : cpExcelDetails) {
			if (!((effectiveDate.before(cpExcelDetail.getEffectiveDate())
					&& closingDate.before(cpExcelDetail.getEffectiveDate()))
					|| (effectiveDate.after(cpExcelDetail.getClosingDate())
							&& closingDate.after(cpExcelDetail.getClosingDate())))) {
				return true;
			}
		}
		return false;
	}

//	@Override
//	public CpExcelMst findMstByFileName(String fileName) {
//		return cpExcelMstDao.findByFileName(fileName);
//	}

	@Override
	public List<CpExcelMst> findMstByPaySubjectIdOrderById(Integer paySubjectId) {
		return cpExcelMstDao.findByPaySubjectIdOrderByIdDesc(paySubjectId);
	}

	@Override
	public List<CpExcelMst> findMstByChargeSubjectIdOrderById(Integer chargeSubjectId) {
		return cpExcelMstDao.findByChargeSubjectIdOrderByIdDesc(chargeSubjectId);
	}

//	@Override
//	public void deleteDetailByCpExcelMstId(Integer cpExcelMstId) {
//		cpExcelDetailDao.deleteByCpExcelMstId(cpExcelMstId);
//
//	}

//	@Override
//	public void deleteMstById(Integer cpExcelMstId) {
//		cpExcelMstDao.deleteById(cpExcelMstId);
//
//	}

	@Override
	public void deleteDetailById(Integer cpExcelDetailId) {
		cpExcelDetailDao.deleteById(cpExcelDetailId);

	}

//	@Override
//	public List<CpExcelDetail> findDetailByCpExcelMstId(Integer cpExcelMstId) {
//
//		return cpExcelDetailDao.findByCpExcelMstId(cpExcelMstId);
//	}

//	@Override
//	public List<CpExcelDetail> findDetailByCpExcelMstIdAndStateInOrderByIdAsc(Integer cpExcelMstId,
//			List<Integer> status) {
//		return cpExcelDetailDao.findByCpExcelMstIdAndStateInOrderByIdAsc(cpExcelMstId, status);
//	}

	@Override
	public List<CpExcelMst> findMstAll() {
		return cpExcelMstDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public boolean isExistFileName(String fileName, String chargeId) {
		CpExcelMst cpExcelMst1 = new CpExcelMst();
		cpExcelMst1.setFileName(fileName);
		Example<CpExcelMst> example = Example.of(cpExcelMst1);
		return cpExcelMstDao.exists(example);
	}
	@Override
	public CpExcelDetail getDetailById(Integer detailId) {
		return cpExcelDetailDao.findById(detailId).get();
	}
	@Override
	public CpExcelMst getMstById(Integer mstId) {
		return cpExcelMstDao.getOne(mstId);
	}
	
    /**
     * 分页查询
     * @param pageNum
     * @param size
     * @return
     */
    @Override
    public Page<CpExcelDetail> findDetailByPage(Integer pageNum, Integer size) {
    	Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNum - 1, size, sort);
        Page<CpExcelDetail> page= cpExcelDetailDao.findAll(pageRequest);
        return page;
    }

}