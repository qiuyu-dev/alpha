package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AlphaSubjectDao;
import com.mysoft.alpha.dao.CpExcelDetailDao;
import com.mysoft.alpha.entity.AlphaSubject;
import com.mysoft.alpha.service.AlphaSubjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
	public boolean isExistCustomerSubject(String recordType, String recordNumber, String name, String sex,
			String phone) {
		AlphaSubject alphaSubject = new AlphaSubject();
		if (recordNumber.equals("空") || StringUtils.isEmpty(recordNumber)) {
			alphaSubject.setName(name);
			alphaSubject.setSex(sex);
			alphaSubject.setPhone(phone);
		} else {
			alphaSubject.setName(name);
			alphaSubject.setSex(sex);
			alphaSubject.setPhone(phone);
			alphaSubject.setRecordType(recordType);
			alphaSubject.setRecordNumber(recordNumber);
		}
		Example<AlphaSubject> example = Example.of(alphaSubject);
		return alphaSubjectDao.exists(example);
	}

	@Override
	public AlphaSubject findByParams(String recordType, String recordNumber, String name, String sex, String phone) {
		AlphaSubject alphaSubject = new AlphaSubject();
		if (recordNumber.equals("空") || StringUtils.isEmpty(recordNumber)) {
			alphaSubject.setName(name);
			alphaSubject.setSex(sex);
			alphaSubject.setPhone(phone);
		} else {
			alphaSubject.setName(name);
			alphaSubject.setSex(sex);
			alphaSubject.setPhone(phone);
			alphaSubject.setRecordType(recordType);
			alphaSubject.setRecordNumber(recordNumber);
		}
		Example<AlphaSubject> example = Example.of(alphaSubject);
		Optional<AlphaSubject> alphaSubjectOptional = alphaSubjectDao.findOne(example);
		if (alphaSubjectOptional.isPresent()) {
			return alphaSubjectOptional.get();
		} else {
			return null;
		}
	}

//	@Override
//	public boolean isExistAlphaSubject(String recordType, String recordNumber) {
////        if (recordType != null && recordType.equals("身份证")) {
//		if (recordType != null && recordNumber != null) {
//			AlphaSubject alphaSubject = new AlphaSubject();
//			alphaSubject.setRecordType(recordType);
//			alphaSubject.setRecordNumber(recordNumber);
//			Example<AlphaSubject> example = Example.of(alphaSubject);
//			return alphaSubjectDao.exists(example);
//		} else {
//			return false;
//		}
//	}

//	@Override
//	public AlphaSubject findBySubjectTypeAndRecordTypeAndRecordNumber(String recordType, String recordNumber) {
//		AlphaSubject alphaSubject = new AlphaSubject();
//		alphaSubject.setRecordType(recordType);
//		alphaSubject.setRecordNumber(recordNumber);
//		Example<AlphaSubject> example = Example.of(alphaSubject);
//		Optional<AlphaSubject> alphaSubjectOptional = alphaSubjectDao.findOne(example);
//		if (alphaSubjectOptional.isPresent()) {
//			return alphaSubjectOptional.get();
//		} else {
//			return null;
//		}
//	}

	@Override
	public AlphaSubject save(AlphaSubject alphaSubject) {
		return alphaSubjectDao.save(alphaSubject);
	}

//	@Override
//	public void deleteBySourceTypeAndSourceId(Integer sourceType, Integer sourceId) {
//		alphaSubjectDao.deleteBySourceTypeAndSourceId(sourceType, sourceId);
//	}

//	@Override
//	public void deleteBySourceTypeAndSourceDetailId(Integer sourceType, Integer sourceDetailId) {
//		AlphaSubject alphaSubject = alphaSubjectDao.findBySourceTypeAndSourceDetailId(sourceType, sourceDetailId);
//		if (alphaSubject != null) {
//			CpExcelDetail cpExcelDetail = new CpExcelDetail();
//			cpExcelDetail.setCustomerSubjectId(alphaSubject.getId());
//			Example<CpExcelDetail> example = Example.of(cpExcelDetail);
//			if (cpExcelDetailDao.exists(example)) {
//				cpExcelDetailDao.deleteById(alphaSubject.getId());
//			}
//		}
//	}
	@Override
	public List<AlphaSubject> findAllById(List<Integer> ids) {
	 	return	alphaSubjectDao.findAllById(ids);

	}

	@Override
	public List<AlphaSubject> findAllBySubjectType(Integer subjectType) {
		return alphaSubjectDao.findAllBySubjectType(subjectType);
	}

	@Override
	public Page<AlphaSubject> findPageByIds(List<Integer> ids, Pageable pageable) {
		return alphaSubjectDao.findPageByIds(ids, pageable);
	}
}