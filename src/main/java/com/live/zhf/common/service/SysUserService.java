package com.live.zhf.common.service;

import com.github.pagehelper.PageInfo;
import com.live.zhf.base.BaseService;
import com.live.zhf.common.entity.SysPermission;
import com.live.zhf.common.entity.SysUser;
import com.live.zhf.utils.Result;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2020-05-14 20:39:41
 */
public interface SysUserService extends BaseService<SysUser> {

    Result<PageInfo> queryPage(String userName, String status,Integer currentPage, Integer pageSize, String order, Integer sortType);

    List<SysPermission> getUserPermission(Integer userId);

    String login(Authentication authentication);
}