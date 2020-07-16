package com.mysoft.alpha.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.mysoft.alpha.dao.AdminRoleDAO;
import com.mysoft.alpha.entity.AdminMenu;
import com.mysoft.alpha.entity.AdminPermission;
import com.mysoft.alpha.entity.AdminRole;
import com.mysoft.alpha.entity.AdminUserRole;
import com.mysoft.alpha.service.AdminMenuService;
import com.mysoft.alpha.service.AdminPermissionService;
import com.mysoft.alpha.service.AdminRolePermissionService;
import com.mysoft.alpha.service.AdminRoleService;
import com.mysoft.alpha.service.AdminUserRoleService;
import com.mysoft.alpha.service.UserService;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {
	@Autowired
	AdminRoleDAO adminRoleDAO;

	@Autowired
	UserService userService;

	@Autowired
	AdminUserRoleService adminUserRoleService;

	@Autowired
	AdminPermissionService adminPermissionService;

	@Autowired
	AdminRolePermissionService adminRolePermissionService;

	@Autowired
	AdminMenuService adminMenuService;

	public List<AdminRole> listWithPermsAndMenus() {
		List<AdminRole> roles = adminRoleDAO.findAll();
		List<AdminPermission> perms;
		List<AdminMenu> menus;
		for (AdminRole role : roles) {
			perms = adminPermissionService.listPermsByRoleId(role.getId());
			menus = adminMenuService.getMenusByRoleId(role.getId());
			role.setPerms(perms);
			role.setMenus(menus);
		}
		return roles;
	}

	public List<AdminRole> findAll() {
		return adminRoleDAO.findAll();
	}

	public void addOrUpdate(AdminRole adminRole) {
		adminRoleDAO.save(adminRole);
	}

	public List<AdminRole> listRolesByUser(String username) {
		int uid = userService.findByUsername(username).getId();
		List<Integer> rids = adminUserRoleService.listAllByUid(uid).stream().map(AdminUserRole::getRid)
				.collect(Collectors.toList());
		return adminRoleDAO.findAllById(rids);
	}

	public AdminRole updateRoleStatus(AdminRole role) {
		AdminRole roleInDB = adminRoleDAO.findById(role.getId());
		roleInDB.setEnabled(role.isEnabled());
		return adminRoleDAO.save(roleInDB);
	}

	@Transactional
	public void editRole(@RequestBody AdminRole role) {
		adminRoleDAO.save(role);
		adminRolePermissionService.savePermChanges(role.getId(), role.getPerms());
	}
}
