package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.WxUser;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * (WxUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-29 14:12:50
 */
public interface WxUserDao extends JpaRepository<WxUser, Integer> {

    WxUser findByOpenid(String openid);


}