package com.live.zhf.base;

import com.github.pagehelper.PageInfo;
import com.live.zhf.utils.Result;

import java.util.List;

public interface BaseController<T> {
    Result<T> get(Integer id);

    Result<PageInfo> queryPage(Integer currentPage, Integer pageSize,String order,Integer sortType);

    Result<Boolean> insert(T t);

    Result<Boolean> update(T t);

    Result<Boolean> delete(Integer id);
}
