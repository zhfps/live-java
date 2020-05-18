package com.live.zhf.common.controller;

import com.live.zhf.common.entity.Menu;
import com.live.zhf.common.entity.Meta;
import com.live.zhf.common.entity.SysMenu;
import com.live.zhf.common.entity.SysUser;
import com.live.zhf.common.service.SysMenuService;
import com.live.zhf.utils.MenuTree;
import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import com.live.zhf.utils.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (SysMenu)表控制层
 *
 * @author makejava
 * @since 2020-05-15 20:37:20
 */
@RestController
@RequestMapping("/api/sys/")
public class SysMenuController {
    /**
     * 服务对象
     */
    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private ResultBuilder resultBuilder;

    @GetMapping("getAll")
    @ResponseBody
    public Result<List<Menu>> getAll() {
        List<Menu> tree = this.sysMenuService.queryAll();
        Result<List<Menu>> result = this.resultBuilder.success(tree, ResultCode.SUCCESS);
        return result;
    }

}