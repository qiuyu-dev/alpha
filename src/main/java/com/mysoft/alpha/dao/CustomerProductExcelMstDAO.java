package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.CustomerProductExcelMst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerProductExcelMstDAO extends JpaRepository<CustomerProductExcelMst, Integer> {


    /**
     * find  如果为查询到返回空，get报错
     * @param status
     * @return
     */
    List<CustomerProductExcelMst> findCustomerProductExcelMstsByStatusOrderByIdAsc(String status);


    /**
     *  根据提供企业返回
     * @param fromId
     * @return
     */
    List<CustomerProductExcelMst> findByFromIdOrderByIdDesc(int fromId);

    /**
     *  根据提供人员返回
     * @param operator
     * @return
     */
    List<CustomerProductExcelMst> findByOperatorOrderByIdDesc(String operator);



}
