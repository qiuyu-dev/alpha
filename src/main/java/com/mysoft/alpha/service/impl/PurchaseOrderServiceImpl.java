package com.mysoft.alpha.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysoft.alpha.entity.BatchFee;
import com.mysoft.alpha.entity.BatchFeeDetail;
import com.mysoft.alpha.service.BatchFeeDetailService;
import com.mysoft.alpha.service.BatchFeeService;
import com.mysoft.alpha.service.PurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	BatchFeeService batchFeeService;
	
	@Autowired
	BatchFeeDetailService batchFeeDetailService;
	 
	@Transactional
	@Override
	public void batchFeeFormProcess(BatchFee batchFee,String ids) {
		batchFeeService.addOrUpdateBatchFee(batchFee);
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
