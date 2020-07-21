package com.live.zhf.common.entity.dto;

import com.live.zhf.common.entity.menu.Select;
import com.live.zhf.common.entity.SysPermission;
import java.util.ArrayList;
import java.util.List;

public class BuildPermissionSelectTree {
    private List<SysPermission> list = new ArrayList<SysPermission>();
    private List<Select<Integer,String>> tree = new ArrayList<Select<Integer,String>>();
    public BuildPermissionSelectTree(List<SysPermission> list) {
        this.list=list;
    }

    //建立树形结构
    public List<Select<Integer,String>> buildTree(){
        List<Select<Integer,String>> selectTree = new ArrayList<Select<Integer,String>>();
        for(Select<Integer,String> select : getRootNode()) {
            select=buildChildrenTree(select);
            selectTree.add(select);
        }
        return selectTree;
    }

    //递归，建立子树形结构
    private Select<Integer, String> buildChildrenTree(Select<Integer,String> pNode){
        List<Select<Integer,String>> childrenSelect= new ArrayList<Select<Integer,String>>();
        Integer index = 0;
        for(SysPermission node : list) {
            if(node.getParentId() == pNode.getValue()) {
                Select<Integer,String> select = new Select<Integer,String>();
                select.setLabel(node.getName());
                select.setValue(node.getId());
                childrenSelect.add(buildChildrenTree(select));
                list.remove(index);
            }
           index++;
        }
        pNode.setChildren(childrenSelect);
        return pNode;
    }

    //获取根节点
    private List<Select<Integer,String>> getRootNode() {
        List<Select<Integer,String>> rootMenuLists = new ArrayList<Select<Integer,String>>();
        Integer index = 0;
        for(SysPermission node : list) {
            if(node.getParentId() < 1) {
                Select<Integer,String> select = new Select<Integer,String>();
                select.setLabel(node.getName());
                select.setValue(node.getId());
                rootMenuLists.add(select);
                list.remove(index);
            }
            index++;
        }
        return rootMenuLists;
    }
}
