package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.CustomerProductExcelDetailDAO;
import com.mysoft.alpha.dao.CustomerProductExcelMstDAO;
import com.mysoft.alpha.entity.CustomerProductExcelDetail;
import com.mysoft.alpha.entity.CustomerProductExcelMst;
import com.mysoft.alpha.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerProductExcelMstDAO customerProductExcelMstDAO;

	@Autowired
	private CustomerProductExcelDetailDAO customerProductExcelDetailDAO;
//
//	@Autowired
//	private BaseCache baseCache;

	public CustomerProductExcelDetail addOrUpdateCustomerProductExcelDetail(
			CustomerProductExcelDetail customerProductExcelDetail) {
//		baseCache.getTenMinuteCache().invalidate(CacheKeyManager.CUSTOMERORDER_KEY);
		if (StringUtils.isEmpty(customerProductExcelDetail.getId())
				&& StringUtils.isEmpty(customerProductExcelDetail.getStatus())) {
			customerProductExcelDetail.setStatus("1");// 默认状态
		}
		return customerProductExcelDetailDAO.save(customerProductExcelDetail);
	}

	public void deleteCustomerProductExcelDetailById(int id) {
//		baseCache.getTenMinuteCache().invalidate(CacheKeyManager.CUSTOMERORDER_KEY);
		customerProductExcelDetailDAO.deleteById(id);
	}

	@Transactional
	public void deleteCustomerProductExcelDetailByIds(Integer[] ids) {
//		baseCache.getTenMinuteCache().invalidate(CacheKeyManager.CUSTOMERORDER_KEY);
		customerProductExcelDetailDAO.deleteAllByIdIn(ids);
	}

	@Override
	public List<CustomerProductExcelMst> findCustomerProductExcelMstList() {
		return customerProductExcelMstDAO.findAll();
	}

	@Override
	public List<CustomerProductExcelDetail> findCustomerProductExcelDetailList() {
		List<CustomerProductExcelDetail> list = null;
//		try {
//			Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.CUSTOMERORDER_KEY, () -> {
				List<CustomerProductExcelDetail> customerProductExcelDetailList = customerProductExcelDetailDAO
						.findAll(Sort.by(Sort.Direction.DESC, "id"));
				System.out.println("从数据库里面查客户单列表");
//				return customerProductExcelDetailList;
//			});
//
//			if (cacheObj instanceof List) {
//				list = (List<CustomerProductExcelDetail>) cacheObj;
//				return list;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return list;
	}

	@Transactional
	@Override
	public void saveAllCustomerProductExcelDetail(List<CustomerProductExcelDetail> customerProductExcelDetailList) {
//		baseCache.getTenMinuteCache().invalidate(CacheKeyManager.CUSTOMERORDER_KEY);
		customerProductExcelDetailDAO.saveAll(customerProductExcelDetailList);

	}

	@Override
	public CustomerProductExcelMst addOrUpdateCustomerProductExcelMst(CustomerProductExcelMst customerProductExcelMst) {
		return customerProductExcelMstDAO.save(customerProductExcelMst);
	}

}
