package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.AdminRole;

import java.util.List;

/**
 * 角色(AdminRole)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:13:22
 */
public interface AdminRoleService {

    List<AdminRole> listRolesByUser(String username);

    List<AdminRole> listWithPermsAndMenus();

    List<AdminRole> listSubRolesByUser(String username);

    AdminRole updateRoleStatus(AdminRole role);

    void addOrUpdate(AdminRole role);

    void editRole(AdminRole role);
}