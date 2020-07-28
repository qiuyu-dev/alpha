package com.mysoft.alpha.controller;

import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.model.RegisterForm;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        Subject subject = SecurityUtils.getSubject();
//        subject.getSession().setTimeout(10000);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            User user = userService.findByUsername(username);
        	System.out.println("subject.isRemembered()="+subject.isRemembered());
        	System.out.println("subject.isAuthenticated()="+subject.isAuthenticated());
        	System.out.println("subject.getPrincipal()="+subject.getPrincipal());
            if (user.getEnabled().toString().equals("0")) {
                return ResultFactory.buildFailResult("该用户已被禁用");
            }
            return ResultFactory.buildSuccessResult(username);
        } catch (IncorrectCredentialsException e) {
            return ResultFactory.buildFailResult("密码错误");
        } catch (UnknownAccountException e) {
            return ResultFactory.buildFailResult("账号不存在");
        }
    }

    @PostMapping("/register")
    @Transactional
    public Result register(@RequestBody RegisterForm registerForm) {

        System.out.println("register");

        int status = userService.register(registerForm);
        switch (status) {
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("用户已存在");
            case 3:
            	return ResultFactory.buildFailResult("组织机构代码不能为空");
            case 4:
            	return ResultFactory.buildFailResult("组织机构代码已存在");
            case 5:
            	return ResultFactory.buildFailResult("公司名称不能为空"); 
            case 6:
            	return ResultFactory.buildFailResult("请选择公司类型"); 
            case 7:
            	return ResultFactory.buildFailResult("用户不能为空");
            case 8:
            	return ResultFactory.buildFailResult("密码不能为空");             	
            case 9:
            	return ResultFactory.buildFailResult("姓名不能为空");  
            case 10:
            	return ResultFactory.buildFailResult("联系电话不能为空");   
            case 11:
            	return ResultFactory.buildFailResult("email不能为空");               	
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @GetMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultFactory.buildSuccessResult("成功登出");
    }

    @GetMapping("/authentication")
    public String authentication() {
        return "身份认证成功";
    }
}
