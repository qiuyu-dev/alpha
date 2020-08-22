package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.*;
import com.mysoft.alpha.entity.CpExcelDetail;
import com.mysoft.alpha.entity.CpExcelMst;
import com.mysoft.alpha.service.CpExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 客户-产品Excel主表(CpExcelMst)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:14:06
 */
@Service
public class CpExcelServiceImpl implements CpExcelService {
    /**
     * excel主表服务对象
     */
    @Autowired
    private CpExcelMstDao cpExcelMstDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AdminUserRoleDao adminUserRoleDao;
    @Autowired
    private AdminRoleDao adminRoleDao;
    @Autowired
    private AlphaSubjectDao alphaSubjectDao;

    /**
     * excel明细服务对象
     */
    @Autowired
    private CpExcelDetailDao cpExcelDetailDao;

    public CpExcelMst saveMst(CpExcelMst cpExcelMst) {
        return cpExcelMstDao.save(cpExcelMst);
    }

    public CpExcelDetail saveDetail(CpExcelDetail cpExceldetail) {
        return cpExcelDetailDao.save(cpExceldetail);
    }

    public void saveAllDetails(List<CpExcelDetail> cpExcelDetails) {
        cpExcelDetailDao.saveAll(cpExcelDetails);
    }

    @Override
    public boolean isExistOutTradeNoe(String outTradeNo, Integer chargeId) {
        CpExcelDetail cpExcelDetail = new CpExcelDetail();
        cpExcelDetail.setOutTradeNo(outTradeNo);
        Example<CpExcelDetail> example = Example.of(cpExcelDetail);
        List<CpExcelDetail> cpExcelDetailList = cpExcelDetailDao.findAll(example);
        for(CpExcelDetail cpExcelDetail1:cpExcelDetailList){
            CpExcelMst cpExcelMst = cpExcelMstDao.getOne(cpExcelDetail1.getCpExcelMstId());
            if(cpExcelMst.getChargeSubjectId()!=null && cpExcelMst.getChargeSubjectId().compareTo(
                    chargeId) ==0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isExistOutTradeNoe(Integer customerId, Integer productId, Date effectiveDate, Date closingDate) {
        List<CpExcelDetail> cpExcelDetails =
                cpExcelDetailDao.findByCustomerSubjectIdAndProductId(customerId, productId);
        for (CpExcelDetail cpExcelDetail : cpExcelDetails) {
            //            String effective = DateUtil.getDateByFormat(effectiveDate.toString(), "yyyy-MM-dd");
            //            String closing = DateUtil.getDateByFormat(closingDate.toString(), "yyyy-MM-dd");
            //            String begin = DateUtil.getDateByFormat(cpExcelDetail.getEffectiveDate().toString(), "yyyy-MM-dd");
            //            String end = DateUtil.getDateByFormat(cpExcelDetail.getClosingDate().toString(), "yyyy-MM-dd");

            //            if (!((effective.compareTo(begin) < 0 && closing.compareTo(begin) < 0) ||
            //                    (effective.compareTo(end) > 0 && closing.compareTo(end) > 0))) {
            if (!((effectiveDate.before(cpExcelDetail.getEffectiveDate()) &&
                    closingDate.before(cpExcelDetail.getEffectiveDate())) ||
                    (effectiveDate.after(cpExcelDetail.getClosingDate()) &&
                            closingDate.after(cpExcelDetail.getClosingDate())))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CpExcelMst findMstByFileName(String fileName) {
        return cpExcelMstDao.findByFileName(fileName);
    }

    @Override
    public List<CpExcelMst> findMstByPaySubjectIdOrderById(Integer paySubjectId) {
        return cpExcelMstDao.findByPaySubjectIdOrderByIdDesc(paySubjectId);
    }

    @Override
    public List<CpExcelMst> findMstByChargeSubjectIdOrderById(Integer chargeSubjectId) {
        return cpExcelMstDao.findByChargeSubjectIdOrderByIdDesc(chargeSubjectId);
    }

    @Override
    public void deleteDetailByCpExcelMstId(Integer cpExcelMstId) {
        cpExcelDetailDao.deleteByCpExcelMstId(cpExcelMstId);

    }

    @Override
    public void deleteMstById(Integer cpExcelMstId) {
        cpExcelMstDao.deleteById(cpExcelMstId);

    }

    @Override
    public void deleteDetailById(Integer cpExcelDetailId) {
        cpExcelDetailDao.deleteById(cpExcelDetailId);

    }

    @Override
    public List<CpExcelDetail> findDetailByCpExcelMstId(Integer cpExcelMstId) {

        return cpExcelDetailDao.findByCpExcelMstId(cpExcelMstId);
    }

    @Override
    public List<CpExcelDetail> findDetailByCpExcelMstIdAndStateInOrderByIdAsc(Integer cpExcelMstId,
                                                                              List<Integer> status) {
        return cpExcelDetailDao.findByCpExcelMstIdAndStateInOrderByIdAsc(cpExcelMstId, status);
    }

    @Override
    public List<CpExcelMst> findMstAll() {
        return cpExcelMstDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }


    @Override
    public boolean isExistFileName(String fileName, String chargeId) {
        CpExcelMst cpExcelMst1 = new CpExcelMst();
        cpExcelMst1.setFileName(fileName);
//        cpExcelMst1.setChargeSubjectId(Integer.valueOf(chargeId));
        Example<CpExcelMst> example = Example.of(cpExcelMst1);
//        System.out.println("cpExcelMstDao.exists(example):" + cpExcelMstDao.exists(example));
        return cpExcelMstDao.exists(example);
        //        CpExcelMst cpExcelMst = cpExcelMstDao.findByFileName(fileName);
        //        return null != cpExcelMst;
    }

    public CpExcelDetail getDetailById(Integer detailId) {
        return cpExcelDetailDao.getOne(detailId);
    }

    public CpExcelMst getMstById(Integer mstId) {
        return cpExcelMstDao.getOne(mstId);
    }


}