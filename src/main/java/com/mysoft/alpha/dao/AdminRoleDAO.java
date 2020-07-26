package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRoleDAO extends JpaRepository<AdminRole, Integer> {
    AdminRole findById(int id);
    List<AdminRole> findByNameZhLikeOrderByIdDesc(String nameZh);

    List<AdminRole> findByNameZhLikeOrderByIdAsc(String nameZh);
}
