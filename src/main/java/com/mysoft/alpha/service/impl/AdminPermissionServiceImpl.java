package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AdminPermissionDao;
import com.mysoft.alpha.dao.AdminRolePermissionDao;
import com.mysoft.alpha.entity.AdminPermission;
import com.mysoft.alpha.entity.AdminRole;
import com.mysoft.alpha.entity.AdminRolePermission;
import com.mysoft.alpha.service.AdminPermissionService;
import com.mysoft.alpha.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 许可(AdminPermission)表服务实现类
 *
 * @author makejava
 * @since 2020-08-02 16:13:18
 */
@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {
    /**
     * 服务对象
     */
    @Autowired
    private AdminPermissionDao adminPermissionDao;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminRolePermissionDao adminRolePermissionDao;

    @Override
    public Set<String> listPermissionURLsByUser(String username) {
        List<Integer> rids =
                adminRoleService.listRolesByUser(username).stream().map(AdminRole::getId).collect(Collectors.toList());

        List<Integer> pids = adminRolePermissionDao.findAllByRidIn(rids).stream().map(AdminRolePermission::getPid)
                                                   .collect(Collectors.toList());

        List<AdminPermission> perms = adminPermissionDao.findAllById(pids);

        Set<String> URLs = perms.stream().map(AdminPermission::getUrl).collect(Collectors.toSet());

        return URLs;
    }

    /**
     * Determine whether client requires permission when requests a certain API.
     *
     * @param requestAPI API requested by client
     * @return true when requestAPI is found in the DB
     */
    @Override
    public boolean needFilter(String requestAPI) {
        List<AdminPermission> ps = adminPermissionDao.findAll();
        for (AdminPermission p : ps) {
            // match prefix
            if (requestAPI.startsWith(p.getUrl())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<AdminPermission> listPermsByRoleId(Integer rid) {
        List<Integer> pids = adminRolePermissionDao.findAllByRid(rid).stream().map(AdminRolePermission::getPid)
                                                   .collect(Collectors.toList());
        return adminPermissionDao.findAllById(pids);
    }

    @Override
    public List<AdminPermission> list() {
        return adminPermissionDao.findAll();
    }
}