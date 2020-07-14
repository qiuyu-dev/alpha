package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysoft.alpha.entity.AdminMenu;

import java.util.List;

public interface AdminMenuDAO extends JpaRepository<AdminMenu, Integer> {
     AdminMenu findById(int id);
     List<AdminMenu> findAllByParentId(int parentId);
}

