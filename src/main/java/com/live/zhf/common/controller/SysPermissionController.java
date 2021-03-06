package com.live.zhf.common.controller;

import com.github.pagehelper.PageInfo;
import com.live.zhf.base.BaseController;
import com.live.zhf.common.entity.menu.Select;
import com.live.zhf.common.entity.SysPermission;
import com.live.zhf.common.entity.dto.PermissionTree;
import com.live.zhf.common.service.SysPermissionService;
import com.live.zhf.exception.exception.SysException;
import com.live.zhf.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    @PreAuthorize("hasAuthority('sys:permission:query')")
    @GetMapping("get")
    public Result<SysPermission> get(Integer id) {
        return this.sysPermissionService.get(id);
    }


    @ApiOperation(value = "分页获取数据")
    @PreAuthorize("hasAuthority('sys:permission:query')")
    @GetMapping("queryPage")
    public Result<PageInfo> queryPage(@RequestParam(value = "currentPage") Integer currentPage, @RequestParam(value = "pageSize")Integer pageSize,  @RequestParam(value = "order") String order, @RequestParam(value = "sortType")Integer sortType) {
        return this.sysPermissionService.queryPage(currentPage,pageSize,order,sortType);
    }

    @ApiOperation(value = "权限树")
    @PreAuthorize("hasAuthority('sys:permission:query')")
    @GetMapping("getTree")
    public Result<List<PermissionTree>> getTree(String name) {
        return this.sysPermissionService.getTree(name);
    }

    @ApiOperation(value = "权限下拉框")
    @PreAuthorize("hasAuthority('sys:permission:query')")
    @GetMapping("getSelect")
    public Result<List<Select<Integer, String>>> getSelect(Integer type) {
        return this.sysPermissionService.getSelect(type);
    }

    @ApiOperation(value = "新增")
    @PreAuthorize("hasAuthority('sys:permission:add')")
    @PostMapping("insert")
    @Override
    public Result<Boolean> insert( @RequestBody SysPermission sysPermission) throws SysException {
        return this.sysPermissionService.insert(sysPermission);
    }
    @ApiOperation(value = "更新")
    @PreAuthorize("hasAuthority('sys:permission:update')")
    @PostMapping("update")
    @Override
    public Result<Boolean> update( @RequestBody SysPermission sysPermission) {
        return this.sysPermissionService.update(sysPermission);
    }
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('sys:permission:del')")
    @DeleteMapping(value = "delete")
    @Override
    public Result<Boolean> delete(Integer id) {
        return this.sysPermissionService.delete(id);
    }

}