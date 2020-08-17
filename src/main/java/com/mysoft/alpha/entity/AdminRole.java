package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 角色(AdminRole)实体类
 *
 * @author makejava
 * @since 2020-08-02 16:13:20
 */
@Entity
@Table(name = "admin_role")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminRole implements Serializable {
    private static final long serialVersionUID = -79543607633194140L;
    /**
     * 角色主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 角色名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 角色名称-中文，不可修改
     */
    @Column(name = "name_zh")
    private String nameZh;
    /**
     * 是否可用，0不可用，1可用
     */
    @Column(name = "enabled")
    private Integer enabled;
    /**
     * 操作员
     */
    @Column(name = "operator")
    private String operator;
    /**
     * Transient property for storing permissions owned by current role.
     */
    @Transient
    private List<AdminPermission> perms;

    /**
     * Transient property for storing menus owned by current role.
     */
    @Transient
    private List<AdminMenu> menus;

    public AdminRole() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<AdminPermission> getPerms() {
        return perms;
    }

    public void setPerms(List<AdminPermission> perms) {
        this.perms = perms;
    }

    public List<AdminMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<AdminMenu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdminRole{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", nameZh='").append(nameZh).append('\'');
        sb.append(", enabled=").append(enabled);
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", perms=").append(perms);
        sb.append(", menus=").append(menus);
        sb.append('}');
        return sb.toString();
    }
}