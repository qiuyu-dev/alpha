package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AdminRolePermissionDao;
import com.mysoft.alpha.entity.AdminPermission;
import com.mysoft.alpha.entity.AdminRolePermission;
import com.mysoft.alpha.service.AdminRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色-许可(AdminRolePermission)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:13:29
 */
@Service
public class AdminRolePermissionServiceImpl implements AdminRolePermissionService {
    /**
     * 服务对象
     */
    @Autowired
    private AdminRolePermissionDao adminRolePermissionDao;

    @Override
    public void savePermChanges(Integer rid, List<AdminPermission> perms) {
        System.out.println("--------------------adminRolePermissionDAO.deleteAllByRid(rid);     begin----------------");
        adminRolePermissionDao.deleteAllByRid(rid);
        System.out.println("--------------------adminRolePermissionDAO.deleteAllByRid(rid);     end----------------");

        List<AdminRolePermission> rps = new ArrayList<>();
        perms.forEach(p -> {
            AdminRolePermission rp = new AdminRolePermission();
            rp.setRid(rid);
            rp.setPid(p.getId());
            rps.add(rp);
        });
        System.out.println("--------------------adminRolePermissionDAO.saveAll(rps);     begin----------------");

        adminRolePermissionDao.saveAll(rps);
        System.out.println("--------------------adminRolePermissionDAO.saveAll(rps);     end----------------");


    }

//    @Override
//    public List<AdminRolePermission> findAllByRid(Integer rid) {
//        return adminRolePermissionDao.findAllByRid(rid);
//    }
}