package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AdminUserRoleDao;
import com.mysoft.alpha.entity.AdminRole;
import com.mysoft.alpha.entity.AdminUserRole;
import com.mysoft.alpha.service.AdminUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户-角色(AdminUserRole)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:13:33
 */
@Service
public class AdminUserRoleServiceImpl implements AdminUserRoleService {
    /**
     * 服务对象
     */
    @Autowired
    private AdminUserRoleDao adminUserRoleDao;

    @Override
    @Transactional
    public void saveRoleChanges(Integer uid, List<AdminRole> roles) {
        adminUserRoleDao.deleteAllByUid(uid);
        List<AdminUserRole> urs = new ArrayList<>();
        roles.forEach(r -> {
            AdminUserRole ur = new AdminUserRole();
            ur.setUid(uid);
            ur.setRid(r.getId());
            urs.add(ur);
        });
        adminUserRoleDao.saveAll(urs);

    }
}