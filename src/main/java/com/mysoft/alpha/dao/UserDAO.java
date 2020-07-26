package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.Company;
import com.mysoft.alpha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);

    List<User> findByCompanyOrderByIdDesc(Company company);
   
}
