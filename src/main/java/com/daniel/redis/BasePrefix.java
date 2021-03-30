package com.daniel.redis;

/**
 * @Package: com.daniel.redis
 * @ClassName: BasePrefix
 * @Author: daniel
 * @CreateTime: 2021/3/28 19:31
 * @Description: 设置key的前缀，以及过期时间
 */
public abstract class BasePrefix implements KeyPrefix{

    /**
     * 过期时间
     */
    private int expireSeconds;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 只有String类型的构造方法，过期时间设置为0，即永不过期。
     * @param prefix
     */
    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    /**
     * 完整参数的构造方法
     * @param expireSeconds 设置的过期时间
     * @param prefix    设置的前缀
     */
    public BasePrefix ( int expireSeconds, String prefix ) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    /**
     * 获取过期时间
     * @return 过期时间
     */
    public int getExpireSeconds () {
        return expireSeconds;
    }

    /**
     * 获取前缀
     * @return 封装好的前缀。格式为“类名：prefix”
     */
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+" : "+prefix;
    }

    /**
     * 获取过期时间，0为默认永不过期
     * @return
     */
    public int expireSeconds() {
        return expireSeconds;
    }

}
