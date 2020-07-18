package com.live.zhf.common.controller;

import com.live.zhf.common.entity.SysRolePermission;
import com.live.zhf.common.entity.SysRoleUser;
import com.live.zhf.common.service.SysRoleUserService;
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
 * (SysRoleUser)表控制层
 *
 * @author makejava
 * @since 2020-07-16 22:43:48
 */
@Api(value = "权限管理",tags = "角色相关接口")
@RestController
@RequestMapping("api/role/")
public class SysRoleUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleUserService sysRoleUserService;

    /**
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "根据userId获取角色")
    @GetMapping("getUserRole")
    public Result<List<SysRoleUser>> getRolePermission(Integer id) {
        return this.sysRoleUserService.queryById(id);
    }


    @ApiOperation(value = "给用户分配角色")
    @PreAuthorize("hasAuthority('sys:user:setrole')")
    @PostMapping("setUserRole")
    public Result<Boolean> setRolePermission(@RequestParam(value = "userId",required = true) Integer userId, @RequestBody List<Integer> roles) {
        this.sysRoleUserService.deleteById(userId);
        for (Integer roleId: roles) {
            SysRoleUser permission = new SysRoleUser();
            permission.setRoleId(roleId);
            permission.setUserId(userId);
            this.sysRoleUserService.insert(permission);
        }
        return new ResultBuilder().success(true, ResultCode.SUCCESS);
    }

}