package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.CustomerProduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 客户-企业-产品订单(CustomerProduct)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:13:53
 */
public interface CustomerProductDao extends JpaRepository<CustomerProduct, Integer> {

    List<CustomerProduct> findByCustomerSubjectIdAndProductId(Integer customerSubjectId, Integer ProductId);

    List<CustomerProduct> findBySourceDetailIdIsInOrderById(List<Integer> detailIds);

    List<CustomerProduct> findBySourceIdInAndStateInOrderById(List<Integer> sourceIds, List<Integer> status);
    
    Page<CustomerProduct> findBySourceIdInAndStateInOrderById(List<Integer> sourceIds, List<Integer> status, Pageable pageable);

    void deleteBySourceTypeAndSourceDetailId(Integer sourceType, Integer sourceDetailId);
    
    List<CustomerProduct> findByProductId(Integer productId);

}