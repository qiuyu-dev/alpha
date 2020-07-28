package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.entity.BatchFeeDetail;
import com.mysoft.alpha.entity.BatchFeeMst;
import com.mysoft.alpha.service.BatchFeeDetailService;
import com.mysoft.alpha.service.BatchFeeMstService;
import com.mysoft.alpha.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	BatchFeeMstService batchFeeMstService;

	@Autowired
	BatchFeeDetailService batchFeeDetailService;

	@Transactional
	@Override
	public void batchFeeMstFormProcess(BatchFeeMst batchFee, String ids) {
		BatchFeeMst mstNew =batchFeeMstService.addOrUpdateBatchFeeMst(batchFee);
		List<BatchFeeDetail> batchFeeDetailList= new ArrayList<>();
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			BatchFeeDetail batchFeeDetail = new BatchFeeDetail();
			batchFeeDetail.setCeId(Integer.parseInt(id));
			batchFeeDetail.setEffectiveNumber(1);
			batchFeeDetail.setCreateTime(new Date());
			batchFeeDetail.setOperator(batchFee.getOperator());
			batchFeeDetail.setBatchFeeMstId(mstNew.getId());
			batchFeeDetailList.add(batchFeeDetail);
			batchFeeDetailService.addOrUpdateBatchFeeDetail(batchFeeDetail);
		}
//		batchFee.setBatchFeeDetail(batchFeeDetailList);
//		BatchFeeMst mstNew =batchFeeMstService.addOrUpdateBatchFeeMst(batchFee);

	}

}
