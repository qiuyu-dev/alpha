package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.dto.UserDTO;
import com.mysoft.alpha.entity.User;


public interface UserService {

	public List<UserDTO> list();

    public boolean isExist(String username);
    
    public boolean isExistOrgcode(String orgcode);

    public User findByUsername(String username);

    public User get(String username, String password);
    
    public int register(User user);

    public void updateUserStatus(User user);

    public User resetPassword(User user);

    public void editUser(User user);

    public void deleteById(int id);
}
