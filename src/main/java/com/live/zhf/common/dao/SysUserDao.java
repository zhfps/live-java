package com.live.zhf.common.dao;

import com.live.zhf.base.BaseDao;
import com.live.zhf.common.entity.SysPermission;
import com.live.zhf.common.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Set;

/**
 * (SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-14 20:39:40
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUser> {
    /**
     * 获取权限列表
     * @param userId
     * @return
     */
    List<SysPermission> getUserPermission(Integer userId);

    /**
     * 根据名称查询用户
     * @param userName
     * @return
     */
    SysUser getUserByName(String userName);

    Set<String> getLoginUserPermission(Integer userId);
}