package com.live.zhf.common.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.live.zhf.common.entity.SysUser;
import com.live.zhf.common.service.SysUserService;
import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import com.live.zhf.utils.ResultCode;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表控制层
 *
 * @author makejava
 * @since 2020-05-14 20:39:43
 */
@RestController
@RequestMapping(value = "api/user/")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;
    @Resource
    private ResultBuilder resultBuilder;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getUserById")
    public Result<SysUser> getUserById(Integer id) {
        SysUser user = this.sysUserService.queryById(id);
        Result<SysUser> result = this.resultBuilder.success(user, ResultCode.SUCCESS);
        return result;
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("getUserAll")
    public Result<PageInfo> getUserAll(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<SysUser> users = this.sysUserService.queryAll();
        PageInfo pageInfo = new PageInfo(users);
        Result<PageInfo> result = this.resultBuilder.success(pageInfo, ResultCode.SUCCESS);
        return result;
    }

}