package com.hc.mall.service;

import com.hc.mall.bean.UserAddress;
import com.hc.mall.bean.UserInfo;

import java.util.List;

// 业务逻辑层
public interface UserService  {

    /**
     * 查询所有数据
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 添加数据
     * @param userInfo
     */
    void addUser(UserInfo userInfo);

    /**
     * 修改数据
     */
    void updUser(UserInfo userInfo);
    /**
     * 修改数据
     */
    void updUserByName(UserInfo userInfo);

    // 删除 boolean , int ,void

    /**
     *  删除数据
     */
    void delUser(UserInfo userInfo);


    /**
     * 根据用户id查询用户的地址
     * @param userId
     * @return
     */
    public List<UserAddress> getUserAddressList(String userId);

    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    UserInfo login(UserInfo userInfo);

    /**
     * 认证用户
     * @param userId
     * @return
     */
    UserInfo verify(String userId);
}
