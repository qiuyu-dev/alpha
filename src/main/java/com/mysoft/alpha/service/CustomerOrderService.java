package com.mysoft.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mysoft.alpha.dao.CustomerOrderDAO;
import com.mysoft.alpha.entity.CustomerOrder;
import com.mysoft.alpha.redis.RedisService;
import com.mysoft.alpha.util.CastUtils;

@Service
public class CustomerOrderService {
	private static final String CUSTOMER_ORDER_LIST = "customerOrderlist";
	@Autowired
	private CustomerOrderDAO customerOrderDAO;
    @Autowired
    private RedisService redisService;
    
    public List<CustomerOrder> list(){
    	List<CustomerOrder> list = null;
        Object customerOrderCache = redisService.get(CUSTOMER_ORDER_LIST);
        if(customerOrderCache == null) {
        	list = customerOrderDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
        	redisService.set(CUSTOMER_ORDER_LIST, list);
        }else {
        	list = CastUtils.objectConvertToList(customerOrderCache, CustomerOrder.class);
        }        
    	return list;
    }

    public void addOrUpdate(CustomerOrder customerOrder) {
        redisService.delete(CUSTOMER_ORDER_LIST);
        if(StringUtils.isEmpty(customerOrder.getId()) && StringUtils.isEmpty(customerOrder.getState())) {
        	customerOrder.setState("1");//默认状态        	
        }
        customerOrderDAO.save(customerOrder);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete(CUSTOMER_ORDER_LIST);
    }
    
    @Transactional
    public void saveAll(List<CustomerOrder> CustomerOrderList) {
    	for(CustomerOrder customerOrder : CustomerOrderList) {
        	customerOrderDAO.save(customerOrder);
    	}
    }
    
    public void deleteById(int id) {
        redisService.delete(CUSTOMER_ORDER_LIST);
        customerOrderDAO.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete(CUSTOMER_ORDER_LIST);
    }
    
    @Transactional
    public void deleteByIds(Integer[] ids ) {
        redisService.delete(CUSTOMER_ORDER_LIST);    
        customerOrderDAO.deleteAllByIdIn(ids);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete(CUSTOMER_ORDER_LIST);
    }
}
