package com.live.zhf.common.controller;

import com.live.zhf.common.entity.SysRolePermission;
import com.live.zhf.common.service.SysRolePermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysRolePermission)表控制层
 *
 * @author makejava
 * @since 2020-07-13 23:43:16
 */
@RestController
@RequestMapping("sysRolePermission")
public class SysRolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public List<SysRolePermission> selectOne(Integer id) {
        return this.sysRolePermissionService.queryById(id);
    }

}