package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.ComplaintDao;
import com.mysoft.alpha.dao.CustomerProductDao;
import com.mysoft.alpha.dao.UserDao;
import com.mysoft.alpha.entity.CustomerProduct;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.service.AlphaSubjectService;
import com.mysoft.alpha.service.CustomerProductService;
import com.mysoft.alpha.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 客户-企业-产品订单(CustomerProduct)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:13:55
 */
@Service
public class CustomerProductServiceImpl implements CustomerProductService {
    /**
     * 服务对象
     */
    @Autowired
    private CustomerProductDao customerProductDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AlphaSubjectService alphaSubjectService;
    @Autowired
    private ComplaintDao complaintDao;


    public  CustomerProduct  getOneById(Integer id){
        return customerProductDao.getOne(id);
    }


    @Override
    public List<CustomerProduct> findBySourceMstIdIsInOrderById(List<Integer> sourceMstIds) {
        return customerProductDao.findBySourceIdIsInOrderById(sourceMstIds);
    }

    public  List<CustomerProduct>  findAll(){
        return customerProductDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }


    @Override
    public List<CustomerProduct> findBySourceDetailIdIsInOrderById(List<Integer> detailIds) {
        return customerProductDao.findBySourceDetailIdIsInOrderById(detailIds);
    }

    public void saveAll(List<CustomerProduct> customerProductList){
        customerProductDao.saveAll(customerProductList);
    }

    @Override
    public boolean isExistOutTradeNoe(Integer customerId, Integer productId, Date effectiveDate, Date closingDate) {
        List<CustomerProduct> customerProducts =
                customerProductDao.findByCustomerSubjectIdAndProductId(customerId, productId);
        for (CustomerProduct customerProduct : customerProducts) {
            String effective = DateUtil.getDateByFormat(effectiveDate.toString(), "yyyy-MM-dd");
            String closing = DateUtil.getDateByFormat(closingDate.toString(), "yyyy-MM-dd");
            String begin = DateUtil.getDateByFormat(customerProduct.getEffectiveDate().toString(), "yyyy-MM-dd");
            String end = DateUtil.getDateByFormat(customerProduct.getClosingDate().toString(), "yyyy-MM-dd");

            if (!((effective.compareTo(begin) < 0 && closing.compareTo(begin) < 0) ||
                    (effective.compareTo(end) > 0 && closing.compareTo(end) > 0))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteBySourceTypeAndSourceId(Integer sourceType, Integer sourceId) {
        customerProductDao.deleteBySourceTypeAndSourceId(sourceType, sourceId);
    }

    @Override
    public void deleteBySourceTypeAndSourceDetailId(Integer sourceType, Integer sourceDetailId) {
        List<CustomerProduct> customerProductList =
                customerProductDao.findBySourceDetailIdIsInOrderById(Arrays.asList(sourceDetailId));
        for(CustomerProduct customerProduct:customerProductList){
            if(customerProduct.getState()>6){
                throw new CustomException(0, "已开始付费流程，不可删除");
            }

        }
        customerProductDao.deleteBySourceTypeAndSourceDetailId(sourceType, sourceDetailId);
    }
}