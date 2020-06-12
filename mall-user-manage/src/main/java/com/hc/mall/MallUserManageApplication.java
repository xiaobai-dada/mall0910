package com.hc.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.hc.mall.mapper")
public class MallUserManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallUserManageApplication.class, args);
    }

}
