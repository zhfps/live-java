package com.live.zhf.security.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.live.zhf.exception.exception.CaptchaExcetion;
import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import com.live.zhf.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class AuthenctiationFailureHandler implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("鉴权失败");
        Result<String> result = new Result<String>();
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
           if(exception instanceof CaptchaExcetion){
            result = ResultBuilder.error(exception.getMessage(), ResultCode.CAPTCH_ERROE);
           }else if(exception instanceof LockedException){
                result = ResultBuilder.error("账户被锁定，登录失败!", ResultCode.AUTH_ERROE);
            }else if(exception instanceof BadCredentialsException){

                result = ResultBuilder.error("账户名或密码输入错误，登录失败!", ResultCode.AUTH_ERROE);
            }else if(exception instanceof DisabledException){

                result = ResultBuilder.error("账户被禁用，登录失败", ResultCode.AUTH_ERROE);
            }else if(exception instanceof AccountExpiredException){

                result = ResultBuilder.error("账户已过期，登录失败!", ResultCode.AUTH_ERROE);
            }else if(exception instanceof CredentialsExpiredException){

                result = ResultBuilder.error("密码已过期，登录失败!", ResultCode.AUTH_ERROE);
            }else if(exception instanceof InsufficientAuthenticationException){

                result = ResultBuilder.error("未登录", ResultCode.NOT_LOGIN);
            }else{
                result = ResultBuilder.error(exception.getMessage(), ResultCode.AUTH_ERROE);;
            }
            response.getWriter().write(objectMapper.writeValueAsString(result));

    }
}
