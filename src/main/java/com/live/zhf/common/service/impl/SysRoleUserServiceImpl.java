package com.live.zhf.common.service.impl;

import com.live.zhf.common.entity.SysRoleUser;
import com.live.zhf.common.dao.SysRoleUserDao;
import com.live.zhf.common.service.SysRoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysRoleUser)表服务实现类
 *
 * @author makejava
 * @since 2020-05-18 22:57:29
 */
@Service("sysRoleUserService")
public class SysRoleUserServiceImpl implements SysRoleUserService {
    @Resource
    private SysRoleUserDao sysRoleUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public SysRoleUser queryById(Integer userId) {
        return this.sysRoleUserDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRoleUser> queryAllByLimit(int offset, int limit) {
        return this.sysRoleUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRoleUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleUser insert(SysRoleUser sysRoleUser) {
        this.sysRoleUserDao.insert(sysRoleUser);
        return sysRoleUser;
    }

    /**
     * 修改数据
     *
     * @param sysRoleUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleUser update(SysRoleUser sysRoleUser) {
        this.sysRoleUserDao.update(sysRoleUser);
        return this.queryById(sysRoleUser.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userId) {
        return this.sysRoleUserDao.deleteById(userId) > 0;
    }
}