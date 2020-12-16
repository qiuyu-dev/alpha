package com.mysoft.alpha.service.impl;

import com.mysoft.alpha.dao.WxUserDao;
import com.mysoft.alpha.entity.WxUser;
import com.mysoft.alpha.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * (WxUser)表服务实现类
 *
 * @author makejava
 * @since 2020-11-29 14:12:52
 */
@Service
public class WxUserServiceImpl implements WxUserService {
    /**
     * 服务对象
     */
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