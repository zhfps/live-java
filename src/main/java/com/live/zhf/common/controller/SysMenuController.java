package com.live.zhf.common.controller;

import com.github.pagehelper.PageInfo;
import com.live.zhf.base.BaseController;
import com.live.zhf.common.entity.*;
import com.live.zhf.common.service.SysMenuService;
import com.live.zhf.utils.Result;
import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysMenu)表控制层
 *
 * @author makejava
 * @since 2020-05-15 20:37:20
 */
@Api(value = "菜单相关接口",tags = "菜单相关接口")
@RestController
@RequestMapping("api/menu/")
public class SysMenuController implements BaseController<SysMenu> {

    @Resource
    private SysMenuService sysMenuService;

    @ApiOperation(value = "获取一条菜单")
    @PreAuthorize("hasAuthority('sys:menu:get')")
    @GetMapping("get")
    @Override
    public Result<SysMenu> get(Integer id) {
        return this.sysMenuService.get(id);
    }

    @ApiOperation(value = "获取菜单树")
    @GetMapping("getTree")
    @ResponseBody
    public Result<List<Menu>> queryAll(
            @ApiParam(name = "name",value = "菜单名",required = false)
            @RequestParam(name = "name",required = false)
                    String name,
            @ApiParam(name = "status",value = "状态",required = true)
            @RequestParam(name = "status",required = true)
                    String status    ) {
            if(name.isEmpty()){
              name = null;
            }
         return sysMenuService.queryAll(name,status);
    }

    @ApiOperation(value = "获取目树")
    @GetMapping("getSelect")
    @ResponseBody
    public Result<List<Select<Integer,String>>> getSelect( ){
         return sysMenuService.getSelect();
    }



    @ApiOperation(value = "分页获取菜单")
    @GetMapping("getTable")
    @ResponseBody
    public Result<PageInfo> queryPage(
            @ApiParam(name = "currentPage",value = "当前页",required = true)
            @RequestParam(name = "currentPage",required = true)
                    Integer currentPage,
            @ApiParam(name = "pageSize",value = "每页条数",required = true)
            @RequestParam(name = "pageSize",required = true)
                    Integer pageSize,
            @ApiParam(name = "order",value = "排序字段",required = true)
            @RequestParam(name = "排序字段",required = true)
            String order,
            @ApiParam(name = "sortType",value = "排序方式",required = true)
            @RequestParam(name = "排序方式",required = true)
            Integer sortType) {
        return sysMenuService.queryPage(currentPage,pageSize,order,sortType);
    }
    
    @ApiOperation(value = "新增菜单")
    @PostMapping(value = "insert")
    @ResponseBody
    public Result<Boolean> insert(
            @ApiParam(name = "menu",value = "菜单",required = true)
            @RequestBody SysMenu menu
           ) {
        return sysMenuService.insert(menu);
    }

    @ApiOperation(value = "修改菜单")
    @PostMapping("updateMenu")
    @ResponseBody
    public Result<Boolean> update(
            @ApiParam(name = "menu",value = "菜单",required = true)
                  @Validated @RequestBody SysMenu menu
           ){
        return sysMenuService.update(menu);
    }


    @ApiOperation(value = "删除")
    @DeleteMapping("delete")
    @ResponseBody
    public Result<Boolean> delete(
            @ApiParam(name = "id",value = "id号",required = true)
            @RequestParam(name = "id",required = true)
                    Integer id
           ) {
        return sysMenuService.delete(id);
    }
}