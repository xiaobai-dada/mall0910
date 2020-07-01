package com.hc.mall.manageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.hc.mall.manageservice.mapper")
@EnableTransactionManagement  // 开启事务管理
@ComponentScan("com.hc.mall")
public class MallManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallManageServiceApplication.class, args);
    }

}
