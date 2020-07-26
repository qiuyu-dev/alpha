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
@Table(name = "admin_permission")
//@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminPermission  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Permission name;
     */
    private String name;

    /**
     * Permission's description(in Chinese)
     */
    private String desc_;

    /**
     * The path which triggers permission check.
     */
    private String url;

	public AdminPermission() {
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

	public String getDesc_() {
		return desc_;
	}

	public void setDesc_(String desc_) {
		this.desc_ = desc_;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("AdminPermission{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append(", desc_='").append(desc_).append('\'');
		sb.append(", url='").append(url).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
