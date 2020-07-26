package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.*;
import com.mysoft.alpha.entity.*;
import com.mysoft.alpha.service.CpExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Service 业务层
 * @Transactional 事务管理
 */

@Service
@Transactional
public class CpExcelServiceImpl implements CpExcelService {

    /**
     * excel主表
     */
    @Autowired
    private CustomerProductExcelMstDAO customerProductExcelMstDAO;

    /**
     * Excel明细
     */
    @Autowired
    private CustomerProductExcelDetailDAO customerProductExcelDetailDAO;
    /**
     * 客户-企业
     */
    @Autowired
    private CustomerEnterpriseDAO customerEnterpriseDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AdminUserRoleDAO adminUserRoleDAO;
    @Autowired
    private AdminRoleDAO adminRoleDAO;


    @Override
    public List<CustomerProductExcelMst> findCpExcelByUser(String username) {
        List<CustomerProductExcelMst> retrunList = new ArrayList<>();
        if (username.equals("admin")) {
            retrunList = customerProductExcelMstDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
        } else {
            User user = userDAO.findByUsername(username);
            int roleId = adminUserRoleDAO.findAllByUid(user.getId()).get(0).getRid();

            AdminRole role = adminRoleDAO.findById(roleId);
            if (role.getNameZh().contains("管理")) {
                retrunList = customerProductExcelMstDAO.findByFromIdOrderByIdDesc(user.getCompany().getId());
            } else {
                retrunList = customerProductExcelMstDAO.findByOperatorOrderByIdDesc(user.getUsername());
            }
        }
        return retrunList;
    }

    public void taskCpExcelToCustomerEnterprise() {
        int mstCtpe = 2;
        //excel主表循环
        List<CustomerProductExcelMst> cPExcelMstsList =
                customerProductExcelMstDAO.findCustomerProductExcelMstsByCtypeOrderByIdAsc(1);
        for (CustomerProductExcelMst cPExcelMst : cPExcelMstsList) {
            //Excel 明细循环
            List<CustomerProductExcelDetail> cPExcelDetailsList =cPExcelMst.getCpExcelDetails();
                   // customerProductExcelDetailDAO.findCustomerProductExcelDetailsByCpExcelMstOrderByIdAsc(cPExcelMst);
            for (CustomerProductExcelDetail cPExcelDetail : cPExcelDetailsList) {
                //对应明细确认客户-企业是否有记录
                CustomerEnterprise customerEnterprise = customerEnterpriseDAO
                        .findFirstByInsuredIdAndEidAndBeginTimeAndEndTimeOrderByIdIdDesc(cPExcelDetail.getInsuredId(),
                                cPExcelMst.getToId(), cPExcelDetail.getEffectiveDate(), cPExcelDetail.getClosingDate());
                //对应明细确认客户-企业如果没有记录，增加记录
                if (customerEnterprise == null) {
                    CustomerEnterprise newCustomerEnterprise = new CustomerEnterprise();
                    newCustomerEnterprise.setCertificateType(cPExcelDetail.getCertificateType());
                    newCustomerEnterprise.setInsuredId(cPExcelDetail.getInsuredId());
                    newCustomerEnterprise.setCname(cPExcelDetail.getInsuredName());
                    newCustomerEnterprise.setPhonenum(cPExcelDetail.getPhonenum());
                    newCustomerEnterprise.setEid(Integer.valueOf(cPExcelMst.getToId()));
                    newCustomerEnterprise.setLocation(cPExcelDetail.getLocation());
                    newCustomerEnterprise.setAge(cPExcelDetail.getAge());
                    newCustomerEnterprise.setSex(cPExcelDetail.getSex());
                    newCustomerEnterprise.setBeginTime(cPExcelDetail.getEffectiveDate());
                    newCustomerEnterprise.setEndTime(cPExcelDetail.getClosingDate());
                    newCustomerEnterprise.setCestatus(1);
                    newCustomerEnterprise.setFromType(cPExcelMst.getFromType());
                    newCustomerEnterprise.setFromId(cPExcelMst.getFromId());
                    newCustomerEnterprise.setCpemId(cPExcelMst.getId());
                    newCustomerEnterprise.setCpedId(cPExcelDetail.getId());
                    newCustomerEnterprise
                            .setReson("来源：" + cPExcelMst.getCreateTime() + "，文件：" + cPExcelMst.getFileName());
                    newCustomerEnterprise.setOperator("system");
                    customerEnterpriseDAO.save(newCustomerEnterprise);

                }
                //对应明细确认客户-企业如果有记录，修改Excel明细说明
                else {
                    cPExcelDetail.setStatus(-2);
                    cPExcelDetail.setExplanation(
                            "原因：" + cPExcelMst.getCreateTime() + "，文件：" + cPExcelMst.getFileName() + "，第" +
                                    cPExcelDetail.getRowNum() + "行");
                    customerProductExcelDetailDAO.save(cPExcelDetail);
                }
            }
            cPExcelMst.setCtype(mstCtpe);
            customerProductExcelMstDAO.save(cPExcelMst);
        }
    }
}
