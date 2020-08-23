package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mysoft.alpha.entity.User;

import java.util.List;

/**
 * 用户(User)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-02 16:14:13
 */
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    
    User findByUsernameAndEmail(String username, String email);

    List<User> findByAlphaSubjectIdOrderByIdDesc(Integer alphasubjectid);

}