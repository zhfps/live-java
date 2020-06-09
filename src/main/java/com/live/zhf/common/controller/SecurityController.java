package com.live.zhf.common.controller;

import com.google.code.kaptcha.Producer;
import com.live.zhf.common.entity.LoginUser;
import com.live.zhf.common.service.SysUserService;
import com.live.zhf.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Api(value = "权限模板",tags = "权限模块")
@Controller
public class SecurityController {
    @Resource
    private Producer captchaProducer;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Resource
    private SysUserService sysUserService;

    @ApiOperation(value ="获取验证码" )
    @GetMapping(value = "/captchaImage")
    public void getCode (HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
//    @ApiOperation(value ="登录" )
//    @GetMapping(value = "/login")
//    public Result<String> login(String userName, String password, String code){
//      return ResultBuilder.success(sysUserService.login(userName,password,code), ResultCode.SUCCESS);
//    }

}
