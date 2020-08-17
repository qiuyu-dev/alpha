package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mysoft.alpha.entity.AdminPermission;

/**
 * 许可(AdminPermission)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:13:15
 */
public interface AdminPermissionDao extends JpaRepository<AdminPermission, Integer> {


}