package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.AdminPermission;

import java.util.List;
import java.util.Set;

/**
 * 许可(AdminPermission)表服务接口
 *
 * @author makejava
 * @since 2020-08-02 16:13:17
 */
public interface AdminPermissionService {

    Set<String> listPermissionURLsByUser(String username);

    boolean needFilter(String requestAPI);

    List<AdminPermission> listPermsByRoleId(Integer rid);

    List<AdminPermission>  list();
}