package com.live.zhf.security.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import com.live.zhf.utils.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Result<Authentication> result = ResultBuilder.success(authentication, ResultCode.SUCCESS);;

        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
