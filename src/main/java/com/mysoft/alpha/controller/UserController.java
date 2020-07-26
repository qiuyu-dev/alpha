package com.mysoft.alpha.controller;

import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.AdminUserRoleService;
import com.mysoft.alpha.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/admin/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    @GetMapping("/list")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.list(SecurityUtils.getSubject().getPrincipal().toString()));
    }

    @PutMapping("/status")
    @Transactional
    public Result updateUserStatus(@RequestBody @Valid User requestUser) {
        userService.updateUserStatus(requestUser);
        return ResultFactory.buildSuccessResult("用户状态更新成功");
    }

    @PutMapping("/password")
    @Transactional
    public Result resetPassword(@RequestBody @Valid User requestUser) {
        userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult("重置密码成功");
    }

    @PutMapping("/edit")
    @Transactional
    public Result editUser(@RequestBody @Valid User requestUser) {
        userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }
}
