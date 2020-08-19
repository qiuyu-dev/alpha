package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.AlphaSubject;

import java.util.List;

/**
 * 主体(AlphaSubject)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:13:36
 */
public interface AlphaSubjectService {

    AlphaSubject getAlphaSubjectById(int id);

    boolean isExistAlphaSubject( Integer subjectType,String recordType,String recordNumber);

    AlphaSubject findBySubjectTypeAndRecordTypeAndRecordNumber( Integer subjectType,String recordType,
                                                                String recordNumber);

    AlphaSubject save(AlphaSubject alphaSubject);

    void deleteBySourceTypeAndSourceId(Integer sourceType,Integer sourceId);

    List<AlphaSubject> findAllBySubjectType(Integer subjectType);
    void deleteBySourceTypeAndSourceDetailId(Integer sourceType,  Integer sourceDetailId);

}