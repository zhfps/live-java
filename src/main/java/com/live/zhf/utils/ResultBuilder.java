package com.live.zhf.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: YunXi
 * @author: it_dog
 * @create: 2019-11-10
 **/
@Configuration
public class ResultBuilder {
    //成功，返回数据
    public static <T> Result<T> success(T t,ResultCode code){
        Result<T> result = new Result<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        result.setData(t);
        return result;
    }

    //失败，返回失败信息
    public static <T> Result<T> error(T t, ResultCode code){
        Result<T> result = new Result<T>();
        result.setCode(code.getCode());
        result.setData(t);
        result.setMsg(code.getMsg());
        return result;
    }  //失败，返回失败信息
    public static <T> Result<T> error(ResultCode code){
        Result<T> result = new Result<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }
}
