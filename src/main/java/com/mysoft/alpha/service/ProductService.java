package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.Product;

import java.util.List;

/**
 * 产品(Product)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:14:09
 */
public interface ProductService {

    boolean isExistProduct(String productName) ;

    Product findByName(String productName);

    Product save(Product product);

    void deleteBySourceTypeAndSourceId(Integer sourceType,Integer sourceId);

    List<Product> findByAlphaSubjectId(Integer alphaSubjectId);

    List<Product> findAll();

     Product getProductById(Integer id);

    void deleteBySourceTypeAndSourceDetailId(Integer sourceType, Integer sourceDetailId);

}