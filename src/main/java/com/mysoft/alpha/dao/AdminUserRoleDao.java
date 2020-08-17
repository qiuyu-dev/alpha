package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mysoft.alpha.entity.AdminUserRole;

import java.util.List;

/**
 * 用户-角色(AdminUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:13:31
 */
public interface AdminUserRoleDao extends JpaRepository<AdminUserRole, Integer> {

    List<AdminUserRole> findAllByUid(int uid);

    void deleteAllByUid(int uid);
}