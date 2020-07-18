package com.live.zhf.common.controller;

import com.github.pagehelper.PageInfo;
import com.live.zhf.base.BaseController;
import com.live.zhf.common.entity.SysPermission;
import com.live.zhf.common.entity.SysUser;
import com.live.zhf.common.service.SysUserService;
import com.live.zhf.utils.JwtTokenUtil;
import com.live.zhf.exception.exception.SysException;
import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import com.live.zhf.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表控制层
 *
 * @author makejava
 * @since 2020-05-14 20:39:43
 */
@Api(value = "用户管理", tags = "用户管理")
@RestController
@RequestMapping(value = "api/user/")
public class SysUserController implements BaseController<SysUser> {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;
    @Resource
    private ResultBuilder resultBuilder;

    @Resource
    private JwtTokenUtil jwtConfig;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "获取一条用户记录")
    @PreAuthorize("hasAuthority('sys:user:query')")
    @GetMapping("get")
    public Result<SysUser> get(Integer id) {
        return this.sysUserService.get(id);
    }

    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:user:query')")
    @GetMapping("queryPage")
    public Result<PageInfo> queryPage(String userName,String status, Integer currentPage, Integer pageSize, String order, Integer sortType) {
        return sysUserService.queryPage(userName,status,currentPage,pageSize,order,sortType);
    }

    @ApiOperation(value = "创建用户")
    @PreAuthorize("hasAuthority('sys:user:add')")
    @PostMapping("insert")
    @Override
    public Result<Boolean> insert(@RequestBody SysUser sysUser) throws SysException {
        return sysUserService.insert(sysUser);
    }

    @ApiOperation(value = "更新用户")
    @PreAuthorize("hasAuthority('sys:user:update')")
    @PostMapping("update")
    @Override
    public Result<Boolean> update(@RequestBody SysUser sysUser) {
        return sysUserService.update(sysUser);
    }

    @ApiOperation(value = "删除用户")
    @PreAuthorize("hasAuthority('sys:user:del')")
    @DeleteMapping("delete")
    @Override
    public Result<Boolean> delete(Integer id) {
        return sysUserService.delete(id);
    }

    @ApiOperation(value = "获取用户权限")
    @PreAuthorize("hasAuthority('sys:user:query')")
    @GetMapping("getUserPermission")
    public Result<List<SysPermission>> getUserPermission(Integer userId){
        Result<List<SysPermission>> result = resultBuilder.success(this.sysUserService.getUserPermission(userId),ResultCode.SUCCESS);
        return  result;
    }


}