package com.live.zhf.common.service;

import com.live.zhf.common.entity.menu.Menu;
import com.live.zhf.common.entity.SysUserMenu;
import com.live.zhf.utils.Result;

import java.util.List;

/**
 * (SysUserMenu)表服务接口
 *
 * @author makejava
 * @since 2020-07-20 21:21:52
 */
public interface SysUserMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    Result<List<Menu>> queryById(Integer userId);

    List<Menu> getUserMenu(Integer userId);

    Result<List<SysUserMenu>> getUserMenuId(Integer userId);

    /**
     * 新增数据
     *
     * @param sysUserMenu 实例对象
     * @return 实例对象
     */
    Result<Boolean> insert(SysUserMenu sysUserMenu);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    Result<Boolean> deleteById(Integer userId);

}