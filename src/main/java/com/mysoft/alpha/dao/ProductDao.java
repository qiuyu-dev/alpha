package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 产品(Product)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:14:09
 */
public interface ProductDao extends JpaRepository<Product, Integer> {
    Product findByName(String name);
    
    Product findByRecordNumber(String recordNumber);

    @Override
    <S extends Product> S save(S s);

    List<Product> findByAlphaSubjectId(Integer alphaSubjectId);
    
    void deleteById(Integer id);

}