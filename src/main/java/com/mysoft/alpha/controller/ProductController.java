package com.mysoft.alpha.controller;

import com.mysoft.alpha.common.ProductType;
import com.mysoft.alpha.common.SourceType;
import com.mysoft.alpha.entity.Product;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.ProductService;
import com.mysoft.alpha.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品(Product)表控制层
 *
 * @author makejava
 * @since 2020-08-02 16:14:11
 */
@RestController
@RequestMapping("/api/admin/v1/pub/product")
public class ProductController {
    /**
     * 服务对象
     */
    @Autowired
    private ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping("/getNameById")
    public Result getUser(Integer id)  throws CustomException {
        Product product = productService.getProductById(id);
        return ResultFactory.buildSuccessResult(product);
    }

    @PostMapping("/save")
    //    @Transactional 直接存储，没有事务
    public Result excelUpload(@RequestBody @Valid Product product)  throws CustomException {
        if (productService.isExistProduct(product.getName())) {
            return ResultFactory.buildFailResult("已有此产品");
        }

        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        User currUser = userService.findByUsername(operator);
        product.setAlphaSubjectId(currUser.getAlphaSubjectId());
        product.setProductType(ProductType.TYPE3.value());
        product.setSourceType(SourceType.TYPE2.value());
        product.setEnabled(1);
        product.setOperator(operator);
        productService.save(product);
        return ResultFactory.buildSuccessResult("保存成功");

    }

    @GetMapping("/list")
    public Result listServices()  throws CustomException {
        String operator = SecurityUtils.getSubject().getPrincipal().toString();
        List<Product> productList = new ArrayList<>();
        if (operator.equals("admin")) {
            productList = productService.findAll();
        } else {
            User currUser = userService.findByUsername(operator);
            productList = productService.findByAlphaSubjectId(currUser.getAlphaSubjectId());
        }
        return ResultFactory.buildSuccessResult(productList);
    }

    }