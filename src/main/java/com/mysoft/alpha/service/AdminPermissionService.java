package com.mysoft.alpha.service;

import java.util.List;
import java.util.Set;

import com.mysoft.alpha.entity.AdminPermission;

public interface AdminPermissionService {

	public List<AdminPermission> list();

	/**
	 * Determine whether client requires permission when requests a certain API.
	 * 
	 * @param requestAPI API requested by client
	 * @return true when requestAPI is found in the DB
	 */
	public boolean needFilter(String requestAPI);

	public List<AdminPermission> listPermsByRoleId(int rid);

	public Set<String> listPermissionURLsByUser(String username);
}
