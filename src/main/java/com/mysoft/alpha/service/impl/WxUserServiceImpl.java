package com.mysoft.alpha.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysoft.alpha.dao.WxUserDao;
import com.mysoft.alpha.entity.WxUser;
import com.mysoft.alpha.service.WxUserService;

@Service
public class WxUserServiceImpl implements WxUserService{
	@Autowired
	private WxUserDao wxUserDao;
	
	@Override
	public WxUser findByOpenid(String openid) {
		return wxUserDao.findByOpenid(openid);
	}

	@Override
	public WxUser createWxUser(WxUser wxUser) {
		return wxUserDao.save(wxUser);
	}

	@Override
	public WxUser updateWxUser(WxUser wxUser) {
		return wxUserDao.saveAndFlush(wxUser);
	}

}
