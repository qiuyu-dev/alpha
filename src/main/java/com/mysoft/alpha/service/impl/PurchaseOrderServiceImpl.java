package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.entity.BatchFeeDetail;
import com.mysoft.alpha.entity.BatchFeeMst;
import com.mysoft.alpha.service.BatchFeeDetailService;
import com.mysoft.alpha.service.BatchFeeMstService;
import com.mysoft.alpha.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
    BatchFeeMstService batchFeeMstService;
	
	@Autowired
	BatchFeeDetailService batchFeeDetailService;
	 
	@Transactional
	@Override
	public void batchFeeMstFormProcess(BatchFeeMst batchFee, String ids) {
		batchFeeMstService.addOrUpdateBatchFeeMst(batchFee);
		String[] idArray = ids.split(",");
		for(String id:idArray) {
			 BatchFeeDetail batchFeeDetail = new BatchFeeDetail();
			 batchFeeDetail.setBatchNumber(batchFee.getBatchNumber());
			 batchFeeDetail.setCeId(Integer.parseInt(id));
			 batchFeeDetail.setEffectiveNumber(1);
			 batchFeeDetail.setCreateTime(new Date());
			 batchFeeDetail.setOperator(batchFee.getOperator());
			 batchFeeDetailService.addOrUpdateBatchFeeDetail(batchFeeDetail);
		}  	

	}

}
