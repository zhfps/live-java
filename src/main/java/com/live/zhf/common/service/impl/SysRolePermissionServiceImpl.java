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
 * @since 2020-07-13 23:43:16
 */
@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
    @Resource
    private SysRolePermissionDao sysRolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public List<SysRolePermission> queryById(Integer roleId) {
        return this.sysRolePermissionDao.queryById(roleId);
    }


    /**
     * 新增数据
     *
     * @param sysRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(SysRolePermission sysRolePermission) {
        this.sysRolePermissionDao.insert(sysRolePermission);
        return this.sysRolePermissionDao.insert(sysRolePermission)>0;
    }


    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer roleId) {
        return this.sysRolePermissionDao.deleteById(roleId) > 0;
    }
}