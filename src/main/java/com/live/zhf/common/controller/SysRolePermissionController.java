package com.live.zhf.common.controller;

import com.live.zhf.common.entity.SysPermission;
import com.live.zhf.common.entity.SysRolePermission;
import com.live.zhf.common.service.SysRolePermissionService;
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
 * (SysRolePermission)表控制层
 *
 * @author makejava
 * @since 2020-07-13 23:43:16
 */
@Api(value = "权限管理",tags = "权限相关接口")
@RestController
@RequestMapping("api/permission/")
public class SysRolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    /**
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "根据roelID 获取权限")
    @GetMapping("getRolePermission")
    public Result<List<SysRolePermission>> getRolePermission(Integer id) {
        return this.sysRolePermissionService.queryById(id);
    }


    @ApiOperation(value = "给角色分配权限")
    @PreAuthorize("hasAuthority('sys:role:setpermission')")
    @PostMapping("setRolePermission")
    public Result<Boolean> setRolePermission(@RequestParam(value = "roleId",required = true) Integer roleId, @RequestBody List<Integer> permissions) {
        this.sysRolePermissionService.deleteById(roleId);
        for (Integer permissionId: permissions) {
            SysRolePermission permission = new SysRolePermission();
            permission.setPermissionId(permissionId);
            permission.setRoleId(roleId);
            this.sysRolePermissionService.insert(permission);
        }
        return new ResultBuilder().success(true, ResultCode.SUCCESS);
    }

}