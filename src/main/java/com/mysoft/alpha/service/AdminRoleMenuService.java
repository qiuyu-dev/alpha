package com.mysoft.alpha.service;

import java.util.List;
import java.util.Map;

import com.mysoft.alpha.entity.AdminRoleMenu;

public interface AdminRoleMenuService {

    public List<AdminRoleMenu> findAllByRid(int rid);

    public List<AdminRoleMenu> findAllByRidIn(List<Integer> rids);

    public void save(AdminRoleMenu adminRoleMenu);

    public void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds);
}
