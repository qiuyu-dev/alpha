package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.AdminRole;

import java.util.List;

/**
 * 用户-角色(AdminUserRole)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:13:32
 */
public interface AdminUserRoleService {

    void saveRoleChanges(Integer rid, List<AdminRole> roles);
}