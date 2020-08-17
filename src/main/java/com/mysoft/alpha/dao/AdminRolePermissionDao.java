package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.AdminRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 角色-许可(AdminRolePermission)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:13:29
 */
public interface AdminRolePermissionDao extends JpaRepository<AdminRolePermission, Integer> {


    List<AdminRolePermission> findAllByRidIn(List<Integer> rids);

    void deleteAllByRid(Integer rid);

    List<AdminRolePermission> findAllByRid(Integer rid);
}