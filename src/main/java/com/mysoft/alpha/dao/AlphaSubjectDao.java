package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.AlphaSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 主体(AlphaSubject)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:13:36
 */
public interface AlphaSubjectDao extends JpaRepository<AlphaSubject, Integer> {


    AlphaSubject findByRecordNumber(String orgcode);

    AlphaSubject findBySubjectTypeAndRecordTypeAndRecordNumber(Integer subjectType, String recordType,
                                                               String recordNumber);

    void deleteBySourceTypeAndSourceDetailIdIn(Integer sourceType,List<Integer> sourceDetailId);

    void deleteBySourceTypeAndSourceId(Integer sourceType,Integer sourceId);

    List<AlphaSubject> findAllBySubjectType(Integer subjectType);

    AlphaSubject findBySourceTypeAndSourceDetailId(Integer subjectType, Integer sourceDetailId);



}