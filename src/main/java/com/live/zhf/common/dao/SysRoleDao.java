package com.live.zhf.common.dao;

import com.live.zhf.base.BaseDao;
import com.live.zhf.common.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-18 23:05:26
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRole> {
    List<SysRole> queryPage(@Param("description") String description);
    List<SysRole> queryAll();
}