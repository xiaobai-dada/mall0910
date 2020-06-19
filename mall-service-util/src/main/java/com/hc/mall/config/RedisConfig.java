package com.hc.mall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小白
 * @create 2020-06-17-20:48
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host:disable}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.database}")
    private int database;

    @Bean
    public RedisUitl getRedisUitl(){

        if ("disable".equals(host)){
            return null;
        }

        RedisUitl redisUitl = new RedisUitl();
        // 将initJedisPool方法中的值传入
        redisUitl.initJedisPool(host,port,database);
        return redisUitl;

    }

}
