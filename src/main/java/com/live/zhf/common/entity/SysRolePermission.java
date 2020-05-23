package com.live.zhf.common.entity;

import java.io.Serializable;

/**
 * (SysRolePermission)实体类
 *
 * @author makejava
 * @since 2020-05-18 23:04:56
 */
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 903742465778278564L;
    
    private Integer roleId;
    
    private Integer permissionId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleid) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

}