package com.hc.mall.mapper;


import com.hc.mall.bean.UserInfo;
import tk.mybatis.mapper.common.Mapper;

/**
 *  数据访问层
 *  Mapper<UserInfo> : 泛型表示你要操作的实体类
 */
public interface UserInfoMapper extends Mapper<UserInfo> {
}
