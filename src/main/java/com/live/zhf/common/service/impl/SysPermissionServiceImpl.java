package com.live.zhf.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.live.zhf.common.entity.menu.Select;
import com.live.zhf.common.entity.SysPermission;
import com.live.zhf.common.dao.SysPermissionDao;
import com.live.zhf.common.entity.dto.BuildPermissionSelectTree;
import com.live.zhf.common.entity.dto.BuildPermissionTree;
import com.live.zhf.common.entity.dto.PermissionTree;
import com.live.zhf.common.service.SysPermissionService;
import com.live.zhf.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (SysPermission)表服务实现类
 *
 * @author makejava
 * @since 2020-05-18 23:06:28
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {
    @Resource
    private SysPermissionDao sysPermissionDao;

    @Resource
    private ResultBuilder resultBuilder;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Result<SysPermission> get(Integer id) {
        Result<SysPermission> result = resultBuilder.success(this.sysPermissionDao.get(id), ResultCode.SUCCESS);
        return result;
    }

    /**
     * 查询多条数据
     *
     * @param currentPage 查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @Override
    public Result<PageInfo> queryPage(Integer currentPage, Integer pageSize, String order, Integer sortType){
        String orderBy = order;

        if(sortType == 1){
            orderBy+=" desc";
        }else {
            orderBy+=" asc";
        }
        PageHelper.startPage(currentPage, pageSize,orderBy);
        List<SysPermission> permissions = this.sysPermissionDao.queryPage();
        PageInfo pageInfo = new PageInfo(permissions);
        Result<PageInfo> result = this.resultBuilder.success(pageInfo, ResultCode.SUCCESS);
        return result;
    }

    @Override
    public Result<List<PermissionTree>> getTree(String name) {
        List<SysPermission> permissions;
        List<PermissionTree> list;
        permissions = this.sysPermissionDao.queryPage();

        if(StringUtils.isEmpty(name)){
            list= getTrees(permissions,0);
            return this.resultBuilder.success(list, ResultCode.SUCCESS);
        }else {
            List<SysPermission> items = this.sysPermissionDao.getPermissionByName(name);
            if(!items.isEmpty()){
                Integer parentId = items.get(0).getId();
                PermissionTree tree = new PermissionTree();
                BeanUtils.copyProperties(items.get(0),tree);
                tree.setChildren(getTrees(permissions,parentId));
                list = new ArrayList<>();
                list.add(tree);
                return this.resultBuilder.success(list, ResultCode.SUCCESS);
            }else {
                return null;
            }
        }

    }

    @Override
    public Result<List<Select<Integer, String>>> getSelect(Integer type) {
        List<SysPermission> permissions = this.sysPermissionDao.getSelect(type);
        List<Select<Integer,String>> tree =  getSelect(permissions);
        Result<List<Select<Integer,String>>> result =this.resultBuilder.success(tree, ResultCode.SUCCESS);
        return result;
    }
    public static List<Select<Integer,String>> getSelect(List<SysPermission> list) {
        List<Select<Integer,String>> tree=new BuildPermissionSelectTree(list).buildTree();
        return tree;
    }
    public static List<PermissionTree> getTrees(List<SysPermission> list,Integer parentId) {
        BuildPermissionTree build =new BuildPermissionTree(list);
        List<PermissionTree> tree=build.buildTree(parentId);
        return tree;
    }
    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    @Override
    public Result<Boolean> insert(SysPermission sysPermission) {
        Result<Boolean> result;
        Integer cell =  this.sysPermissionDao.insert(sysPermission);
        if(cell > 0){
            result = this.resultBuilder.success(true,ResultCode.SUCCESS);
        }else {
            result = this.resultBuilder.error(true,ResultCode.CREATE_ERROE);
        }
        return result;

    }

    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    @Override
    public Result<Boolean> update(SysPermission sysPermission) {
        Result<Boolean> result;
       Integer cell = this.sysPermissionDao.update(sysPermission);
       if(cell > 0){
           result = this.resultBuilder.success(true,ResultCode.SUCCESS);
       }else {
           result = this.resultBuilder.error(true,ResultCode.CREATE_ERROE);
       }
       return result;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Result<Boolean> delete(Integer id) {
        Result<Boolean> result;
        Integer cell = this.sysPermissionDao.delete(id);
        if(cell > 0){
            result = this.resultBuilder.success(true,ResultCode.SUCCESS);
        }else {
            result = this.resultBuilder.error(true,ResultCode.CREATE_ERROE);
        }
        return result;
    }
}