package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AdminMenuDAO;
import com.mysoft.alpha.entity.AdminMenu;
import com.mysoft.alpha.entity.AdminRoleMenu;
import com.mysoft.alpha.entity.AdminUserRole;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.service.AdminMenuService;
import com.mysoft.alpha.service.AdminRoleMenuService;
import com.mysoft.alpha.service.AdminUserRoleService;
import com.mysoft.alpha.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuServiceImpl implements AdminMenuService {
	@Autowired
	AdminMenuDAO adminMenuDAO;

	@Autowired
	UserService userService;

	@Autowired
	AdminUserRoleService adminUserRoleService;

	@Autowired
	AdminRoleMenuService adminRoleMenuService;

	public List<AdminMenu> findAll(){

		List<Integer> menuIds = adminMenuDAO.findAll().stream().map(AdminMenu::getId)
													.collect(Collectors.toList());
		List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds);
		handleMenus(menus);
		return menus;
//		return adminMenuDAO.findAll();
			}

	public List<AdminMenu> getAllByParentId(int parentId) {
		return adminMenuDAO.findAllByParentId(parentId);
	}

	public List<AdminMenu> getMenusByCurrentUser() {
		// Get current user in DB.
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		User user = userService.findByUsername(username);

		// Get roles' ids of current user.
		List<Integer> rids = adminUserRoleService.listAllByUid(user.getId()).stream().map(AdminUserRole::getRid)
				.collect(Collectors.toList());

		// Get menu items of these roles.
		List<Integer> menuIds = adminRoleMenuService.findAllByRidIn(rids).stream().map(AdminRoleMenu::getMid)
				.collect(Collectors.toList());
		List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

		// Adjust the structure of the menu.
		handleMenus(menus);
		return menus;
	}

	public List<AdminMenu> getMenusByRoleId(int rid) {
		List<Integer> menuIds = adminRoleMenuService.findAllByRid(rid).stream().map(AdminRoleMenu::getMid)
				.collect(Collectors.toList());
		List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds);
		handleMenus(menus);
		return menus;
	}

	/**
	 * Adjust the Structure of the menu.
	 *
	 * @param menus Menu items list without structure
	 */
	public void handleMenus(List<AdminMenu> menus) {
		menus.forEach(m -> {
			List<AdminMenu> children = getAllByParentId(m.getId());
			m.setChildren(children);
		});
		menus.removeIf(m -> m.getParentId() != 0);
	}
}
