package com.mysoft.alpha.service;

import com.mysoft.alpha.dto.UserDTO;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.model.RegisterForm;

import java.util.List;

/**
 * 用户(User)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:14:13
 */
public interface UserService {

    User findByUsername(String userName);

    User findByUsernameAndEmail(String userName, String email);

    int register(RegisterForm registerForm);

    List<UserDTO> list(String toString);

    void updateUserStatus(User requestUser);

    User resetPassword(User requestUser);

    void editUser(User requestUser);
}