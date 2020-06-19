package com.hc.mall.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 小白
 * @create 2020-06-17-20:48
 */
public class RedisUitl {

    private JedisPool jedisPool;

    // String host,int port,int database 可以配置到 application.properties
    // 初始化连接池
    public void initJedisPool(String host,int port,int database){

        // 直接创建一个连接池的配置类
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置等待时间
        jedisPoolConfig.setMaxWaitMillis(10 * 1000);
        // 设置最小剩余数
        jedisPoolConfig.setMinIdle(10);
        // 开启缓冲池
        jedisPoolConfig.setBlockWhenExhausted(true);
        // 当用户获取到一个连接是，自检是否可用
        jedisPoolConfig.setTestOnBorrow(true);
        // 连接池对象
        jedisPool = new JedisPool(jedisPoolConfig,host,port,20*1000);

    }

    // 获取jedis
    public Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

}

