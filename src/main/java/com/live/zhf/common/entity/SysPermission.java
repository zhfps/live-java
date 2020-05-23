package com.live.zhf.common.entity;

import java.io.Serializable;

/**
 * (SysPermission)实体类
 *
 * @author makejava
 * @since 2020-05-18 23:06:28
 */
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 363788374207537821L;
    
    private Integer id;
    
    private Integer parentId;
    
    private String name;
    
    private String css;
    
    private String href;
    
    private Object type;
    
    private String permission;
    
    private Integer sort;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}