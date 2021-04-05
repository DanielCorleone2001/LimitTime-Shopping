package com.daniel.redis.key;

import com.daniel.redis.BasePrefix;

/**
 * @Package: com.daniel.redis
 * @ClassName: UserKey
 * @Author: daniel
 * @CreateTime: 2021/3/28 21:00
 * @Description:
 */
public class UserKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public static UserKey token = new UserKey(TOKEN_EXPIRE,"tk");

    public UserKey(String prefix) {
        super(prefix);
    }

    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

}
