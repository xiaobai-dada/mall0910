package com.hc.mall.listservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hc.mall")
public class MallListServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallListServiceApplication.class, args);
    }

}
