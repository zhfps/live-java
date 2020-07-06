package com.live.zhf.common.service;

import com.github.pagehelper.PageInfo;
import com.live.zhf.base.BaseService;
import com.live.zhf.common.entity.SysRole;
import com.live.zhf.utils.Result;

import java.util.List;

/**
 * (SysRole)表服务接口
 *
 * @author makejava
 * @since 2020-05-18 23:05:26
 */
public interface SysRoleService extends BaseService<SysRole> {
    Result<PageInfo> queryPage(String description, Integer currentPage, Integer pageSize, String order, Integer sortType);
}