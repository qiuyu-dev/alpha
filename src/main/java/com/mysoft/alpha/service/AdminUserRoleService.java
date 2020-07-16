package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.entity.AdminRole;
import com.mysoft.alpha.entity.AdminUserRole;

public interface AdminUserRoleService {

    public List<AdminUserRole> listAllByUid(int uid);

    public void saveRoleChanges(int uid, List<AdminRole> roles);
}
