package com.daniel.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Package: com.daniel.redis
 * @ClassName: RedisPoolFactory
 * @Author: daniel
 * @CreateTime: 2021/3/28 19:18
 * @Description:
 */

@Service
public class RedisPoolFactory {

    @Autowired
    private RedisConfig redisConfig;


    /**
     * 工厂模式，获取一个新的jedis pool对象。
     * 先配置基本的信息，再沟槽jedis pool对象
     * @return jedis poll对象
     */
    @Bean
    public JedisPool JedisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        jedisPoolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000 );

        //建立一个新的jedis对象，按照上面的配置作为参数进行初始化
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout() * 1000, redisConfig.getPassword(), 0);

        return jedisPool;
    }
}
