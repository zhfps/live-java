package com.live.zhf.exception;

import com.live.zhf.exception.exception.CaptchaExcetion;
import com.live.zhf.exception.exception.InsertException;
import com.live.zhf.exception.exception.NotFoundUserException;
import com.live.zhf.exception.exception.SysException;
import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import com.live.zhf.utils.ResultCode;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
        Result<String> result= ResultBuilder.error(e.getMessage(), ResultCode.PERMISSION_TOKEN_ERROE);
        return result;
    }


    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    public Result<String> handlerException(Exception e){
        System.out.println(e.getMessage());
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(e.getMessage(), ResultCode.PERMISSION_TOKEN_ERROE);
        return result;
    }


    @ExceptionHandler(value = { AuthenticationException.class })
    @ResponseBody
    public Result<String> handlerAuthorizationException(AuthenticationException e){
        System.out.println(e.getMessage());
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(e.getMessage(), ResultCode.PERMISSION_TOKEN_ERROE);
        return result;
    }


    @ExceptionHandler(value = { SysException.class })
    @ResponseBody
    public Result<String> handlerSysException(SysException e){
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(e.getMessage(), ResultCode.PERMISSION_TOKEN_ERROE);
        return result;
    }


    @ExceptionHandler(value = { MissingServletRequestParameterException.class })
    @ResponseBody
    public Result<String> handlerMissingServletRequestParameterException(MissingServletRequestParameterException e){
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(e.getMessage(), ResultCode.QUERY_ERROR);
        return result;
    }


    @ExceptionHandler(value = { InsertException.class })
    @ResponseBody
    public Result<String> handlerInsertException(InsertException e){
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(e.getMessage(), ResultCode.PERMISSION_TOKEN_ERROE);
        return result;
    }
    @ExceptionHandler(value = { UsernameNotFoundException.class })
    @ResponseBody
    public Result<String> handlerUsernameNotFoundException(UsernameNotFoundException e){
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(e.getMessage(), ResultCode.NOT_USER);
        return result;
    }
    @ExceptionHandler(value = { NotFoundUserException.class })
    @ResponseBody
    public Result<String> handlerNotFoundUserException(NotFoundUserException e){
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(e.getMessage(), ResultCode.NOT_USER);
        return result;
    }

    /**
     * 权限不足
     * @param e
     * @return
     */
    @ExceptionHandler(value = { AccessDeniedException.class })
    @ResponseBody
    public Result<String> handlerAccessDeniedException(AccessDeniedException e){
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(e.getMessage(), ResultCode.AUTH_NOT);
        return result;
    }

    /***
     * 验证码错误
     * @param e
     * @return
     */
    @ExceptionHandler(value = { CaptchaExcetion.class })
    @ResponseBody
    public Result<String> handlerCaptchaExcetion(CaptchaExcetion e){
        log.error(e.getMessage());
        Result<String> result= ResultBuilder.error(e.getMessage(), ResultCode.NOT_USER);
        return result;
    }
}
