package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.AlphaSubject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 主体(AlphaSubject)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:13:36
 */
public interface AlphaSubjectDao extends JpaRepository<AlphaSubject, Integer> {
    @Query(value = "select * from alpha_subject subject where subject.id in(:ids)", 
            countQuery = "select count(*) from alpha_subject subject where subject.id in(:ids)", nativeQuery = true)
	Page<AlphaSubject> findPageByIds(List<Integer> ids, Pageable pageable);

    AlphaSubject findByRecordNumber(String orgcode);

    void deleteBySourceTypeAndSourceId(Integer sourceType, Integer sourceId);

    List<AlphaSubject> findAllBySubjectType(Integer subjectType);

    AlphaSubject findBySourceTypeAndSourceDetailId(Integer subjectType, Integer sourceDetailId);


}