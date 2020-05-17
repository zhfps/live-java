package com.live.zhf.exception;

import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import com.live.zhf.utils.ResultCode;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlers {
    @ExceptionHandler(value = { SignatureException.class })
    @ResponseBody
    public Result<String> handlerSignatureException(SignatureException e){
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(ResultCode.SUCCESS);
        return result;
    }
    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    public Result<String> handlerException(Exception e){
        System.out.println(e.getMessage());
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(ResultCode.SUCCESS);
        return result;
    }
    @ExceptionHandler(value = { AuthenticationException.class })
    @ResponseBody
    public Result<String> handlerAuthorizationException(AuthenticationException e){
        System.out.println(e.getMessage());
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(ResultCode.SUCCESS);
        return result;
    }
    @ExceptionHandler(value = { SysException.class })
    @ResponseBody
    public Result<String> handlerSysException(SysException e){
        System.out.println(e.getMessage());
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(ResultCode.SUCCESS);
        return result;
    }
}
