package com.live.zhf.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserMenu implements Serializable {
    private static final long serialVersionUID = 837339155931351119L;
    
    private Integer menuId;
    
    private Integer userId;

}