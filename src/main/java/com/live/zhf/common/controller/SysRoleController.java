package com.live.zhf.common.controller;

import com.github.pagehelper.PageInfo;
import com.live.zhf.base.BaseController;
import com.live.zhf.base.BaseService;
import com.live.zhf.common.entity.SysRole;
import com.live.zhf.common.service.SysRoleService;
import com.live.zhf.exception.exception.SysException;
import com.live.zhf.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysRole)表控制层
 *
 * @author makejava
 * @since 2020-05-18 23:05:26
 */
@Api(value = "角色相关接口",tags = "角色")
@RestController
@RequestMapping("/api/sysRole/")
public class SysRoleController implements BaseController<SysRole> {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

    @ApiOperation(value = "根据ID 获取一条权限")
    @PreAuthorize("hasAuthority('sys:role:query')")
    @GetMapping("get")
    @Override
    public Result<SysRole> get(Integer id) {
        return sysRoleService.get(id);
    }


    @ApiOperation(value = "获取全部角色")
    @PreAuthorize("hasAuthority('sys:role:query')")
    @GetMapping("queryAll")
    public Result<List<SysRole>> queryAll() {
        return sysRoleService.queryAll();
    }


    @ApiOperation(value = "分页获取数据")
    @PreAuthorize("hasAuthority('sys:role:query')")
    @GetMapping("queryPage")
    public Result<PageInfo> queryPage(String description, Integer currentPage, Integer pageSize,String order, Integer sortType) {
        return sysRoleService.queryPage(description,currentPage,pageSize,order,sortType);
    }


    @ApiOperation(value = "新增")
    @PreAuthorize("hasAuthority('sys:role:add')")
    @PostMapping("insert")
    @Override
    public Result<Boolean> insert(@RequestBody SysRole sysRole) throws SysException {
        return sysRoleService.insert(sysRole);
    }


    @ApiOperation(value = "更新")
    @PreAuthorize("hasAuthority('sys:role:update')")
    @PostMapping("update")
    @Override
    public Result<Boolean> update(@RequestBody SysRole sysRole) {
        return sysRoleService.update(sysRole);
    }


    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('sys:role:del')")
    @DeleteMapping(value = "delete")
    @Override
    public Result<Boolean> delete(Integer id) {
        return sysRoleService.delete(id);
    }
}