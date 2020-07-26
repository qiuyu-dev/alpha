package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.AdminRolePermissionDAO;
import com.mysoft.alpha.entity.AdminPermission;
import com.mysoft.alpha.entity.AdminRolePermission;
import com.mysoft.alpha.service.AdminRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRolePermissionServiceImpl implements AdminRolePermissionService {
	@Autowired
	AdminRolePermissionDAO adminRolePermissionDAO;

	public List<AdminRolePermission> findAllByRid(int rid) {
		return adminRolePermissionDAO.findAllByRid(rid);
	}

	@Transactional
	public void savePermChanges(int rid, List<AdminPermission> perms) {
		System.out.println("--------------------adminRolePermissionDAO.deleteAllByRid(rid);     begin----------------");
		adminRolePermissionDAO.deleteAllByRid(rid);
		System.out.println("--------------------adminRolePermissionDAO.deleteAllByRid(rid);     end----------------");

		List<AdminRolePermission> rps = new ArrayList<>();
		perms.forEach(p -> {
			AdminRolePermission rp = new AdminRolePermission();
			rp.setRid(rid);
			rp.setPid(p.getId());
			rps.add(rp);
		});
		System.out.println("--------------------adminRolePermissionDAO.saveAll(rps);     begin----------------");

		adminRolePermissionDAO.saveAll(rps);
		System.out.println("--------------------adminRolePermissionDAO.saveAll(rps);     end----------------");

	}
}
