package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.entity.AdminPermission;
import com.mysoft.alpha.entity.AdminRolePermission;

public interface AdminRolePermissionService {

	public List<AdminRolePermission> findAllByRid(int rid);

	public void savePermChanges(int rid, List<AdminPermission> perms);
}
