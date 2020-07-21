package com.live.zhf.common.service;

import com.github.pagehelper.PageInfo;
import com.live.zhf.base.BaseService;
import com.live.zhf.common.entity.menu.Select;
import com.live.zhf.common.entity.SysPermission;
import com.live.zhf.common.entity.dto.PermissionTree;
import com.live.zhf.utils.Result;

import java.util.List;

/**
 * (SysPermission)表服务接口
 *
 * @author makejava
 * @since 2020-05-18 23:06:28
 */
public interface SysPermissionService extends BaseService<SysPermission> {
    Result<PageInfo> queryPage(Integer currentPage, Integer pageSize,String order,Integer sortType);

    Result<List<PermissionTree>> getTree(String name);

    Result<List<Select<Integer,String>>> getSelect(Integer type);

}