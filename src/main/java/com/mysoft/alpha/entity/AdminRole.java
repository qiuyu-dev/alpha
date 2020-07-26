package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



/**
 *
 *  entity
 *  class 数据对象类
 *  Serializable 序列化用于网络传输
 *  创建空构造函数
 *  get，set
 *  tostring
 */

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Entity
@Table(name = "admin_role")
//@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminRole   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Role name.
     */
    @Column(name = "name")
    private String name;

    /**
     * Role name in Chinese.
     */
    @Column(name = "name_zh")
    private String nameZh;

    /**
     * Role status.
     */
    @Column(name = "enabled")
    private int enabled;


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

	public AdminRole(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
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
        sb.append(", perms=").append(perms);
        sb.append(", menus=").append(menus);
        sb.append('}');
        return sb.toString();
    }
}
