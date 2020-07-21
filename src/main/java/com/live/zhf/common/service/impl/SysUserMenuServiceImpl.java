package com.live.zhf.common.service.impl;

import com.live.zhf.common.entity.menu.Menu;
import com.live.zhf.common.entity.menu.SysMenu;
import com.live.zhf.common.entity.SysUserMenu;
import com.live.zhf.common.dao.SysUserMenuDao;
import com.live.zhf.common.service.SysUserMenuService;
import com.live.zhf.common.entity.menu.MenuTree;
import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import com.live.zhf.utils.ResultCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUserMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-07-20 21:21:52
 */
@Service("sysUserMenuService")
public class SysUserMenuServiceImpl implements SysUserMenuService {
    @Resource
    private SysUserMenuDao sysUserMenuDao;

    @Resource
    private ResultBuilder resultBuilder;

    @Override
    public Result<List<Menu>> queryById(Integer userId) {
        List<SysMenu> menus = this.sysUserMenuDao.queryById(userId);
        List<Menu> tree =  getMenus(menus);
        Result<List<Menu>> result = this.resultBuilder.success(tree, ResultCode.SUCCESS);
        return result;
    }

    @Override
    public List<Menu> getUserMenu(Integer userId) {
        List<SysMenu> menus = this.sysUserMenuDao.queryById(userId);
        List<Menu> tree =  getMenus(menus);
        return tree;
    }

    @Override
    public Result<List<SysUserMenu>> getUserMenuId(Integer userId) {
        return this.resultBuilder.success(this.sysUserMenuDao.getUserMenuId(userId), ResultCode.SUCCESS);
    }

    public static List<Menu> getMenus(List<SysMenu> sysMenus) {
        MenuTree menuTree =new MenuTree(sysMenus);
        List<Menu> tree=menuTree.buildTree();
        return tree;
    }
    @Override
    public Result<Boolean> insert(SysUserMenu sysUserMenu) {
        int index = this.sysUserMenuDao.insert(sysUserMenu);
        if(index>0){
            return resultBuilder.success(true, ResultCode.SUCCESS);
        }else {
            return resultBuilder.success(false, ResultCode.SUCCESS);
        }
    }

    @Override
    public Result<Boolean> deleteById(Integer userId) {
        int index = this.sysUserMenuDao.deleteById(userId);
        if(index>0){
            return resultBuilder.success(true, ResultCode.SUCCESS);
        }else {
            return resultBuilder.success(false, ResultCode.SUCCESS);
        }
    }
}