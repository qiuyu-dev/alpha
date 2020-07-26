package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AdminRoleDAO;
import com.mysoft.alpha.dao.AdminUserRoleDAO;
import com.mysoft.alpha.dao.CompanyDAO;
import com.mysoft.alpha.dao.UserDAO;
import com.mysoft.alpha.dto.UserDTO;
import com.mysoft.alpha.entity.AdminRole;
import com.mysoft.alpha.entity.AdminUserRole;
import com.mysoft.alpha.entity.Company;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.model.RegisterForm;
import com.mysoft.alpha.service.AdminRoleService;
import com.mysoft.alpha.service.AdminUserRoleService;
import com.mysoft.alpha.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    CompanyDAO companyDAO;

    @Autowired
    AdminRoleService adminRoleService;

    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleDAO adminRoleDAO;
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;


    public List<UserDTO> list(String username) {
        //根据登录用户，查询用户信息
        List<User> users = new ArrayList<>();

        if (username.equals("admin")) {
            users = userDAO.findAll();
        } else {
            User loginuser = userDAO.findByUsername(username);
            users = userDAO.findByCompanyOrderByIdDesc(loginuser.getCompany());
        }

        // Find all roles in DB to enable JPA persistence context.
        //        List<AdminRole> allRoles = adminRoleService.findAll();

        List<UserDTO> userDTOS =
                users.stream().map(user -> (UserDTO) new UserDTO().convertFrom(user)).collect(Collectors.toList());

        userDTOS.forEach(u -> {
            List<AdminRole> roles = adminRoleService.listRolesByUser(u.getUsername());
            u.setRoles(roles);
        });

        return userDTOS;
    }

    public boolean isExistUsername(String username) {
        System.out.println("-----------username:" + username);
        User user = userDAO.findByUsername(username);
        return null != user;
    }

    public boolean isExistOrgcode(String orgcode) {
        Company company = companyDAO.findByCode(orgcode);
        return null != company;
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

        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        phone = HtmlUtils.htmlEscape(phone);
        user.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);
        user.setEnabled(1);
        user.setCreateTime(new Date());
        if (username.equals("") || password.equals("")) {
            return 0;
        }

        boolean exist = isExistUsername(username);
        if (exist) {
            return 2;
        }

        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        user.setSalt(salt);
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        user.setPassword(encodedPassword);
        userDAO.save(user);

        return 1;
    }

    private int validateForm(RegisterForm registerForm) {
        if (registerForm.getItype() == 1) {
            if (StringUtils.isEmpty(registerForm.getOrgcode()) || registerForm.getOrgcode().trim().equals("")) {
                return 3;
            } else {
                boolean existOrgcode = isExistOrgcode(registerForm.getOrgcode());
                if (existOrgcode) {
                    return 4;
                }
            }

            if (StringUtils.isEmpty(registerForm.getCrop()) || registerForm.getCrop().trim().equals("")) {
                return 5;
            }

        if (registerForm.getCtype() == 0) {
            return 6;
        }
              }
        if (StringUtils.isEmpty(registerForm.getUsername()) || registerForm.getUsername().trim().equals("")) {
            return 7;
        } else {
            boolean exist = isExistUsername(registerForm.getUsername());
            if (exist) {
                return 2;
            }
        }

        if (StringUtils.isEmpty(registerForm.getPassword()) || registerForm.getPassword().trim().equals("")) {
            return 8;
        }

        if (StringUtils.isEmpty(registerForm.getName()) || registerForm.getName().trim().equals("")) {
            return 9;
        }

        if (StringUtils.isEmpty(registerForm.getPhone()) || registerForm.getPhone().trim().equals("")) {
            return 10;
        }

        if (StringUtils.isEmpty(registerForm.getEmail()) || registerForm.getEmail().trim().equals("")) {
            return 11;
        }

        return 0;
    }

    @Transactional
    public int register(RegisterForm registerForm) {
        int ret = validateForm(registerForm);
        if (ret != 0) {
            return ret;
        }
        User user = new User();
        String orgcode = registerForm.getOrgcode();
        String crop = registerForm.getCrop();
        int ctype = registerForm.getCtype();
        String username = registerForm.getUsername();
        String password = registerForm.getPassword();
        String name = registerForm.getName();
        String phone = registerForm.getPhone();
        String email = registerForm.getEmail();


        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        phone = HtmlUtils.htmlEscape(phone);
        user.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);
        user.setEnabled(1);
        user.setCreateTime(new Date());
        user.setOperator(username);

        //保存公司信息
        Company company = new Company();
        if (registerForm.getItype() == 1) {
            company.setCode(orgcode);
            company.setName(crop);
            company.setPhone(phone);
            company.setCtype(ctype);
            company.setEnabled(1);
            company.setCreateTime(new Date());
            company.setOperator(username);
            Company companyR = companyDAO.save(company);
              user.setCompany(companyR);//存入关联id
        }else {
            User currUser = userDAO.findByUsername(SecurityUtils.getSubject().getPrincipal().toString());
               user.setCompany(currUser.getCompany());//存入关联id
        }


        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        user.setSalt(salt);
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        user.setPassword(encodedPassword);

        User userNew = userDAO.save(user);

        if (registerForm.getItype() == 1) {

            //添加对应角色
            AdminUserRole adminUserRole = new AdminUserRole();
            adminUserRole.setUid(userNew.getId());

            switch (userNew.getCompany().getCtype().intValue()) {
                case 1:
                    //1、保险商（产品企业）=采购-管理岗
                    adminUserRole.setRid(2);
                    break;
                case 2:
                    //2、服务商（服务企业）=服务-管理岗
                    adminUserRole.setRid(3);
                    break;
                default:
                    adminUserRole.setRid(0);

            }
            adminUserRoleDAO.save(adminUserRole);
        }
        return 1;
    }

    public void updateUserStatus(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setEnabled(user.getEnabled());
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
