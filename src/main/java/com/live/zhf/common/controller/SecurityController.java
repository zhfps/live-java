package com.live.zhf.common.controller;

import com.google.code.kaptcha.Producer;
import com.live.zhf.common.service.SysUserService;
import com.live.zhf.exception.exception.CodeException;
import com.live.zhf.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Api(value = "权限模板",tags = "权限模块")
@Controller
public class SecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private Producer captchaProducer;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Resource
    private SysUserService sysUserService;

    @ApiOperation(value ="获取验证码" )
    @GetMapping(value = "/captchaImage/{version}")
    public void getCode(HttpServletRequest req, HttpServletResponse resp, @PathVariable String version) throws IOException {
        try {
            String capStr;
            BufferedImage bufferedImage = null;
            String capText = captchaProducer.createText();
            redisTemplate.opsForValue().set("Code",capText);
            bufferedImage = captchaProducer.createImage(capText);
            // ResponseUtils工具类
            CodeUtil.responseBufferedImage(req, resp, bufferedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @ApiOperation(value ="登录" )
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result<String> login(
            @RequestParam(name = "userName",required = true)String userName,
            @RequestParam(name = "password",required = true)String password,
            @RequestParam(name = "code",required = true)String code,HttpServletRequest request) throws CodeException {

        if(StringUtils.isEmpty(code)) {
            throw new CodeException("验证码不能为空");
        }

        // 1. 获取服务器redeis中的验证码
        String Code = redisTemplate.opsForValue().get("Code");

        // 2. 请求验证码校验
        if(!StringUtils.equals(Code, code)) {
            throw new CodeException("验证码不正确");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password)
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);


      return ResultBuilder.success(sysUserService.login(authentication), ResultCode.SUCCESS);
    }

    @ApiOperation(value ="获取当前用户信息" )
    @GetMapping(value = "/api/info")
    @ResponseBody
    public Result<Authentication> Info(){

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      return ResultBuilder.success(authentication, ResultCode.SUCCESS);
    }

    @ApiOperation(value ="退出登录" )
    @GetMapping(value = "/api/logout")
    @ResponseBody
    public Result<String> logOut(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        redisTemplate.delete(userName);
        return ResultBuilder.success(userName, ResultCode.SUCCESS);
    }
}
