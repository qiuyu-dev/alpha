package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.CpExcelDetailDao;
import com.mysoft.alpha.dao.CustomerProductDao;
import com.mysoft.alpha.dao.ProductDao;
import com.mysoft.alpha.entity.CustomerProduct;
import com.mysoft.alpha.entity.Product;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.service.AlphaSubjectService;
import com.mysoft.alpha.service.CpExcelService;
import com.mysoft.alpha.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品(Product)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:14:10
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    AlphaSubjectService alphaSubjectService;
    @Autowired
    CpExcelService cpExcelService;
    @Autowired
    CpExcelDetailDao cpExcelDetailDao;
    
    @Autowired
    private CustomerProductDao customerProductDao;
    /**
     * 服务对象
     */
    @Autowired
    private ProductDao productDao;
    
    @Override
    public Product getProductById(Integer id) {
        return productDao.getOne(id);
    }

    @Override
    public boolean isExistProduct(String productName) {
        Product product = productDao.findByName(productName);
        return null != product;
    }

    @Override
    public Product findByName(String productName) {
        return productDao.findByName(productName);
    }
    
    @Override
    public Product findByRecordNumber(String recordNumber) {
    	return productDao.findByRecordNumber(recordNumber);
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

//    @Override
//    public void deleteById(Integer id) {
//         productDao.deleteById(id);
//    }

//    @Override
//    public void deleteBySourceTypeAndSourceId(Integer sourceType, Integer sourceId) {
//        List<Product> porductList = productDao.findBySourceTypeAndSourceId(sourceType, sourceId);
//        for (Product product : porductList) {
//            CpExcelDetail cpExcelDetail = new CpExcelDetail();
//            cpExcelDetail.setProductId(product.getId());
//            Example<CpExcelDetail> example = Example.of(cpExcelDetail);
//            if (!cpExcelDetailDao.exists(example)) {
//                productDao.deleteById(product.getId());
//            }
//        }
//    }

//    @Override
//    public void deleteBySourceTypeAndSourceDetailId(Integer sourceType, Integer sourceDetailId) {
//        Product product = productDao.findBySourceTypeAndSourceDetailId(sourceType, sourceDetailId);
//        if (product != null) {
//            CpExcelDetail cpExcelDetail = new CpExcelDetail();
//            cpExcelDetail.setProductId(product.getId());
//            Example<CpExcelDetail> example = Example.of(cpExcelDetail);
//            if (cpExcelDetailDao.exists(example)) {
//                productDao.deleteById(product.getId());
//            }
//        }
//    }
@Override
    public List<Product> findByAlphaSubjectId(Integer alphaSubjectId) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "productType"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "alphaSubjectId"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "id"));
        List<Product> productList = productDao.findByAlphaSubjectId(alphaSubjectId);
        List<Product> returnList = new ArrayList<>();
        for (Product product : productList) {
            product.setAlphaSubject(alphaSubjectService.getAlphaSubjectById(product.getAlphaSubjectId()));
            returnList.add(product);
        }
        return returnList;

    }
    @Override
    public List<Product> findAll() {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "productType"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "alphaSubjectId"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "id"));
        List<Product> productList = productDao.findAll(Sort.by(orders));
        List<Product> returnList = new ArrayList<>();
        for (Product product : productList) {
            product.setAlphaSubject(alphaSubjectService.getAlphaSubjectById(product.getAlphaSubjectId()));
            returnList.add(product);
        }
        return returnList;
    }
    
    @Override
    public void deleteProductById(Integer id) {
    	List<CustomerProduct> customerProductList = customerProductDao.findByProductId(id);
    	if(customerProductList != null && customerProductList.size() > 0) {
    		throw new CustomException(0, "服务使用中，不可删除");
    	}
    	productDao.deleteById(id);
    }

	@Override
	public boolean isExistRecordNumber(String recordNumber) {
        Product product = productDao.findByRecordNumber(recordNumber);
        return null != product;
	}
}