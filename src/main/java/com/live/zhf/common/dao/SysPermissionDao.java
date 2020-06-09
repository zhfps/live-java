package com.live.zhf.common.dao;

import com.live.zhf.base.BaseDao;
import com.live.zhf.common.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysPermission)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-18 23:06:28
 */
@Mapper
public interface SysPermissionDao extends BaseDao<SysPermission> {
}