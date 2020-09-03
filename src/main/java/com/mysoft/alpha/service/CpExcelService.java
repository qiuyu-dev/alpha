package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.CpExcelDetail;
import com.mysoft.alpha.entity.CpExcelMst;

import java.util.Date;
import java.util.List;

/**
 * 客户-产品Excel主表(CpExcelMst)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:14:05
 */
public interface CpExcelService {

    List<CpExcelDetail> findDetailByParamsOrderByIdAsc(Integer cpExcelMstId, List<Integer> status, String name,
                                                       String recordNumber, String productName, String outTradeNo);


    boolean isExistProductId(Integer productId);

    List<CpExcelMst> findMstAll();

    CpExcelMst saveMst(CpExcelMst cpExcelMst);

    void saveAllDetails(List<CpExcelDetail> cpExcelDetails);

    boolean isExistOutTradeNo(String outTradeNo, Integer chargeId);

    boolean isExistOutTradeNo(Integer customerId, Integer productId, Date effectiveDate, Date closingDate);

    CpExcelMst findMstByFileName(String fileName);

    void deleteDetailByCpExcelMstId(Integer cpExcelMstId);

    void deleteMstById(Integer cpExcelMstId);

    List<CpExcelDetail> findDetailByCpExcelMstId(Integer cpExcelMstId);

    List<CpExcelDetail> findDetailByCpExcelMstIdAndStateInOrderByIdAsc(Integer cpExcelMstId, List<Integer> status);

    CpExcelDetail saveDetail(CpExcelDetail cpExceldetail);

    //    List<CpExcelMst> findCpExcelMstByUser(String username);

    boolean isExistFileName(String fileName, String chargeId);

    List<CpExcelMst> findMstByChargeSubjectIdOrderById(Integer chargeSubjectId);

    List<CpExcelMst> findMstByPaySubjectIdOrderById(Integer paySubjectId);

    CpExcelDetail getDetailById(Integer detailId);

    CpExcelMst getMstById(Integer mstId);

    void deleteDetailById(Integer cpExcelDetailId);


	List<CpExcelDetail> findDetailByCustomerSubjectId(Integer customerSubjectId);

}