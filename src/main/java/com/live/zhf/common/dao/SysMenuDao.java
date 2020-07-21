package com.live.zhf.common.dao;

import com.live.zhf.base.BaseDao;
import com.live.zhf.common.entity.menu.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SysMenu)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-15 20:37:20
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenu> {
    /**
     * 获取全部数据
     * @param title
     * @param status
     * @return
     */
    List<SysMenu> queryAll(String title, String status);
    /**
     * 前端菜单目录下拉框
     */
    List<SysMenu> getSelect(@Param("type") String type);
}