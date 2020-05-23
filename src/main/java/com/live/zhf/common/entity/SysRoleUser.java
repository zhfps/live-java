package com.live.zhf.common.entity;

import java.io.Serializable;

/**
 * (SysRoleUser)实体类
 *
 * @author makejava
 * @since 2020-05-18 22:57:29
 */
public class SysRoleUser implements Serializable {
    private static final long serialVersionUID = 514174253856956055L;
    
    private Integer userId;
    
    private Integer roleId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}