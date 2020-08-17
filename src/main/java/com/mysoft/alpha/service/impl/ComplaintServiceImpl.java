package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.ComplaintDao;
import com.mysoft.alpha.entity.Complaint;
import com.mysoft.alpha.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 投诉(Complaint)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:13:59
 */
@Service
public class ComplaintServiceImpl implements ComplaintService {
    /**
     * 服务对象
     */
    @Autowired
    private ComplaintDao complaintDao;

    public Complaint save(Complaint complaint){
        return complaintDao.save(complaint);
    }


    public List<Complaint> findByCustomerProductId(Integer customerProductId){
        return complaintDao.findByCustomerProductIdOrderByIdDesc(customerProductId);
    }
}