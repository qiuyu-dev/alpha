package com.mysoft.alpha.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 微信配置类
 */
@Configuration
@PropertySource(value="classpath:application.properties")
public class WeChatConfig {
    /**
     * 小程序appid
     */
    @Value("${wxminiprogram.appid}")
    private String appId;

    /**
     * 小程序秘钥
     */
    @Value("${wxminiprogram.appsecret}")
    private String appsecret;
    /*
     * UnionID 机制说明
     *如果开发者拥有多个移动应用、网站应用、和公众帐号（包括小程序），可通过 UnionID 来区分用户的唯一性，
     *因为只要是同一个微信开放平台帐号下的移动应用、网站应用和公众帐号（包括小程序），用户的 UnionID 是唯一的。
     *换句话说，同一用户，对同一个微信开放平台下的不同应用，UnionID是相同的。
     *用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回
     */
    //获取openid,sessionkey,unionid
    private final static String AUTH_JSCODE2SESSION="https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
    //获取小程序全局唯一后台接口调用凭据（access_token）
    private final static String AUTH_GET_ACCESS_TOKEN="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public static String getAuthJscode2session() {
		return AUTH_JSCODE2SESSION;
	}

	public static String getAuthGetAccessToken() {
		return AUTH_GET_ACCESS_TOKEN;
	}     
    
}
