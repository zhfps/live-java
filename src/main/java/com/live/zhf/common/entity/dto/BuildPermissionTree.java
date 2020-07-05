package com.live.zhf.common.entity.dto;

import com.live.zhf.common.entity.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BuildPermissionTree {
 private List<SysPermission> sysPermissions = new ArrayList<SysPermission>();
 public BuildPermissionTree(List<SysPermission> sysPermissions) {
  this.sysPermissions=sysPermissions;
 }

 //建立树形结构S
 public List<PermissionTree> buildTree(Integer parentId){
  List<PermissionTree> trees =new  ArrayList<PermissionTree>();
  for(PermissionTree item : getRootNode(parentId)) {
   item=buildChildrenTree(item);
   trees.add(item);
  }
  return trees;
 }

 //递归，建立子树形结构
 private PermissionTree buildChildrenTree(PermissionTree pNode){
  List<PermissionTree> childrens =new  ArrayList<PermissionTree>();
  Integer index = 0;
  for(SysPermission node : sysPermissions) {
   if(node.getParentId() == pNode.getId()) {
    PermissionTree root = new PermissionTree();
    BeanUtils.copyProperties(node,root);
    childrens.add(buildChildrenTree(root));
    sysPermissions.remove(index);
   }
   index++;
  }
  pNode.setChildren(childrens);
  return pNode;
 }

 //获取根节点
 private List<PermissionTree> getRootNode(Integer parentId) {
  List<PermissionTree> roots =new  ArrayList<PermissionTree>();
  Integer index = 0;
  for(SysPermission node : sysPermissions) {
   if(node.getParentId() == parentId) {
    PermissionTree root = new PermissionTree();
    BeanUtils.copyProperties(node,root);
    roots.add(root);
    sysPermissions.remove(index);
   }
   index++;
  }
  return roots;
 }
}
