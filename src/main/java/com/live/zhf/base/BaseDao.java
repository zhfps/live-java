package com.live.zhf.base;

import java.util.List;

public interface BaseDao<T> {

    T get(Integer id);


    List<T> queryPage();


    int insert(T t);


    int update(T t);


    int delete(Integer id);
}
