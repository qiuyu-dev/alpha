package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.entity.CustomerOrder;

public interface CustomerOrderService {
   
    public List<CustomerOrder> list();

    public void addOrUpdate(CustomerOrder customerOrder);
    
    public void saveAll(List<CustomerOrder> CustomerOrderList);
    
    public void deleteById(int id);
    
    public void deleteByIds(Integer[] ids);
}
