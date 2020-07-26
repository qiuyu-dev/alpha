package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AdminRoleDAO;
import com.mysoft.alpha.entity.*;
import com.mysoft.alpha.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {
    @Autowired
    AdminRoleDAO adminRoleDAO;

    @Autowired
    UserService userService;

    @Autowired
    AdminUserRoleService adminUserRoleService;

    @Autowired
    AdminPermissionService adminPermissionService;

    @Autowired
    AdminRolePermissionService adminRolePermissionService;

    @Autowired
    AdminMenuService adminMenuService;

    public List<AdminRole> listWithPermsAndMenus() {
        List<AdminRole> roles = adminRoleDAO.findAll();
        //		List<AdminPermission> perms;
        //		List<AdminMenu> menus; gll 20200724
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

    public List<AdminRole> findAll() {
        return adminRoleDAO.findAll();
    }

    public void addOrUpdate(AdminRole adminRole) {
        adminRoleDAO.save(adminRole);
    }

    public List<AdminRole> listRolesByUser(String username) {
        int uid = userService.findByUsername(username).getId();
        List<Integer> rids =
                adminUserRoleService.listAllByUid(uid).stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        return adminRoleDAO.findAllById(rids);
    }

    @Override
    public List<AdminRole> listSubRolesByUser(String username) {
        List<AdminRole> roles = new ArrayList<>();
        if (!username.equals("admin")) {
            User user = userService.findByUsername(username);
            AdminRole role = adminRoleDAO.findById(adminUserRoleService.listAllByUid(user.getId()).get(0).getRid());
            //            System.out.println("------------------substring" + role.getNameZh().substring(0, 2));
            roles = adminRoleDAO.findByNameZhLikeOrderByIdDesc(role.getNameZh().substring(0, 1) + "%");
        } else {
            roles = adminRoleDAO.findAll(Sort.by(Sort.Direction.ASC, "id"));
        }
        return roles;
    }

    public AdminRole updateRoleStatus(AdminRole role) {
        AdminRole roleInDB = adminRoleDAO.findById(role.getId());
        roleInDB.setEnabled(role.getEnabled());
        return adminRoleDAO.save(roleInDB);
    }

    @Transactional
    public void editRole(@RequestBody AdminRole role) {
        adminRoleDAO.save(role);
        adminRolePermissionService.savePermChanges(role.getId(), role.getPerms());
    }
}
