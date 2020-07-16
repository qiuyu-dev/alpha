package com.mysoft.alpha.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.mysoft.alpha.dao.UserDAO;
import com.mysoft.alpha.dto.UserDTO;
import com.mysoft.alpha.entity.AdminRole;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.service.AdminRoleService;
import com.mysoft.alpha.service.AdminUserRoleService;
import com.mysoft.alpha.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    public List<UserDTO> list() {
        List<User> users = userDAO.findAll();

        // Find all roles in DB to enable JPA persistence context.
//        List<AdminRole> allRoles = adminRoleService.findAll();

        List<UserDTO> userDTOS = users
                .stream().map(user -> (UserDTO) new UserDTO().convertFrom(user)).collect(Collectors.toList());

        userDTOS.forEach(u -> {
            List<AdminRole> roles = adminRoleService.listRolesByUser(u.getUsername());
            u.setRoles(roles);
        });

        return userDTOS;
    }

    public boolean isExist(String username) {
        User user = userDAO.findByUsername(username);
        return null != user;
    }
    
    public boolean isExistOrgcode(String orgcode) {
    	User user = userDAO.findByOrgcode(orgcode);
    	return null != user;
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password) {
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public int register(User user) {
        String username = user.getUsername();
        String name = user.getName();
        String phone = user.getPhone();
        String email = user.getEmail();
        String password = user.getPassword();
        String crop = user.getCrop();
        String orgcode = user.getOrgcode();

        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        phone = HtmlUtils.htmlEscape(phone);
        user.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);
        crop = HtmlUtils.htmlEscape(crop);
        user.setCrop(crop);
        orgcode = HtmlUtils.htmlEscape(orgcode);
        user.setOrgcode(orgcode);
        user.setEnabled(true);

        if (username.equals("") || password.equals("")) {
            return 0;
        }

        boolean exist = isExist(username);

        if (exist) {
            return 2;
        }

        boolean existOrgcode = isExistOrgcode(orgcode);
        if (existOrgcode) {
        	return 3;
        }
        
        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);

        userDAO.save(user);

        return 1;
    }

    public void updateUserStatus(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setEnabled(user.isEnabled());
        userDAO.save(userInDB);
    }

    public User resetPassword(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        userInDB.setPassword(encodedPassword);
        return userDAO.save(userInDB);
    }

    public void editUser(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setName(user.getName());
        userInDB.setPhone(user.getPhone());
        userInDB.setEmail(user.getEmail());
        userDAO.save(userInDB);
        adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
    }

    public void deleteById(int id) {
        userDAO.deleteById(id);
    }
}
