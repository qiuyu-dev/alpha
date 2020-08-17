package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.AdminPermission;

import java.util.List;

/**
 * 角色-许可(AdminRolePermission)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:13:29
 */
public interface AdminRolePermissionService {

    void savePermChanges(Integer rid, List<AdminPermission> perms);

//    List<AdminRolePermission> findAllByRid(Integer rid);
}