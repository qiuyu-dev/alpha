package com.mysoft.alpha.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


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
@Table(name = "admin_role_menu")
//@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminRoleMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Role id.
     */
    private int rid;

    /**
     * Menu id.
     */
    private int mid;

	public AdminRoleMenu() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("AdminRoleMenu{");
		sb.append("id=").append(id);
		sb.append(", rid=").append(rid);
		sb.append(", mid=").append(mid);
		sb.append('}');
		return sb.toString();
	}
}
