package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mysoft.alpha.entity.Complaint;

import java.util.List;

/**
 * 投诉(Complaint)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:13:57
 */
public interface ComplaintDao extends JpaRepository<Complaint, Integer> {
    List<Complaint> findByCustomerProductIdOrderByIdDesc(Integer customerProductId);


}