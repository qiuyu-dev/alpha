package com.mysoft.alpha.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.mysoft.alpha.entity.AdminRole;

public interface AdminRoleService {

    public List<AdminRole> listWithPermsAndMenus();

    public List<AdminRole> findAll();

    public void addOrUpdate(AdminRole adminRole);

    public List<AdminRole> listRolesByUser(String username);

    public AdminRole updateRoleStatus(AdminRole role);

    public void editRole(@RequestBody AdminRole role);
}
