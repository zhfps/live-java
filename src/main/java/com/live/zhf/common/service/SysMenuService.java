package com.live.zhf.common.service;

import com.github.pagehelper.PageInfo;
import com.live.zhf.common.entity.Menu;
import com.live.zhf.common.entity.Select;
import com.live.zhf.common.entity.SysMenu;
import com.live.zhf.exception.DeleteException;
import com.live.zhf.exception.InsertException;
import com.live.zhf.exception.UpdateException;
import com.live.zhf.utils.Result;

import java.util.List;

/**
 * (SysMenu)表服务接口
 *
 * @author makejava
 * @since 2020-05-15 20:37:20
 */
public interface SysMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Result<SysMenu> queryById(Integer id);

    /**
     * 查询全部
     * @return
     */
    Result<PageInfo> queryPage(Integer currentPage, Integer pageSize);

    /**
     * 查询全部
     * @return
     */
    Result<List<Menu>> queryAll(String name, String status);
    /**
     * 前端菜单目录下拉框
     */
    Result<List<Select<Integer,String>>> getSelect();
    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    Result<SysMenu> insert(SysMenu sysMenu) throws InsertException;

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    Result<SysMenu> update(SysMenu sysMenu) throws UpdateException;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result<Boolean> deleteById(Integer id) throws DeleteException;

}