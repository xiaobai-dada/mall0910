package com.hc.mall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 老铁
 * @create 2020-06-17 11:49
 */

/*
    <beans>
        <bean id=“redisUtil” class="com.hc.mall.config.RedisUitl">
            <property name="host" value="">
            <property name="port" value="">
            <property name="database" value="">
        </bean>

    </beans>

 */


@Configuration // 相当于 .xml文件
public class RedisConfig {




    // disable表示如果没有从配置文件中获取到host，则默认值为disable
    @Value("${spring.redis.host:disable}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.database}")
    private int database;


    // 将获取到的数据传入initJedisPool方法中
    @Bean
    public RedisUitl getRedisUitl(){

        if("disable".equals(host)){
            return null;
        }

        RedisUitl redisUitl = new RedisUitl();
        // 将initJedisPool方法中的值传入
        redisUitl.initJedisPool(host,port,database);
        return redisUitl;
    }
}
