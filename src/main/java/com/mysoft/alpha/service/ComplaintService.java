package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.Complaint;

import java.util.List;

/**
 * 投诉(Complaint)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:13:58
 */
public interface ComplaintService {
    Complaint save(Complaint complaint);
    List<Complaint> findByCustomerProductId(Integer customerProductId);

}