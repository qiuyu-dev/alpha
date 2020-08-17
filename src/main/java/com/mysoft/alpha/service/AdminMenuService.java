package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.AdminMenu;

import java.util.List;

/**
 * 菜单(AdminMenu)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:12:34
 */
public interface AdminMenuService {

    List<AdminMenu> getMenusByCurrentUser();

    List<AdminMenu> getMenusByRoleId(int rid);

    List<AdminMenu> findAll();


}