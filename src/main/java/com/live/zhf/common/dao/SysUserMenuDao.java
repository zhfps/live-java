package com.live.zhf.common.dao;

import com.live.zhf.common.entity.menu.SysMenu;
import com.live.zhf.common.entity.SysUserMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (SysUserMenu)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-20 21:21:52
 */
@Mapper
public interface SysUserMenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    List<SysMenu> queryById(Integer userId);

    List<SysUserMenu> getUserMenuId(Integer userId);
    /**
     * 新增数据
     *
     * @param sysUserMenu 实例对象
     * @return 影响行数
     */
    int insert(SysUserMenu sysUserMenu);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

}