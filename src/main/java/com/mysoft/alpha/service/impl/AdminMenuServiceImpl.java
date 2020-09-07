package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AdminMenuDao;
import com.mysoft.alpha.dao.AdminRoleMenuDao;
import com.mysoft.alpha.dao.AdminUserRoleDao;
import com.mysoft.alpha.entity.AdminMenu;
import com.mysoft.alpha.entity.AdminRoleMenu;
import com.mysoft.alpha.entity.AdminUserRole;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.service.AdminMenuService;
import com.mysoft.alpha.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单(AdminMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:12:35
 */
@Service
public class AdminMenuServiceImpl implements AdminMenuService {
    /**
     * 服务对象
     */
    @Autowired
    private AdminMenuDao adminMenuDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminUserRoleDao adminUserRoleDao;

    @Autowired
    private AdminRoleMenuDao adminRoleMenuDao;

    @Override
    public List<AdminMenu> findAll() {

        List<Integer> menuIds = adminMenuDao.findAll().stream().map(AdminMenu::getId).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDao.findAllById(menuIds);
        handleMenus(menus);
        return menus;
    }

    @Override
    public List<AdminMenu> getMenusByCurrentUser() {
        // Get current user in DB.
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(username);

        // Get roles' ids of current user.
        List<Integer> rids = adminUserRoleDao.findAllByUid(user.getId()).stream().map(AdminUserRole::getRid)
                                             .collect(Collectors.toList());

        // Get menu items of these roles.
        List<Integer> menuIds =
                adminRoleMenuDao.findAllByRidIn(rids).stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDao.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        // Adjust the structure of the menu.
        handleMenus(menus);
        return menus;
    }

    @Override
    public List<AdminMenu> getMenusByRoleId(int rid) {
        List<Integer> menuIds =
                adminRoleMenuDao.findAllByRid(rid).stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDao.findAllById(menuIds);
        handleMenus(menus);
        return menus;
    }

    /**
     * Adjust the Structure of the menu.
     *
     * @param menus Menu items list without structure
     */
    private void handleMenus(List<AdminMenu> menus) {
        menus.forEach(m -> {
            List<AdminMenu> children = adminMenuDao.findAllByParentId(m.getId());
            m.setChildren(children);
        });
        menus.removeIf(m -> m.getParentId() != 0);
    }
}