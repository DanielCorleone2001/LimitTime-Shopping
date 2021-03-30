package com.daniel.redis;

/**
 * @Package: com.daniel.redis
 * @ClassName: KeyPrefix
 * @Author: daniel
 * @CreateTime: 2021/3/28 19:29
 * @Description: 设置Redis的key的前缀，保证key的唯一性
 */
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
