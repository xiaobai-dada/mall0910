package com.hc.mall.service.impl;



import com.alibaba.dubbo.config.annotation.Service;
import com.hc.mall.bean.UserInfo;
import com.hc.mall.mapper.UserInfoMapper;
import com.hc.mall.service.UserService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // 表示当前UsernfoMapper在容器中！
//    @Autowired
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.selectAll();
    }

    @Override
    public void addUser(UserInfo userInfo) {

        // 有选择的添加
        // insertSelective 和 insert 的区别： 效果都一样，sql语句不一样

        // insertSelective 只会设置你插入的字段   insert into  user_info(id,name) value(null,"张三")
        // insert 所有字段统一添加一遍            insert into  user_info(id,name......) value(null,"张三",null.......)
        userInfoMapper.insertSelective(userInfo);
    }

    @Override
    public void updUser(UserInfo userInfo) {
        // 根据Id 修改name
        // update userInfo set name=? where id=?
        // UserInfo userInfo1 = new UserInfo();

        userInfoMapper.updateByPrimaryKeySelective(userInfo);


    }

    @Override
    public void updUserByName(UserInfo userInfo) {
        // 根据name 修改 loginName

        // update userInfo set loginName=? where name=?
        Example example = new Example(UserInfo.class);
        // 创建修改条件
        // 第一个参数：property 指的是实体类的属性名，还是数据库表中的字段名？
        // 第二个参数：修改的具体值
        example.createCriteria().andEqualTo("name",userInfo.getName());

        // 第一个参数：userInfo 表示要修改的数据
        // 第二个参数：表示根据什么条件修改
        userInfoMapper.updateByExampleSelective(userInfo,example);


    }

//    @Override
//    public void delUser(UserInfo userInfo) {
//        // 删除数据
//        // delete from userInfo where id = ?
//        userInfoMapper.deleteByPrimaryKey(userInfo);
//    }

//    @Override
//    public void delUser(UserInfo userInfo) {
//        // 删除数据
//        // delete from userInfo where name = ?
//        // example 主要作用就是封装条件
//        Example example = new Example(UserInfo.class);
//        example.createCriteria().andEqualTo("name",userInfo.getName());
//        userInfoMapper.deleteByExample(example);
//    }


    @Override
    public void delUser(UserInfo userInfo) {
        // 删除数据
        // delete from userInfo where nickName = ?
        // example 主要作用就是封装条件
        userInfoMapper.delete(userInfo);
    }
}