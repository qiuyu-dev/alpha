package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.CustomerProduct;

import java.util.Date;
import java.util.List;

/**
 * 客户-企业-产品订单(CustomerProduct)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:13:54
 */
public interface CustomerProductService {

    boolean isExistProductId(Integer productId);
    CustomerProduct getOneById(Integer id);

//    List<CustomerProduct> findBySourceMstIdIsInOrderById(List<Integer> sourceMstIds);
    List<CustomerProduct> findBySourceMstIdInAndStatusIn(List<Integer> sourceMstIds,
                                                         List<Integer> status);
    void save( CustomerProduct customerProduct);

    List<CustomerProduct> findAll();

    void saveAll(List<CustomerProduct> customerProductList);

    boolean isExistOutTradeNoe(Integer customerId, Integer productId, Date effectiveDate, Date closingDate);

    void deleteBySourceTypeAndSourceId(Integer sourceType, Integer sourceId);

    List<CustomerProduct> findBySourceDetailIdIsInOrderById(List<Integer> detailIds);

    void deleteBySourceTypeAndSourceDetailId(Integer sourceType, Integer sourceDetailId);

}