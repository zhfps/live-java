package com.live.zhf.common.entity.dto;

import com.live.zhf.common.entity.SysPermission;

import java.io.Serializable;
import java.util.List;

public class PermissionTree implements Serializable {
    private static final long serialVersionUID = 363788274207537821L;

    private Integer id;

    private Integer parentId;

    private String name;

    private String css;

    private String href;

    private Object type;

    private String permission;

    private Integer sort;

    private List<PermissionTree> children;

    public PermissionTree() {
    }

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

    public List<PermissionTree> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionTree> children) {
        this.children = children;
    }
}