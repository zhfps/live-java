package com.live.zhf.base;

import com.live.zhf.common.entity.SysMenu;

import java.util.List;

public interface BaseDao<T> {

    T get(Integer id);


    List<T> queryPage();


    int insert(T t);


    int update(T t);


    int delete(Integer id);
}
