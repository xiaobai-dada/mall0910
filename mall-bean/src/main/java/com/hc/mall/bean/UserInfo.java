package com.hc.mall.bean;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@ToString
public class UserInfo implements Serializable {

     // id   Column   GeneratedValue 都是通用mapper的注解

        @Id // 表示主键
        @Column  // 普通字段
        // 获取数据库的主键自增
        // mysql : strategy = GenerationType.IDENTITY
        // oracle : strategy = GenerationType.AUTO
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;
        @Column
        private String loginName;
        @Column
        private String nickName;
        @Column
        private String passwd;
        @Column
        private String name;
        @Column
        private String phoneNum;
        @Column
        private String email;
        @Column
        private String headImg;
        @Column
        private String userLevel;
}