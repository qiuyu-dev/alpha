package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 菜单(AdminMenu)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:12:32
 */
public interface AdminMenuDao extends JpaRepository<AdminMenu, Integer> {


    List<AdminMenu> findAllByParentId(int parentId);
}