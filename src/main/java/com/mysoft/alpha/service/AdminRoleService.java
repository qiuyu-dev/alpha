package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.AdminRole;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AdminRoleService {

    public List<AdminRole> listWithPermsAndMenus();

    public List<AdminRole> findAll();

    public void addOrUpdate(AdminRole adminRole);

    public List<AdminRole> listRolesByUser(String username);
    public List<AdminRole> listSubRolesByUser(String username);

    public AdminRole updateRoleStatus(AdminRole role);

    public void editRole(@RequestBody AdminRole role);
}
