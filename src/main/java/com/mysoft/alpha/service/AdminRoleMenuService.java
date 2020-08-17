package com.mysoft.alpha.service;

import java.util.List;
import java.util.Map;

/**
 * 角色-菜单(AdminRoleMenu)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:13:26
 */
public interface AdminRoleMenuService {

//    List<AdminRoleMenu> findAllByRidIn(List<Integer> rids);

//    List<AdminRoleMenu> findAllByRid(int rid);

    void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds);
}