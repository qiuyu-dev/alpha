package com.mysoft.alpha.controller;

import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/v1/pri")
public class MenuController {
    @Autowired
    AdminMenuService adminMenuService;

    @GetMapping("/menu")
    public Result menu() throws CustomException {
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByCurrentUser());
    }

    @GetMapping("/menus/listbyroleid")
    public Result listAllMenusByRoledId(@RequestParam int rid)  throws CustomException {
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByRoleId(rid));
    }

    @GetMapping("/menus/list")
    public Result listAllMenus() throws CustomException  {
        return ResultFactory.buildSuccessResult(adminMenuService.findAll());
    }
    
    @GetMapping("/menu/currentuser")
    public Result currentuser()  throws CustomException {
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByCurrentUser());
    }    

}
