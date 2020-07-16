package com.mysoft.alpha.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mysoft.alpha.entity.AdminMenu;

@Service
public interface AdminMenuService {

    public List<AdminMenu> getAllByParentId(int parentId);

    public List<AdminMenu> getMenusByCurrentUser();

    public List<AdminMenu> getMenusByRoleId(int rid);

    /**
     * Adjust the Structure of the menu.
     *
     * @param menus Menu items list without structure
     */
    public void handleMenus(List<AdminMenu> menus);
}
