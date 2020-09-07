package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AdminRoleDao;
import com.mysoft.alpha.dao.AdminUserRoleDao;
import com.mysoft.alpha.entity.*;
import com.mysoft.alpha.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色(AdminRole)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:13:23
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {
    /**
     * 服务对象
     */
    @Autowired
    private AdminRoleDao adminRoleDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminUserRoleDao adminUserRoleDao;

    @Autowired
    private AdminPermissionService adminPermissionService;

    @Autowired
    private AdminMenuService adminMenuService;

    @Autowired
    private AdminRolePermissionService adminRolePermissionService;

    @Override
    public List<AdminRole> listRolesByUser(String username) {
        int uid = userService.findByUsername(username).getId();
        List<Integer> rids =
                adminUserRoleDao.findAllByUid(uid).stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        return adminRoleDao.findAllById(rids);
    }

    @Override
    public List<AdminRole> listWithPermsAndMenus() {
        List<AdminRole> roles = adminRoleDao.findAll();
        for (AdminRole role : roles) {
            List<AdminPermission> perms;
            List<AdminMenu> menus;
            perms = adminPermissionService.listPermsByRoleId(role.getId());
            menus = adminMenuService.getMenusByRoleId(role.getId());
            role.setPerms(perms);
            role.setMenus(menus);
        }
        return roles;
    }

    @Override
    public List<AdminRole> listSubRolesByUser(String username) {
        List<AdminRole> roles = new ArrayList<>();
        if (!username.equals("admin")) {
            User user = userService.findByUsername(username);
            AdminRole role = adminRoleDao.getOne(adminUserRoleDao.findAllByUid(user.getId()).get(0).getRid());
            roles = adminRoleDao.findByNameZhLikeOrderByIdAsc(role.getNameZh().substring(0, 1) + "%");
        } else {
            roles = adminRoleDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
        }
        return roles;
    }

    @Override
    public AdminRole updateRoleStatus(AdminRole role) {
        AdminRole roleInDB = adminRoleDao.getOne(role.getId());
        roleInDB.setEnabled(role.getEnabled());
        return adminRoleDao.save(roleInDB);
    }

    @Override
    public void addOrUpdate(AdminRole role) {
        adminRoleDao.save(role);
    }

    @Override
    public void editRole(AdminRole role) {
        adminRoleDao.save(role);
        adminRolePermissionService.savePermChanges(role.getId(), role.getPerms());
    }

}