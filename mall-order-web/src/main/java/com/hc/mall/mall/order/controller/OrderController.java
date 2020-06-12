package com.hc.mall.mall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hc.mall.bean.UserInfo;
import com.hc.mall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {

    @Reference
    UserService userService;

    @ResponseBody
    @RequestMapping("trade")
    public List<UserInfo> trade(){
        // 返回一个视图名称叫index.html
        List<UserInfo> all = userService.findAll();
        return all;
    }

}
