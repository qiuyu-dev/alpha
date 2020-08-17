package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mysoft.alpha.entity.AdminRoleMenu;

import java.util.List;

/**
 * 角色-菜单(AdminRoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:13:25
 */
public interface AdminRoleMenuDao extends JpaRepository<AdminRoleMenu, Integer> {


    List<AdminRoleMenu> findAllByRid(int rid);

    List<AdminRoleMenu> findAllByRidIn(List<Integer> rids);

    void deleteAllByRid(int rid);
}