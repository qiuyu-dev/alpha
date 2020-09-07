package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.BatchFeeDetailDao;
import com.mysoft.alpha.dao.BatchFeeMstDao;
import com.mysoft.alpha.dao.UserDao;
import com.mysoft.alpha.entity.BatchFeeDetail;
import com.mysoft.alpha.entity.BatchFeeMst;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.service.BatchFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 批次付费主表(BatchFeeMst)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:13:50
 */
@Service
public class BatchFeeServiceImpl implements BatchFeeService {
	/**
	 * 服务对象
	 */
	@Autowired
	private BatchFeeMstDao batchFeeMstDao;

	@Autowired
	private BatchFeeDetailDao batchFeeDetailDao;

	@Autowired
	private UserDao userDao;

	@Override
	public List<BatchFeeMst> findMstByCpExcelDetailId(Integer cpExcelDetailId) {
		List<BatchFeeDetail> batchFeeDetailList = batchFeeDetailDao.findBySourceDetailIdOrderByIdAsc(cpExcelDetailId);
		return batchFeeMstDao.findByIdInOrderByEffectiveDateDesc(
				batchFeeDetailList.stream().map(BatchFeeDetail::getBatchFeeMstId).collect(Collectors.toList()));
	}
	@Override
	public BatchFeeMst getMstById(Integer id) {
		return batchFeeMstDao.getOne(id);
	}
//	@Override
//	public List<BatchFeeDetail> findDetailBySourceDetailIdOrderByIdDesc(Integer cpExcelDetailId) {
//
//		return batchFeeDetailDao.findBySourceDetailIdOrderByIdAsc(cpExcelDetailId);
//	}
	@Override
	public List<BatchFeeDetail> findDetailByCustomerSubjectId(Integer customerSubjectId) {
		BatchFeeDetail batchFeeDetail = new BatchFeeDetail();
		batchFeeDetail.setCustomerSubjectId(customerSubjectId);
		Example<BatchFeeDetail> example = Example.of(batchFeeDetail);
		return batchFeeDetailDao.findAll(example);
	}
	@Override
	public BatchFeeMst saveBatchFeeMst(BatchFeeMst batchFeeMst) {
		return batchFeeMstDao.save(batchFeeMst);
	}
	@Override
	public BatchFeeDetail saveBatchFeeDetail(BatchFeeDetail batchFeeDetail) {
		return batchFeeDetailDao.save(batchFeeDetail);
	}

	@Override
	public List<BatchFeeMst> findAllBatchFeeMstByUserAndStateIn(String username, List<Integer> status) {
		List<BatchFeeMst> retrunList = new ArrayList<>();
		if (username.equals("admin")) {
			retrunList = batchFeeMstDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
		} else {
			User user = userDao.findByUsername(username);
			retrunList = batchFeeMstDao.findByChargeSubjectIdAndStateInOrderByIdAsc(user.getAlphaSubjectId(), status);
		}

		return retrunList;
	}

	@Override
	public List<BatchFeeDetail> findDetailsByBatchFeeMstId(Integer batchFeeMstId) {
		return batchFeeDetailDao.findByBatchFeeMstIdOrderByIdAsc(batchFeeMstId);
	}

}