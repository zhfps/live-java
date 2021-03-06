package com.live.zhf.common.entity.menu;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
public class MenuTree {
    private List<SysMenu> menuList = new ArrayList<SysMenu>();
    public MenuTree(List<SysMenu> menuList) {
        this.menuList=menuList;
    }

    //建立树形结构
    public List<Menu> buildTree(){
        List<Menu> treeMenus =new  ArrayList<Menu>();
        for(Menu menuNode : getRootNode()) {
            menuNode=buildChildrenTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private Menu buildChildrenTree(Menu pNode){
        List<Menu> childrenMenus =new  ArrayList<Menu>();
        Integer index = 0 ;
        for(SysMenu menuNode : menuList) {
            if(menuNode.getParentId() == pNode.getId()) {
                Menu menu = new Menu();
                BeanUtils.copyProperties(menuNode,menu);
                Meta meta = new Meta();
                BeanUtils.copyProperties(menuNode,meta);
                menu.setMeta(meta);
                childrenMenus.add(buildChildrenTree(menu));
                menuList.remove(index);
            }
            index++;
        }
        pNode.setChildren(childrenMenus);
        return pNode;
    }

    //获取根节点
    private List<Menu> getRootNode() {
        List<Menu> rootMenuLists =new  ArrayList<Menu>();
        Integer index = 0;
        for(SysMenu menuNode : menuList) {
            if(menuNode.getParentId() < 1) {
                Menu menu = new Menu();
                BeanUtils.copyProperties(menuNode,menu);
                Meta meta = new Meta();
                BeanUtils.copyProperties(menuNode,meta);
                menu.setMeta(meta);
                rootMenuLists.add(menu);
                menuList.remove(index);
            }
            index++;
        }
        return rootMenuLists;
    }
}