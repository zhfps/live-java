package com.live.zhf.utils;

import java.io.Serializable;

/**
 * @program: Yunxi
 * @author: it_dog
 * @create: 2019-11-10
 **/
public class Result<T> {
    private int code;
    private String msg;
    private T data;// 数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
