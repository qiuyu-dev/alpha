package com.mysoft.alpha.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "remote_opt_log")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class RemoteOptLog implements Serializable {
	private static final long serialVersionUID = 5393382629917837125L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	/**
	 * 账户名，验证，唯一
	 */
	@Column(name = "username")
	private String username;

	/**
	 * 操作 上传 1 或下载 2 
	 */
	@Column(name = "operate")
	private String operate;
	/**
	 * ip
	 */
	@Column(name = "ip")
	private String ip;
	/**
	 * 访问url
	 */
	@Column(name = "url")
	private String url;
	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "RemoteOptLog [id=" + id + ", username=" + username + ", operate=" + operate + ", ip=" + ip + ", url="
				+ url + ", remark=" + remark + ", createTime=" + createTime + "]";
	}


}
