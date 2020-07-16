package com.live.zhf.common.service.impl;

import com.live.zhf.common.entity.SysRoleUser;
import com.live.zhf.common.dao.SysRoleUserDao;
import com.live.zhf.common.service.SysRoleUserService;
import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import com.live.zhf.utils.ResultCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysRoleUser)表服务实现类
 *
 * @author makejava
 * @since 2020-07-16 22:47:23
 */
@Service("sysRoleUserService")
public class SysRoleUserServiceImpl implements SysRoleUserService {
    @Resource
    private SysRoleUserDao sysRoleUserDao;


    @Resource
    private ResultBuilder resultBuilder;

    @Override
    public Result<List<SysRoleUser>> queryById(Integer userId) {
        return resultBuilder.success(this.sysRoleUserDao.queryById(userId), ResultCode.SUCCESS);
    }

    @Override
    public Result<Boolean> insert(SysRoleUser sysRoleUser) {
        int index = this.sysRoleUserDao.insert(sysRoleUser);
        if(index>0){
            return resultBuilder.success(true, ResultCode.SUCCESS);
        }else {
            return resultBuilder.success(false, ResultCode.SUCCESS);
        }

    }

    @Override
    public Result<Boolean> deleteById(Integer userId) {
        int index = this.sysRoleUserDao.deleteById(userId);
        if(index>0){
            return resultBuilder.success(true, ResultCode.SUCCESS);
        }else {
            return resultBuilder.success(false, ResultCode.SUCCESS);
        }
    }
}