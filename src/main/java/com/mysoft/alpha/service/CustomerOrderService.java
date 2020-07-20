package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.entity.CustomerProductExcelDetail;
import com.mysoft.alpha.entity.CustomerProductExcelMst;

public interface CustomerOrderService {
   
    public List<CustomerProductExcelMst> findCustomerProductExcelMstList();
    
    public List<CustomerProductExcelDetail> findCustomerProductExcelDetailList();

    public CustomerProductExcelMst addOrUpdateCustomerProductExcelMst(CustomerProductExcelMst customerProductExcelMst);
    
    public CustomerProductExcelDetail addOrUpdateCustomerProductExcelDetail(CustomerProductExcelDetail customerProductExcelDetail);
    
    public void saveAllCustomerProductExcelDetail(List<CustomerProductExcelDetail> customerProductExcelDetailList);
    
    public void deleteCustomerProductExcelDetailById(int id);
    
    public void deleteCustomerProductExcelDetailByIds(Integer[] ids);    
    
}
