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
@Table(name = "admin_role_permission")
//@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminRolePermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Role id.
     */
    private int rid;

    /**
     * Permission id.
     */
    private int pid;

	public AdminRolePermission() {
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("AdminRolePermission{");
		sb.append("id=").append(id);
		sb.append(", rid=").append(rid);
		sb.append(", pid=").append(pid);
		sb.append('}');
		return sb.toString();
	}
}
