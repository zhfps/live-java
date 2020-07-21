package com.live.zhf.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.live.zhf.common.dao.SysMenuDao;
import com.live.zhf.common.entity.menu.*;
import com.live.zhf.common.service.SysMenuService;
import com.live.zhf.utils.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public Result<SysMenu> get(Integer id) {
        Result<SysMenu> result = this.resultBuilder.success(this.sysMenuDao.get(id), ResultCode.SUCCESS);
        return result;
    }


    @Override
    public Result<PageInfo> queryPage(Integer currentPage, Integer pageSize,String order,Integer sortType) {
        String orderBy = order;
        if(sortType == 1){
            orderBy+=" desc";
        }else {
            orderBy+=" asc";
        }

        PageHelper.startPage(currentPage, pageSize,orderBy);
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
    public Result<List<Select<Integer,String>>> getSelect(String type){
        List<SysMenu> menus = this.sysMenuDao.getSelect(type);
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
    public Result<Boolean> insert(SysMenu sysMenu) {
       Result<Boolean> result;

       Integer index = this.sysMenuDao.insert(sysMenu);

       if(index<1){
             result= this.resultBuilder.error(true, ResultCode.CREATE_ERROE);
       }else {
            result= this.resultBuilder.success(true, ResultCode.SUCCESS);

       }
        return result;
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public Result<Boolean> update(SysMenu sysMenu) {
         Result<Boolean> result;
        Integer index = this.sysMenuDao.update(sysMenu);

        if(index<1){
            result= this.resultBuilder.error(true, ResultCode.CREATE_ERROE);
        }else {
            result= this.resultBuilder.success(true, ResultCode.SUCCESS);

        }
        return result;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Result<Boolean> delete(Integer id){
        Result<Boolean> result;
        Integer index = this.sysMenuDao.delete(id);

        if(index<1){
            result= this.resultBuilder.error(true, ResultCode.CREATE_ERROE);
        }else {
            result= this.resultBuilder.success(true, ResultCode.SUCCESS);

        }
        return result;
    }
}