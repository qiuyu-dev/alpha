package com.mysoft.alpha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.AdminMenuService;

@RestController
@RequestMapping("/api/admin/v1/pri/menu")
public class MenuController {
    @Autowired
    AdminMenuService adminMenuService;

    @GetMapping("/menu")
    public Result menu() {
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByCurrentUser());
    }

    @GetMapping("/listbyroleid")
    public Result listAllMenusByRoledId(@RequestParam int rid) {
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByRoleId(rid));
    }

    @GetMapping("/list")
    public Result listAllMenus() {
        return ResultFactory.buildSuccessResult(adminMenuService.findAll());
    }
    
    @GetMapping("/currentuser")
    public Result currentuser() {
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByCurrentUser());
    }    

}
