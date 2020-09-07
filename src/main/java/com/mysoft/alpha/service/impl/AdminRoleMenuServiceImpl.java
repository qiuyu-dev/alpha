package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AdminRoleMenuDao;
import com.mysoft.alpha.entity.AdminRoleMenu;
import com.mysoft.alpha.service.AdminRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色-菜单(AdminRoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:13:27
 */
@Service
public class AdminRoleMenuServiceImpl implements AdminRoleMenuService {
    /**
     * 服务对象
     */
    @Autowired
    private AdminRoleMenuDao adminRoleMenuDao;

    @Override
    public void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds) {
        adminRoleMenuDao.deleteAllByRid(rid);
        List<AdminRoleMenu> rms = new ArrayList<>();
        for (Integer mid : menusIds.get("menusIds")) {
            AdminRoleMenu rm = new AdminRoleMenu();
            rm.setMid(mid);
            rm.setRid(rid);
            rms.add(rm);
        }
        adminRoleMenuDao.saveAll(rms);
    }

}