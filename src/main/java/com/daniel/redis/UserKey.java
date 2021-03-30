package com.daniel.redis;

/**
 * @Package: com.daniel.redis
 * @ClassName: UserKey
 * @Author: daniel
 * @CreateTime: 2021/3/28 21:00
 * @Description:
 */
public class UserKey extends BasePrefix{

    private UserKey(String prefix) {
        super(prefix);
    }
    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");

    @Override
    public int expireSeconds() {
        return 0;
    }
}
