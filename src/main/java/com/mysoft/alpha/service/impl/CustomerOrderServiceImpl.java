package com.mysoft.alpha.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mysoft.alpha.cache.CacheKeyManager;
import com.mysoft.alpha.dao.CustomerOrderDAO;
import com.mysoft.alpha.entity.CustomerOrder;
import com.mysoft.alpha.service.CustomerOrderService;
import com.mysoft.alpha.util.BaseCache;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderDAO customerOrderDAO;
    
	@Autowired
	private BaseCache baseCache;
    
    @SuppressWarnings("unchecked")
	public List<CustomerOrder> list(){
    	List<CustomerOrder> list = null;
    	try {
    		Object cacheObj = baseCache.getTenMinuteCache().get(	CacheKeyManager.CUSTOMERORDER_KEY, () -> {
    			List<CustomerOrder> customerOrderlist = customerOrderDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
    			System.out.println("从数据库里面查客户单列表");
    			return customerOrderlist;
    			});
    		
        	if (cacheObj instanceof List) {
        		list = (List<CustomerOrder>)cacheObj;
        		return list;
        	}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}  
    	return list;
    }

    public void addOrUpdate(CustomerOrder customerOrder) {
    	baseCache.getTenMinuteCache().invalidate(CacheKeyManager.CUSTOMERORDER_KEY);
        if(StringUtils.isEmpty(customerOrder.getId()) && StringUtils.isEmpty(customerOrder.getState())) {
        	customerOrder.setState("1");//默认状态        	
        }
        customerOrderDAO.save(customerOrder);
    }
    
    @Transactional
    public void saveAll(List<CustomerOrder> customerOrderList) {
    	baseCache.getTenMinuteCache().invalidate(CacheKeyManager.CUSTOMERORDER_KEY);
    	customerOrderDAO.saveAll(customerOrderList);
//    	for(CustomerOrder customerOrder : customerOrderList) {
//        	customerOrderDAO.save(customerOrder);
//    	}

    }
    
    public void deleteById(int id) {
    	baseCache.getTenMinuteCache().invalidate(CacheKeyManager.CUSTOMERORDER_KEY);
        customerOrderDAO.deleteById(id);
    }
    
    @Transactional
    public void deleteByIds(Integer[] ids ) {
    	baseCache.getTenMinuteCache().invalidate(CacheKeyManager.CUSTOMERORDER_KEY);
        customerOrderDAO.deleteAllByIdIn(ids);
    }
}
