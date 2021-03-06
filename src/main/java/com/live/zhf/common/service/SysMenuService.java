package com.live.zhf.common.service;

import com.github.pagehelper.PageInfo;
import com.live.zhf.base.BaseService;
import com.live.zhf.common.entity.menu.Menu;
import com.live.zhf.common.entity.menu.Select;
import com.live.zhf.common.entity.menu.SysMenu;
import com.live.zhf.utils.Result;

import java.util.List;

/**
 * (SysMenu)表服务接口
 *
 * @author makejava
 * @since 2020-05-15 20:37:20
 */
public interface SysMenuService extends BaseService<SysMenu> {

    Result<PageInfo> queryPage(Integer currentPage, Integer pageSize, String order, Integer sortType);
    /**
     * 查询全部
     * @return
     */
    Result<List<Menu>> queryAll(String name, String status);
    /**
     * 前端菜单目录下拉框
     */
    Result<List<Select<Integer,String>>> getSelect(String type);


}