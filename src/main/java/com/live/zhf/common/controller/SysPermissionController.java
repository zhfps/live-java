package com.live.zhf.common.controller;

import com.live.zhf.common.entity.SysPermission;
import com.live.zhf.common.service.SysPermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysPermission)表控制层
 *
 * @author makejava
 * @since 2020-05-18 23:06:28
 */
@RestController
@RequestMapping("api/permission")
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;

    @GetMapping("selectOne")
    public SysPermission selectOne(Integer id) {
        return this.sysPermissionService.queryById(id);
    }

}