package com.mysoft.alpha.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysoft.alpha.dao.AdminRolePermissionDAO;
import com.mysoft.alpha.entity.AdminPermission;
import com.mysoft.alpha.entity.AdminRolePermission;
import com.mysoft.alpha.service.AdminRolePermissionService;

@Service
public class AdminRolePermissionServiceImpl implements AdminRolePermissionService {
	@Autowired
	AdminRolePermissionDAO adminRolePermissionDAO;

	public List<AdminRolePermission> findAllByRid(int rid) {
		return adminRolePermissionDAO.findAllByRid(rid);
	}

	@Transactional
	public void savePermChanges(int rid, List<AdminPermission> perms) {
		adminRolePermissionDAO.deleteAllByRid(rid);
		List<AdminRolePermission> rps = new ArrayList<>();
		perms.forEach(p -> {
			AdminRolePermission rp = new AdminRolePermission();
			rp.setRid(rid);
			rp.setPid(p.getId());
			rps.add(rp);
		});
		adminRolePermissionDAO.saveAll(rps);
	}
}
