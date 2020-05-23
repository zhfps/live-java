package com.live.zhf.common.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysRole)实体类
 *
 * @author makejava
 * @since 2020-05-18 23:05:26
 */
public class SysRole implements Serializable {
    private static final long serialVersionUID = -89494829214238872L;
    
    private Integer id;
    
    private String name;
    
    private String description;
    
    private Date createTime;
    
    private Date updateTime;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return createTime;
    }

    public void setUpdateTime(Date createTime) {
        this.createTime = createTime;
    }

}