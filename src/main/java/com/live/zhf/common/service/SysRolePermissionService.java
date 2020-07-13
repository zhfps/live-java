package com.live.zhf.common.service;

import com.live.zhf.common.entity.SysRolePermission;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * (SysRolePermission)表服务接口
 *
 * @author makejava
 * @since 2020-07-13 23:43:16
 */
public interface SysRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleid 主键
     * @return 实例对象
     */
    List<SysRolePermission> queryById(Integer roleId);


    /**
     * 新增数据
     *
     * @param sysRolePermission 实例对象
     * @return 实例对象
     */
    boolean insert(SysRolePermission sysRolePermission);


    boolean deleteById(Integer roleId);

}