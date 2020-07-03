package com.live.zhf.base;

import com.github.pagehelper.PageInfo;
import com.live.zhf.exception.exception.SysException;
import com.live.zhf.utils.Result;

import java.util.List;

public interface BaseController<T> {
    Result<T> get(Integer id);
    Result<Boolean> insert(T t) throws SysException;
    Result<Boolean> update(T t);
    Result<Boolean> delete(Integer id);
}
