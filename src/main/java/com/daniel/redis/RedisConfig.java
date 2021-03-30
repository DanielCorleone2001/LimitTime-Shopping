package com.daniel.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Package: com.daniel.redis
 * @ClassName: RedisConfig
 * @Author: daniel
 * @CreateTime: 2021/3/28 19:10
 * @Description: Redis配置信息，通过配置文件，指定前缀进行注入
 */

@Component
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfig {

    private String host;

    private int port;

    private int timeout;//秒

    private int poolMaxTotal;

    private int poolMaxIdle;

    private int poolMaxWait;//秒

    private  String password;
}
