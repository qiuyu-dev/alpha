package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.CustomerProduct;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 客户-企业-产品订单(CustomerProduct)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:13:54
 */
public interface CustomerProductService {

    CustomerProduct getOneById(Integer id);

    List<CustomerProduct> findBySourceMstIdInAndStatusIn(List<Integer> sourceMstIds, List<Integer> status);

    Page<CustomerProduct> findBySourceMstIdInAndStatusIn(List<Integer> sourceMstIds, List<Integer> status, Pageable pageable);
    
    void save(CustomerProduct customerProduct);

    List<CustomerProduct> findAll();
    
    Page<CustomerProduct> findAll(Pageable pageable);

    void saveAll(List<CustomerProduct> customerProductList);

    boolean isExistOutTradeNoe(Integer customerId, Integer productId, Date effectiveDate, Date closingDate);

    List<CustomerProduct> findBySourceDetailIdIsInOrderById(List<Integer> detailIds);

    void deleteBySourceTypeAndSourceDetailId(Integer sourceType, Integer sourceDetailId);
    
    List<CustomerProduct> findByProductId(Integer productId);

}