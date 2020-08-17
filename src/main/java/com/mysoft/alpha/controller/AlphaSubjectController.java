package com.mysoft.alpha.controller;

import com.mysoft.alpha.entity.AlphaSubject;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.AlphaSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主体(AlphaSubject)表控制层
 *
 * @author makejava
 * @since 2020-08-06 10:33:08
 */
@RestController
@RequestMapping("/api/admin/v1/pub/alphaSubject")
public class AlphaSubjectController {
    /**
     * 服务对象
     */
    @Autowired
    private AlphaSubjectService alphaSubjectService;

    @GetMapping("/getNameById")
    public Result getAlphaSubject(Integer id)  throws CustomException {
        AlphaSubject alphaSubject = alphaSubjectService.getAlphaSubjectById(id);
        return ResultFactory.buildSuccessResult(alphaSubject);
    }

    @GetMapping("/charge/list")
    public Result getAllCompanyService()  throws CustomException {
        return ResultFactory.buildSuccessResult(alphaSubjectService.findAllBySubjectType(3));
    }


}