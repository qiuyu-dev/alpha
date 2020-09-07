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

    boolean isExistCustomerSubject(String recordType, String recordNumber, String name, String sex, String phone);

    AlphaSubject findByParams(String recordType, String recordNumber, String name, String sex, String phone);

    AlphaSubject save(AlphaSubject alphaSubject);

    List<AlphaSubject> findAllBySubjectType(Integer subjectType);

    List<AlphaSubject> findAllById(List<Integer> ids);

}