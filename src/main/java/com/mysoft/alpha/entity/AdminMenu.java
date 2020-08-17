package com.mysoft.alpha.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单(AdminMenu)实体类
 *
 * @author makejava
 * @since 2020-08-02 16:12:29
 */
@Entity
@Table(name = "admin_menu")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminMenu implements Serializable {
    private static final long serialVersionUID = -59853103132998345L;
    /**
     * 菜单主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 菜单名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 菜单名称-中文
     */
    @Column(name = "name_zh")
    private String nameZh;
    /**
     * 菜单显示路径，唯一
     */
    @Column(name = "path")
    private String path;
    /**
     * 图标
     */
    @Column(name = "icon_cls")
    private String iconCls;
    /**
     * 组件
     */
    @Column(name = "component")
    private String component;
    /**
     * 父ID
     */
    @Column(name = "parent_id")
    private Integer parentId;
    /**
     * 操作员
     */
    @Column(name = "operator")
    private String operator;
    /**
     * 创建时间
     */
    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * Transient property for storing children menus.
     */
    @Transient
    private List<AdminMenu> children;

    public AdminMenu() {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        sb.append(", name='").append(name).append('\'');
        sb.append(", nameZh='").append(nameZh).append('\'');
        sb.append(", path='").append(path).append('\'');
        sb.append(", iconCls='").append(iconCls).append('\'');
        sb.append(", component='").append(component).append('\'');
        sb.append(", parentId=").append(parentId);
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", children=").append(children);
        sb.append('}');
        return sb.toString();
    }
}