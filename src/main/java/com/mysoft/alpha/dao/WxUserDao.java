package com.mysoft.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysoft.alpha.entity.WxUser;

public interface WxUserDao extends JpaRepository<WxUser, Integer>{
	WxUser findByOpenid(String openid);

}
