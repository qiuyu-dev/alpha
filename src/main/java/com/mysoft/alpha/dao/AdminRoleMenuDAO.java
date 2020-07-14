package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysoft.alpha.entity.AdminRoleMenu;

import java.util.List;

public interface AdminRoleMenuDAO extends JpaRepository<AdminRoleMenu,Integer> {
    List<AdminRoleMenu> findAllByRid(int rid);
    List<AdminRoleMenu> findAllByRidIn(List<Integer> rids);
    void deleteAllByRid(int rid);
}
