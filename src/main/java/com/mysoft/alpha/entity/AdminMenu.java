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
@Entity
@Table(name = "admin_menu")
//@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminMenu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Menu access path.
     */
    private String path;

    /**
     * Menu name.
     */
    private String name;

    /**
     * Menu name in Chinese.
     */
    private String nameZh;

    /**
     * Menu icon class(use element-ui icons).
     */
    private String iconCls;

    /**
     * Front-end component name corresponding to menu.
     */
    private String component;

    /**
     * Parent menu.
     */
    private int parentId;

    /**
     * Transient property for storing children menus.
     */
    @Transient
    private List<AdminMenu> children;

	public AdminMenu() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<AdminMenu> getChildren() {
		return children;
	}

	public void setChildren(List<AdminMenu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("AdminMenu{");
		sb.append("id=").append(id);
		sb.append(", path='").append(path).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append(", nameZh='").append(nameZh).append('\'');
		sb.append(", iconCls='").append(iconCls).append('\'');
		sb.append(", component='").append(component).append('\'');
		sb.append(", parentId=").append(parentId);
		sb.append(", children=").append(children);
		sb.append('}');
		return sb.toString();
	}
}
