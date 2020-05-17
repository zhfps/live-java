package com.live.zhf.utils;

/**
 * @program: YunXi
 * @author: it_dog
 * @create: 2019-11-10
 **/
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(200, "success"),
    PERMISSION_NO_ACCESS(70001, "无访问权限"),
    PERMISSION_TOKEN_ERROE(70002, "Token有误");

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
