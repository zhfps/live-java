package com.live.zhf.common.controller;

import com.live.zhf.common.entity.Menu;
import com.live.zhf.common.entity.SysRoleUser;
import com.live.zhf.common.entity.SysUserMenu;
import com.live.zhf.common.service.SysUserMenuService;
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
 * (SysUserMenu)表控制层
 *
 * @author makejava
 * @since 2020-07-20 21:21:52
 */
@Api(value = "菜单相关接口",tags = "菜单相关接口")
@RestController
@RequestMapping("api/menu/")
public class SysUserMenuController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserMenuService sysUserMenuService;

    /**
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "根据userId获取用户菜单")
    @GetMapping("getUserMenu")
    public Result<List<Menu>> getUserMenu(Integer id) {
        return this.sysUserMenuService.queryById(id);
    }


    @ApiOperation(value = "给用户分配菜单")
//    @PreAuthorize("hasAuthority('sys:user:setmenu')")
    @PostMapping("setUserMenu")
    public Result<Boolean> setUserMenu(@RequestParam(value = "userId",required = true) Integer userId, @RequestBody List<Integer> menuIds) {
        this.sysUserMenuService.deleteById(userId);
        for (Integer menuId: menuIds) {
            SysUserMenu sysUserMenu = new SysUserMenu();
            sysUserMenu.setMenuId(menuId);
            sysUserMenu.setUserId(userId);
            this.sysUserMenuService.insert(sysUserMenu);
        }
        return new ResultBuilder().success(true, ResultCode.SUCCESS);
    }
}