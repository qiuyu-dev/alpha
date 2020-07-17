package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.dto.UserDTO;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.model.RegisterForm;


public interface UserService {

	public List<UserDTO> list();

    public boolean isExistUsername(String username);
    
    public User findByUsername(String username);

    public User get(String username, String password);
    
    public int register(User user);
    
    public int register(RegisterForm registerForm);

    public void updateUserStatus(User user);

    public User resetPassword(User user);

    public void editUser(User user);

    public void deleteById(int id);
}
