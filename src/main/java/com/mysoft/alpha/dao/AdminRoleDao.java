package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mysoft.alpha.entity.AdminRole;

import java.util.List;

/**
 * 角色(AdminRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:13:21
 */
public interface AdminRoleDao extends JpaRepository<AdminRole, Integer> {


    List<AdminRole> findByNameZhLikeOrderByIdAsc(String s);
}