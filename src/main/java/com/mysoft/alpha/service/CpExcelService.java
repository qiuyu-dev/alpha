package com.mysoft.alpha.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysoft.alpha.entity.CpExcelDetail;
import com.mysoft.alpha.entity.CpExcelMst;

/**
 * 客户-产品Excel主表(CpExcelMst)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:14:05
 */
public interface CpExcelService {

    List<CpExcelDetail> findDetailByParamsOrderByIdAsc(Integer cpExcelMstId, List<Integer> status, String name,
                                                       String recordNumber, String productName, String outTradeNo);
    
    Page<CpExcelDetail> findPageByParamsAndSort(Integer cpExcelMstId, List<Integer> status, String name,
            String recordNumber, String productName, String outTradeNo, Pageable pageable);
    
    List<CpExcelMst> findMstAll();

    CpExcelMst saveMst(CpExcelMst cpExcelMst);

    boolean isExistOutTradeNo(String outTradeNo, Integer chargeId);

    boolean isExistOutTradeNo(Integer customerId, Integer productId, Date effectiveDate, Date closingDate);

    CpExcelDetail saveDetail(CpExcelDetail cpExceldetail);

    boolean isExistFileName(String fileName, String chargeId);

    List<CpExcelMst> findMstByChargeSubjectIdOrderById(Integer chargeSubjectId);

    List<CpExcelMst> findMstByPaySubjectIdOrderById(Integer paySubjectId);

    CpExcelDetail getDetailById(Integer detailId);

    CpExcelMst getMstById(Integer mstId);
    
    CpExcelMst findMstByChargeSubjectIdAndBatchNum(Integer chargeSubjectId,String batchNum);

    void deleteDetailById(Integer cpExcelDetailId);
    
    int updateDetailAll(Integer mstId, Integer  status);
    
    int updateDetail(Integer mstId, Integer  status, List<Integer>  ids);

    List<CpExcelDetail> findDetailByCustomerSubjectId(Integer customerSubjectId);
    
    Page<CpExcelDetail> findDetailByPage(Integer mstId, List<Integer> status,Pageable pageable);
    
    Page<Map> findDetailDTOPageByParams(Integer mstId, List<Integer> status,Pageable pageable);

	Page<CpExcelDetail> findDetailPageByParamsAndSort(Integer paySubjectId, List<Integer> status, String name,
			String recordNumber, String productName, String outTradeNo, Pageable pageable);

}