package com.mysoft.alpha.controller;

import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户(User)表控制层
 *
 * @author makejava
 * @since 2020-08-02 16:14:15
 */
@RestController
@RequestMapping("/api/admin/user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    @GetMapping("/getByUsername")
    public Result getUser(@RequestBody @Valid  String username)  throws CustomException {
        User user = userService.findByUsername(username);
        return ResultFactory.buildSuccessResult(user.getName());
    }

    @GetMapping("/list")
    public Result listUsers()  throws CustomException {
        return ResultFactory.buildSuccessResult(userService.list(SecurityUtils.getSubject().getPrincipal().toString()));
    }

    @PutMapping("/status")
    @Transactional
    public Result updateUserStatus(@RequestBody @Valid User requestUser)  throws CustomException {
        userService.updateUserStatus(requestUser);
        return ResultFactory.buildSuccessResult("用户状态更新成功");
    }

    @PutMapping("/password")
    @Transactional
    public Result resetPassword(@RequestBody @Valid User requestUser)  throws CustomException {
        userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult("重置密码成功");
    }

    @PutMapping("/edit")
    @Transactional
    public Result editUser(@RequestBody @Valid User requestUser)  throws CustomException {
        userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }


}