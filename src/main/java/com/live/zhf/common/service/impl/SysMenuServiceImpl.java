package com.live.zhf.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.live.zhf.common.entity.*;
import com.live.zhf.common.dao.SysMenuDao;
import com.live.zhf.common.service.SysMenuService;
import com.live.zhf.exception.DeleteException;
import com.live.zhf.exception.InsertException;
import com.live.zhf.exception.UpdateException;
import com.live.zhf.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (SysMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-05-15 20:37:20
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysMenuDao sysMenuDao;

    @Resource
    private ResultBuilder resultBuilder;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Result<SysMenu> queryById(Integer id) {
        Result<SysMenu> result = this.resultBuilder.success(this.sysMenuDao.queryById(id), ResultCode.SUCCESS);
        return result;
    }


    @Override
    public Result<PageInfo> queryPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<SysMenu> menus = this.sysMenuDao.queryPage();
        PageInfo pageInfo = new PageInfo(menus);
        Result<PageInfo> result = this.resultBuilder.success(pageInfo, ResultCode.SUCCESS);
        return result;
    }
    @Override
    public Result<List<Menu>> queryAll(String name, String status) {
        List<SysMenu> menus = this.sysMenuDao.queryAll(name, status);
        List<Menu> tree =  getMenus(menus);
        Result<List<Menu>> result = this.resultBuilder.success(tree, ResultCode.SUCCESS);
        return result;
    }

    public static List<Menu> getMenus(List<SysMenu> sysMenus) {
        MenuTree menuTree =new MenuTree(sysMenus);
        List<Menu> tree=menuTree.buildTree();
        return tree;
    }
    @Override
    public Result<List<Select<Integer,String>>> getSelect(){
        List<SysMenu> menus = this.sysMenuDao.getSelect();
        List<Select<Integer,String>> tree =  getSelect(menus);
        Result<List<Select<Integer,String>>> result =this.resultBuilder.success(tree, ResultCode.SUCCESS);
        return result;
    }
    public static List<Select<Integer,String>> getSelect(List<SysMenu> sysMenus) {
        List<Select<Integer,String>> tree=new MenuToSelect(sysMenus).buildTree();
        return tree;
    }
    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public Result<SysMenu> insert(SysMenu sysMenu) throws InsertException {
       Integer index = this.sysMenuDao.insert(sysMenu);
       if(index<1){
           throw new InsertException("新增菜单失败");
       }else {
           Result<SysMenu> result = this.resultBuilder.success(sysMenu, ResultCode.SUCCESS);
           return result;
       }

    }

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public Result<SysMenu> update(SysMenu sysMenu) throws UpdateException {
        Integer index = this.sysMenuDao.update(sysMenu);
        if(index<1){
            throw new UpdateException("修改菜单失败");
        }else {
            Result<SysMenu> result = this.resultBuilder.success(sysMenu, ResultCode.SUCCESS);
            return result;
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Result<Boolean> deleteById(Integer id) throws DeleteException {
        Integer index = this.sysMenuDao.deleteById(id);
        if(index<1){
            throw new DeleteException("删除菜单失败");
        }else {
            Result<Boolean> result = this.resultBuilder.success(false, ResultCode.SUCCESS);
            return result;
        }
    }
}