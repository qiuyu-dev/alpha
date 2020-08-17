package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.BatchFeeMst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 批次付费主表(BatchFeeMst)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-09 15:38:45
 */
public interface BatchFeeMstDao extends JpaRepository<BatchFeeMst, Integer> {

    List<BatchFeeMst> findByChargeSubjectIdOrderByIdAsc(Integer chargeSubjectId);
    List<BatchFeeMst> findByIdInOrderByEffectiveDateDesc(List<Integer> idList);



}