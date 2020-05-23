package com.live.zhf.common.dao;

import com.live.zhf.common.entity.SysMenu;
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
public interface SysMenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysMenu queryById(Integer id);


    /**
     * 查询
     */
    List<SysMenu> queryPage();
    /**
     * 查询
     */
    List<SysMenu> queryAll(String title, String status);
    /**
     * 前端菜单目录下拉框
     */
    List<SysMenu> getSelect();

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 影响行数
     */
    int insert(SysMenu sysMenu);

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 影响行数
     */
    int update(SysMenu sysMenu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}