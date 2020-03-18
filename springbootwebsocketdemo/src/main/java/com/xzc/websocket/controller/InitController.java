package com.xzc.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName InitController
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2020/3/11 17:38
 * @Version 1.0
 **/
@Controller
public class InitController {

    @RequestMapping("/websocket")
    public String init() {
        return "websocket.html";
    }
    @RequestMapping("/websocket4567")
    public String init2() {

        return "websocket4567.html";
    }

    @RequestMapping("/test")
    public String test() {

        return "test.html";
    }
}