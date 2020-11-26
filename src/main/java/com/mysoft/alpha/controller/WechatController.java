package com.mysoft.alpha.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysoft.alpha.config.WeChatConfig;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.entity.WxUser;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.UserService;
import com.mysoft.alpha.service.WxUserService;
import com.mysoft.alpha.util.HttpUtils;
import com.mysoft.alpha.util.Md5Util;

@RestController
@RequestMapping("/api/v1/wechat")
public class WechatController {
    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserService userService;
    
    @Autowired
    private WxUserService wxUserService;
    
	@GetMapping("/checkUser")
	public Result checkUser(@RequestParam Map<String, String> map, HttpServletRequest request) throws Exception {
		String name = map.get("name");
		String phone = map.get("phone");
		String openid = map.get("openid");
		String nickName = map.get("nickName");
		String avatarUrl = map.get("avatarUrl");
		String gender = map.get("gender");
		String country = map.get("country");
		String province = map.get("province");
		String city = map.get("city");
		String language = map.get("language");
		User user = userService.findByNameAndPhone(name, phone);
		WxUser wxUser = wxUserService.findByOpenid(Md5Util.convertMD5(openid));
		if(wxUser != null) {
			wxUser.setPhone(phone);
			wxUser.setName(name);
			wxUser.setNickName(nickName);
			wxUser.setAvatarUrl(avatarUrl);
			wxUser.setGender(Integer.parseInt(gender));
			wxUser.setCountry(country);
			wxUser.setProvince(province);
			wxUser.setCity(city);
			wxUser.setLanguage(language);
			if(user == null) {
				wxUserService.updateWxUser(wxUser);
				return ResultFactory.buildFailResult("用户不存在");
			}else {
				wxUser.setUserid(user.getId());
				wxUserService.updateWxUser(wxUser);
			}
		}
		return ResultFactory.buildSuccessResult("success");
	}    
	
    @GetMapping("/wxlogin")
    public Result wxlogin(@RequestParam(value = "code",required = true)String code, HttpServletResponse response) throws Exception {
    	System.out.println("code="+code);
    	String accessTokenUrl = String.format(WeChatConfig.getAuthJscode2session(),weChatConfig.getAppId(),weChatConfig.getAppsecret(),code);
        //获取access_token
        Map<String ,Object> baseMap =  HttpUtils.doGet(accessTokenUrl);
        System.out.println("baseMap="+baseMap);
        String openid = (String)baseMap.get("openid");
        WxUser wxUser = wxUserService.findByOpenid(openid);
        if(wxUser == null) {
        	WxUser wxUserEntity = new WxUser();
        	wxUserEntity.setOpenid(openid);
        	wxUserEntity.setCreateTime(new Date());
        	wxUserService.createWxUser(wxUserEntity);
        }
    	return ResultFactory.buildSuccessResult(Md5Util.convertMD5(openid));
    }
    
}
