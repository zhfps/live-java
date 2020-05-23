package com.live.zhf.utils;

import com.live.zhf.common.entity.Select;
import com.live.zhf.common.entity.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuToSelect {
    private List<SysMenu> menuList = new ArrayList<SysMenu>();
    private List<Select<Integer,String>> tree = new ArrayList<Select<Integer,String>>();
    public MenuToSelect(List<SysMenu> menuList) {
        this.menuList=menuList;
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
        for(SysMenu menuNode : menuList) {
            if(menuNode.getParentId() == pNode.getValue()) {
                Select<Integer,String> select = new Select<Integer,String>();
                select.setLabel(menuNode.getTitle());
                select.setValue(menuNode.getId());
                childrenSelect.add(buildChildrenTree(select));
            }
        }
        pNode.setChildren(childrenSelect);
        return pNode;
    }

    //获取根节点
    private List<Select<Integer,String>> getRootNode() {
        List<Select<Integer,String>> rootMenuLists = new ArrayList<Select<Integer,String>>();
        for(SysMenu menuNode : menuList) {
            if(menuNode.getParentId() < 1) {
                Select<Integer,String> select = new Select<Integer,String>();
                select.setLabel(menuNode.getTitle());
                select.setValue(menuNode.getId());
                rootMenuLists.add(select);
            }
        }
        return rootMenuLists;
    }
}
