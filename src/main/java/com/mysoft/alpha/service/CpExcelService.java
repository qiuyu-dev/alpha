package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.CustomerProductExcelMst;

import java.util.List;

public interface CpExcelService {

    public void taskCpExcelToCustomerEnterprise();

    public List<CustomerProductExcelMst> findCpExcelByUser(String username);

    public void updateCpExcelDetailStatusById(int cpExcelDetailId,int status);

}
