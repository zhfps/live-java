package com.live.zhf.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping(value = "/api/")
public class SystemController {
    @RequestMapping(value = "Test", method = RequestMethod.GET)
    @ResponseBody
    public String Test(){
        return "hello world";
    }
}
