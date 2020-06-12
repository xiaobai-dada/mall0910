package com.hc.mall.controller;



import com.hc.mall.bean.UserInfo;
import com.hc.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserInfoController {

    // 调用服务层
    @Autowired
    private UserService userService;


    // 加 / 绝对路径
    // 不加 / 相对路径

    @RequestMapping("findAll")
    public List<UserInfo> findAll(){
        return userService.findAll();
    }

    // 数据应该从前台页面过来
    @RequestMapping("addUser")
    public void addUser(UserInfo userInfo){

        userInfo.setLoginName("adminStr");
        userInfo.setPasswd("666666");

        userService.addUser(userInfo);
        // 获取添加之后的主键
        System.out.println(userInfo.getId());

    }

    @RequestMapping("updById")
    public String updById(UserInfo userInfo){
        userInfo.setName("马大哈哈哈哈");
        userInfo.setId("5");
        userService.updUser(userInfo);
        return "OK";
    }

    @RequestMapping("updByName")
    public String updByName(UserInfo userInfo){
        userInfo.setName("马大哈哈哈哈");
        userInfo.setLoginName("Administrator");

        userService.updUserByName(userInfo);
        return "OK";
    }

    // http://localhost:8080/delUser?id=4
    // url传参
    // springmvc 传值方式中的一种！
    /*
    <form action ="delUser" method ="get">
            <input type="text" name="id"/>
            <input type="text" name="loginName"/>
           <input type ="submit" value="提交"/>
    </form>

     */
    @RequestMapping("delUser")
    public String delUser(UserInfo userInfo){
        userService.delUser(userInfo);
        return "OK";
    }

    @RequestMapping("delUserByName")
    public String delUserByName(UserInfo userInfo){
        userService.delUser(userInfo);
        return "OK";
    }

    @RequestMapping("delUserByNickName")
    public String delUserByNickName(UserInfo userInfo){
        userService.delUser(userInfo);
        return "OK";
    }
}
