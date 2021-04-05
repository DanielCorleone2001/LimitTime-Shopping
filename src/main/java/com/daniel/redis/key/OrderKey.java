package com.daniel.redis.key;

import com.daniel.redis.BasePrefix;

/**
 * @Package: com.daniel.redis.key
 * @ClassName: OrderKey
 * @Author: daniel
 * @CreateTime: 2021/4/1 20:31
 * @Description:
 */
public class OrderKey extends BasePrefix {

    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }
}
