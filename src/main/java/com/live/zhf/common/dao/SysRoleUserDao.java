package com.live.zhf.common.dao;

import com.live.zhf.common.entity.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysRoleUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-16 22:43:48
 */
@Mapper
public interface SysRoleUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    List<SysRoleUser> queryById(Integer userId);


    /**
     * 新增数据
     *
     * @param sysRoleUser 实例对象
     * @return 影响行数
     */
    int insert(SysRoleUser sysRoleUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

}