package com.live.zhf.common.service;

import com.live.zhf.common.entity.SysRoleUser;
import com.live.zhf.utils.Result;

import java.util.List;

/**
 * (SysRoleUser)表服务接口
 *
 * @author makejava
 * @since 2020-07-16 22:43:48
 */
public interface SysRoleUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    Result<List<SysRoleUser>> queryById(Integer userId);

    /**
     * 新增数据
     *
     * @param sysRoleUser 实例对象
     * @return 实例对象
     */
    Result<Boolean> insert(SysRoleUser sysRoleUser);


    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    Result<Boolean> deleteById(Integer userId);

}