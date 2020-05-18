package com.live.zhf.common.service.impl;

import com.live.zhf.common.entity.Menu;
import com.live.zhf.common.entity.Meta;
import com.live.zhf.common.entity.SysMenu;
import com.live.zhf.common.dao.SysMenuDao;
import com.live.zhf.common.service.SysMenuService;
import com.live.zhf.utils.MenuTree;
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

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysMenu queryById(Integer id) {
        return this.sysMenuDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysMenu> queryAllByLimit(int offset, int limit) {
        return this.sysMenuDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Menu> queryAll() {
        List<Menu> menuList = new ArrayList<Menu>();
        List<SysMenu> sysMenus = this.sysMenuDao.queryAll();
        return getMenus(sysMenus);
    }

    public static List<Menu> getMenus(List<SysMenu> sysMenus) {
        MenuTree menuTree =new MenuTree(sysMenus);
        List<Menu> tree=menuTree.buildTree();
        return tree;
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public SysMenu insert(SysMenu sysMenu) {
        this.sysMenuDao.insert(sysMenu);
        return sysMenu;
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public SysMenu update(SysMenu sysMenu) {
        this.sysMenuDao.update(sysMenu);
        return this.queryById(sysMenu.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysMenuDao.deleteById(id) > 0;
    }
}