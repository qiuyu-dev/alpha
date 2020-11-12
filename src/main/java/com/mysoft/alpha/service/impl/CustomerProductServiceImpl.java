package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.CustomerProductDao;
import com.mysoft.alpha.entity.CustomerProduct;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.service.CustomerProductService;
import com.mysoft.alpha.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    //	@Override
    //	public boolean isExistProductId(Integer productId) {
    //		CustomerProduct customerProduct = new CustomerProduct();
    //		customerProduct.setProductId(productId);
    //		Example<CustomerProduct> example = Example.of(customerProduct);
    //		return customerProductDao.exists(example);
    //	}

    @Override
    public CustomerProduct getOneById(Integer id) {
        return customerProductDao.getOne(id);
    }

    @Override
    public List<CustomerProduct> findBySourceMstIdInAndStatusIn(List<Integer> sourceMstIds, List<Integer> status) {
        return customerProductDao.findBySourceIdInAndStateInOrderById(sourceMstIds, status);
    }
    
    @Override
    public List<CustomerProduct> findAll() {
        return customerProductDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    
	@Override
	public Page<CustomerProduct> findAll(Pageable pageable) {
		return customerProductDao.findAll(pageable);
	}

    @Override
    public List<CustomerProduct> findBySourceDetailIdIsInOrderById(List<Integer> detailIds) {
        return customerProductDao.findBySourceDetailIdIsInOrderById(detailIds);
    }

    @Override
    public void save(CustomerProduct customerProduct) {
        customerProductDao.save(customerProduct);
    }

    @Override
    public void saveAll(List<CustomerProduct> customerProductList) {
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

    //	@Override
    //	public void deleteBySourceTypeAndSourceId(Integer sourceType, Integer sourceId) {
    //		customerProductDao.deleteBySourceTypeAndSourceId(sourceType, sourceId);
    //	}

    @Override
    public void deleteBySourceTypeAndSourceDetailId(Integer sourceType, Integer sourceDetailId) {
        List<CustomerProduct> customerProductList =
                customerProductDao.findBySourceDetailIdIsInOrderById(Arrays.asList(sourceDetailId));
        for (CustomerProduct customerProduct : customerProductList) {
            if (customerProduct.getState() > 6) {
                throw new CustomException(0, "已开始付费流程，不可删除");
            }
        }
        customerProductDao.deleteBySourceTypeAndSourceDetailId(sourceType, sourceDetailId);
    }

	@Override
	public List<CustomerProduct> findByProductId(Integer productId) {
		return customerProductDao.findByProductId(productId);
	}

	@Override
	public Page<CustomerProduct> findBySourceMstIdInAndStatusIn(List<Integer> sourceMstIds, List<Integer> status,
			Pageable pageable) {
		return customerProductDao.findBySourceIdInAndStateInOrderById(sourceMstIds, status, pageable);
	}

}