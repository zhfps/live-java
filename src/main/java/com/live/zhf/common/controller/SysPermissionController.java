package com.live.zhf.common.controller;

import com.github.pagehelper.PageInfo;
import com.live.zhf.base.BaseController;
import com.live.zhf.common.entity.SysPermission;
import com.live.zhf.common.service.SysPermissionService;
import com.live.zhf.exception.exception.SysException;
import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysPermission)表控制层
 *
 * @author makejava
 * @since 2020-05-18 23:06:28
 */
@Api(value = "权限管理",tags = "权限相关接口")
@RestController
@RequestMapping("api/permission/")
public class SysPermissionController implements BaseController<SysPermission> {

    @Resource
    private SysPermissionService sysPermissionService;

    @ApiOperation(value = "根据ID 获取一条权限")
    @GetMapping("get")
    public Result<SysPermission> get(Integer id) {
        return this.sysPermissionService.get(id);
    }
    @ApiOperation(value = "分页获取数据")
    @GetMapping("queryPage")
    public Result<PageInfo> queryPage(Integer currentPage, Integer pageSize, String order, Integer sortType) {
        return this.sysPermissionService.queryPage(currentPage,pageSize,order,sortType);
    }
    @ApiOperation(value = "新增")
    @PostMapping("insert")
    @Override
    public Result<Boolean> insert( @RequestBody SysPermission sysPermission) throws SysException {
        return this.sysPermissionService.insert(sysPermission);
    }
    @ApiOperation(value = "更新")
    @PostMapping("update")
    @Override
    public Result<Boolean> update( @RequestBody SysPermission sysPermission) {
        return this.sysPermissionService.update(sysPermission);
    }
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "delete")
    @Override
    public Result<Boolean> delete(Integer id) {
        return this.sysPermissionService.delete(id);
    }

}