package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.AdminMenu;

import java.util.List;


public interface AdminMenuService {

    public List<AdminMenu> findAll();

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
