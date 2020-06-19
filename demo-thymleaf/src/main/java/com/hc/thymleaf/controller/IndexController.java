package com.hc.thymleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @RequestMapping("index")
    public String item(HttpServletRequest request, HttpSession httpSession) {

        request.setAttribute("testKey","数据测试value");

        List<String> arrayList = new ArrayList<>();

        arrayList.add("张三");
        arrayList.add("李四");
        arrayList.add("王五");
        arrayList.add("赵六");
        arrayList.add("田七");

        request.setAttribute("arrayList",arrayList);

        request.setAttribute("age",18);

        httpSession.setAttribute("job","IT软件工程师");

        request.setAttribute("green","<span style='color:green'>绿色</span>");

        return "index";
    }
}