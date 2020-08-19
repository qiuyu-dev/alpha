package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AlphaSubjectDao;
import com.mysoft.alpha.dao.CpExcelDetailDao;
import com.mysoft.alpha.entity.AlphaSubject;
import com.mysoft.alpha.entity.CpExcelDetail;
import com.mysoft.alpha.service.AlphaSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主体(AlphaSubject)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:13:37
 */
@Service
public class AlphaSubjectServiceImpl implements AlphaSubjectService {


    /**
     * 服务对象
     */
    @Autowired
    private AlphaSubjectDao alphaSubjectDao;

    @Autowired
    private CpExcelDetailDao cpExcelDetailDao;

    @Override
    public AlphaSubject getAlphaSubjectById(int id) {
        return alphaSubjectDao.getOne(id);
    }

    @Override
    public boolean isExistAlphaSubject(Integer subjectType, String recordType, String recordNumber) {
        if (recordType != null && recordType.equals("身份证")) {
            AlphaSubject alphaSubject = alphaSubjectDao
                    .findBySubjectTypeAndRecordTypeAndRecordNumber(subjectType, recordType, recordNumber);
            return null != alphaSubject;
        } else {
            return false;
        }
    }

    @Override
    public AlphaSubject findBySubjectTypeAndRecordTypeAndRecordNumber(Integer subjectType, String recordType,
                                                                      String recordNumber) {
        return alphaSubjectDao.findBySubjectTypeAndRecordTypeAndRecordNumber(subjectType, recordType, recordNumber);
    }

    @Override
    public AlphaSubject save(AlphaSubject alphaSubject) {
        return alphaSubjectDao.save(alphaSubject);
    }

    @Override

    public void deleteBySourceTypeAndSourceId(Integer sourceType, Integer sourceId) {
        alphaSubjectDao.deleteBySourceTypeAndSourceId(sourceType, sourceId);
    }

    @Override
    public void deleteBySourceTypeAndSourceDetailId(Integer sourceType, Integer sourceDetailId) {

        AlphaSubject alphaSubject = alphaSubjectDao.findBySourceTypeAndSourceDetailId(sourceType, sourceDetailId);
        CpExcelDetail cpExcelDetail = new CpExcelDetail();
        cpExcelDetail.setCustomerSubjectId(alphaSubject.getId());
        Example<CpExcelDetail> example = Example.of(cpExcelDetail);
        if (!cpExcelDetailDao.exists(example)) {
            cpExcelDetailDao.deleteById(alphaSubject.getId());
        }
    }

    @Override
    public List<AlphaSubject> findAllBySubjectType(Integer subjectType) {
        return alphaSubjectDao.findAllBySubjectType(subjectType);

    }
}