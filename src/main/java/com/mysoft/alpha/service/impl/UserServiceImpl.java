package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AdminUserRoleDao;
import com.mysoft.alpha.dao.AlphaSubjectDao;
import com.mysoft.alpha.dao.UserDao;
import com.mysoft.alpha.dto.UserDTO;
import com.mysoft.alpha.entity.AdminRole;
import com.mysoft.alpha.entity.AdminUserRole;
import com.mysoft.alpha.entity.AlphaSubject;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户(User)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:14:14
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 服务对象
     */
    @Autowired
    private UserDao userDao;

    @Autowired
    private AlphaSubjectDao alphaSubjectDao;

    @Autowired
    private AdminUserRoleDao adminUserRoleDao;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminUserRoleService adminUserRoleService;

    @Override
    public User findByUsername(String userName) {
        return userDao.findByUsername(userName);
    }
    @Override
	public User findByUsernameAndEmail(String userName, String email) {
		return  userDao.findByUsernameAndEmail(userName, email);
	}
//    @Override
//    public User getUserById(Integer id) {
//        return userDao.getOne(id);
//    }
    
    @Transactional
    public int register(RegisterForm registerForm) {
        int ret = validateForm(registerForm);
        if (ret != 0) {
            return ret;
        }
        User user = new User();
        String crop = registerForm.getCrop();
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

        //保存公司信息
        AlphaSubject alphaSubject = new AlphaSubject();
        if (registerForm.getItype() == 1) {//注册企业和企业管理用户
            alphaSubject.setSubjectType(registerForm.getCtype());//1、客户，2、保险企业，3、服务企业
            alphaSubject.setRecordType("组织机构代码");
            alphaSubject.setRecordNumber(registerForm.getOrgcode());
            alphaSubject.setName(crop);
            alphaSubject.setPhone(phone);
            alphaSubject.setSourceType(2);
            alphaSubject.setEnabled(1);
            alphaSubject.setOperator(username);
            AlphaSubject alphaSubject1 = alphaSubjectDao.save(alphaSubject);
            user.setAlphaSubjectId(alphaSubject1.getId());//存入关联id
            user.setOperator(username);
        }else {
            User currUser = userDao.findByUsername(SecurityUtils.getSubject().getPrincipal().toString());
            user.setAlphaSubjectId(currUser.getAlphaSubjectId());//存入关联id
            user.setOperator(SecurityUtils.getSubject().getPrincipal().toString());
        }

        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        user.setSalt(salt);
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        user.setPassword(encodedPassword);
        User userNew = userDao.save(user);

        if (registerForm.getItype() == 1) {
            //添加对应角色
            AdminUserRole adminUserRole = new AdminUserRole();
            adminUserRole.setUid(userNew.getId());
            AlphaSubject enterprise = alphaSubjectDao.getOne(userNew.getAlphaSubjectId());
            switch (enterprise.getSubjectType()) {
                case 2:
                    //2、保险商（产品企业）=采购-管理岗
                    adminUserRole.setRid(2);
                    break;
                case 3:
                    //3、服务商（服务企业）=服务-管理岗
                    adminUserRole.setRid(3);
                    break;
                default:
                    adminUserRole.setRid(0);
            }
            adminUserRoleDao.save(adminUserRole);
        }
        return 1;
    }

    @Override
    public List<UserDTO> list(String username) {
        //根据登录用户，查询用户信息
        List<User> users = new ArrayList<>();
        if (username.equals("admin")) {
            users = userDao.findAll();
        } else {
            User loginUser = userDao.findByUsername(username);
            users = userDao.findByAlphaSubjectIdOrderByIdDesc(loginUser.getAlphaSubjectId());
        }

        // Find all roles in DB to enable JPA persistence context.
        //        List<AdminRole> allRoles = adminRoleService.findAll();

        List<UserDTO> userDTOs =
                users.stream().map(user -> (UserDTO) new UserDTO().convertFrom(user)).collect(Collectors.toList());

        userDTOs.forEach(u -> {
            List<AdminRole> roles = adminRoleService.listRolesByUser(u.getUsername());
            u.setRoles(roles);
        });

        return userDTOs;
    }

    @Override
    public void updateUserStatus(User user) {
        User userInDB = userDao.findByUsername(user.getUsername());
        userInDB.setEnabled(user.getEnabled());
        userDao.save(userInDB);
    }
    @Override
    public User resetPassword(User user) {
        User userInDB = userDao.findByUsername(user.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        userInDB.setPassword(encodedPassword);
        return userDao.save(userInDB);
    }
    @Override
    public void editUser(User user) {
        User userInDB = userDao.findByUsername(user.getUsername());
        userInDB.setName(user.getName());
        userInDB.setPhone(user.getPhone());
        userInDB.setEmail(user.getEmail());
        userDao.save(userInDB);
        adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
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

    private boolean isExistUsername(String username) {
        User user = userDao.findByUsername(username);
        return null != user;
    }

    private boolean isExistOrgcode(String orgcode) {
        AlphaSubject alphaSubject = alphaSubjectDao.findByRecordNumber(orgcode);
        return null != alphaSubject;
    }

    @Override
    public User findById(Integer id) {
        return (User)userDao.getOne(id);
    }

    @Override
    public User findByUserNameAndNameAndEnabled(String userName, String name,Integer enabled) {
        return userDao.findByUsernameAndNameAndEnabled(userName, name,enabled);
    }


    @Override
    public List<User> findSubUsers(Integer userId) {
        return userDao.findBySupUseridOrderByIdAsc(userId);
    }
}