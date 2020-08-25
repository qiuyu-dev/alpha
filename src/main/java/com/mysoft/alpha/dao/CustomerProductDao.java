package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.CustomerProduct;
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

    void deleteBySourceTypeAndSourceId(Integer sourceType, Integer sourceId );
     List<CustomerProduct> findBySourceDetailIdIsInOrderById(List<Integer>  detailIds);

    List<CustomerProduct> findBySourceIdIsInOrderById(List<Integer>  sourceIds);

    List<CustomerProduct> findBySourceIdInAndStateInOrderById(List<Integer>  sourceIds,List<Integer> status);
    void deleteBySourceTypeAndSourceDetailId(Integer sourceType, Integer sourceDetailId );

}