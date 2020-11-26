package com.mysoft.alpha.service;

import com.mysoft.alpha.entity.WxUser;

public interface WxUserService {
	WxUser findByOpenid(String openid);
	
	WxUser createWxUser(WxUser wxUser);
	
	WxUser updateWxUser(WxUser wxUser);
}
