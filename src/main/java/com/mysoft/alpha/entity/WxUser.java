package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * (WxUser)实体类
 *
 * @author makejava
 * @since 2020-11-29 14:12:49
 */
@Entity
@Table(name = "wx_user")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class WxUser implements Serializable {
    private static final long serialVersionUID = 702565972614096704L;
    /**
     * 微信用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 微信openid
     */
    @Column(name = "openid")
    private String openid;
    /**
     * 微信unionid
     */
    @Column(name = "unionid")
    private String unionid;
    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;
    /**
     * 头像
     */
    @Column(name = "avatar_url")
    private String avatarUrl;
    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;
    /**
     * 用户性别 0未知,1男,2女
     */
    @Column(name = "gender")
    private Integer gender;
    /**
     * 用户所在国家
     */
    @Column(name = "country")
    private String country;
    /**
     * 用户所在省份
     */
    @Column(name = "province")
    private String province;
    /**
     * 用户所在城市
     */
    @Column(name = "city")
    private String city;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 用户表id
     */
    @Column(name = "userid")
    private Integer userid;
    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;
    /**
     * 显示 country，province，city 所用的语言
     */
    @Column(name = "language")
    private String language;


    /**
     * Transient property for storing role owned by current user.
     */
    @Transient
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public WxUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}