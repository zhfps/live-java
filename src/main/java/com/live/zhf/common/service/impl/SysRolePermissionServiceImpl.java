package com.live.zhf.common.service.impl;

import com.live.zhf.common.entity.SysRolePermission;
import com.live.zhf.common.dao.SysRolePermissionDao;
import com.live.zhf.common.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysRolePermission)表服务实现类
 *
 * @author makejava
 * @since 2020-05-18 23:04:56
 */
@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
    @Resource
    private SysRolePermissionDao sysRolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleid 主键
     * @return 实例对象
     */
    @Override
    public SysRolePermission queryById(Integer roleid) {
        return this.sysRolePermissionDao.queryById(roleid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRolePermission> queryAllByLimit(int offset, int limit) {
        return this.sysRolePermissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public SysRolePermission insert(SysRolePermission sysRolePermission) {
        this.sysRolePermissionDao.insert(sysRolePermission);
        return sysRolePermission;
    }

    /**
     * 修改数据
     *
     * @param sysRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public SysRolePermission update(SysRolePermission sysRolePermission) {
        this.sysRolePermissionDao.update(sysRolePermission);
        return this.queryById(sysRolePermission.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer roleid) {
        return this.sysRolePermissionDao.deleteById(roleid) > 0;
    }
}