package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.WxUser;

/**
 * (WxUser)表服务接口
 *
 * @author makejava
 * @since 2020-11-29 14:12:51
 */
public interface WxUserService {


    WxUser findByOpenid(String openid);

  WxUser createWxUser(WxUser wxUser);

    WxUser updateWxUser(WxUser wxUser);

}