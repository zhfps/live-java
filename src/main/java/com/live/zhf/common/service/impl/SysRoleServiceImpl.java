package com.live.zhf.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.live.zhf.common.entity.SysPermission;
import com.live.zhf.common.entity.SysRole;
import com.live.zhf.common.dao.SysRoleDao;
import com.live.zhf.common.service.SysRoleService;
import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import com.live.zhf.utils.ResultCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (SysRole)表服务实现类
 *
 * @author makejava
 * @since 2020-05-18 23:05:26
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    private ResultBuilder resultBuilder;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Result<SysRole> get(Integer id) {
        Result<SysRole> result = resultBuilder.success(this.sysRoleDao.get(id), ResultCode.SUCCESS);
        return result;
    }

    /**
     * 查询多条数据
     *
     * @param currentPage 查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @Override
    public Result<PageInfo> queryPage(String description, Integer currentPage, Integer pageSize,String order,Integer sortType){
        String orderBy = order;
        if(sortType == 1){
            orderBy+=" desc";
        }else {
            orderBy+=" asc";
        }
        PageHelper.startPage(currentPage, pageSize,orderBy);
        List<SysRole> roles = this.sysRoleDao.queryPage(description);
        PageInfo pageInfo = new PageInfo(roles);
        Result<PageInfo> result = this.resultBuilder.success(pageInfo, ResultCode.SUCCESS);
        return result;
    }

    @Override
    public Result<List<SysRole>> queryAll() {
        return this.resultBuilder.success(this.sysRoleDao.queryAll(), ResultCode.SUCCESS);
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public Result<Boolean> insert(SysRole sysRole) {
        Result<Boolean> result;
        sysRole.setCreateTime(new Date());
        Integer cell =  this.sysRoleDao.insert(sysRole);
        if(cell > 0){
            result = this.resultBuilder.success(true,ResultCode.SUCCESS);
        }else {
            result = this.resultBuilder.error(true,ResultCode.CREATE_ERROE);
        }
        return result;

    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public Result<Boolean> update(SysRole sysRole) {
        Result<Boolean> result;
        sysRole.setUpdateTime(new Date());
        Integer cell = this.sysRoleDao.update(sysRole);
        if(cell > 0){
            result = this.resultBuilder.success(true,ResultCode.SUCCESS);
        }else {
            result = this.resultBuilder.error(true,ResultCode.CREATE_ERROE);
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
    public Result<Boolean> delete(Integer id) {
        Result<Boolean> result;
        Integer cell = this.sysRoleDao.delete(id);
        if(cell > 0){
            result = this.resultBuilder.success(true,ResultCode.SUCCESS);
        }else {
            result = this.resultBuilder.error(true,ResultCode.CREATE_ERROE);
        }
        return result;
    }
}