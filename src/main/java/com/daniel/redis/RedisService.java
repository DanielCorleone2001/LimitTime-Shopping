package com.daniel.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Package: com.daniel.redis
 * @ClassName: RedisService
 * @Author: daniel
 * @CreateTime: 2021/3/28 19:10
 * @Description: 集成了Redis的基本操作。其实就是对基本操作的封装，进行一些特定的处理而已
 */

@Service
public class RedisService {

    @Autowired
    JedisPool jedispool;

    /**
     * 将范型类转成String类型，即反序列化.使用fastJson能将其转成String类型
     * @param t 泛型实例
     * @param <T> 范型种类
     * @return
     */
    private <T> String booleanToString (T t) {
        if ( t == null ) {
            return null;
        }

        //获取泛型的类，接着对不同的类进行不同的序列化处理。
        Class<?> clazz = t.getClass();
        if ( clazz == int.class || clazz == Integer.class ) {
            return ""+t;
        } else if ( clazz == String.class ) {
            return (String)t;
        } else if ( clazz == long.class || clazz == Long.class ) {
            return ""+t;
        } else {
            return JSON.toJSONString(t);
        }
    }

    /**
     * 将string的内容转成特定的类
     * @param str string，包含内容
     * @param clazz 转成的类的实例
     * @param <T> 转成的类
     * @return
     */
    @SuppressWarnings("unchocked")
    private <T> T stringToBoolean (String str, Class<T> clazz ) {
        if ( str == null || str.length() < 1 || clazz == null ) {
            return null;
        }

        if ( clazz == int.class || clazz == Integer.class ) {
            return (T)Integer.valueOf(str);
        } else if ( clazz == String.class ) {
            return (T)str;
        } else if ( clazz == long.class || clazz == Long.class ) {
            return (T)Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    /**
     * 将jedis实例返回到jedis pool中，调用关闭方法即可
     * @param jedis 需要关闭的jedis实例
     */
    private void closeRedisToPool ( Jedis jedis ) {
        if ( jedis != null ) {
            jedis.close();
        }
    }

    /**
     * 获取value
     * @param prefix 前缀
     * @param key 需要查找的value的key
     * @param clazz 泛型的实例
     * @param <T> 范型
     * @return
     */
    public <T> T get( KeyPrefix prefix, String key, Class<T> clazz ) {
        Jedis jedis = null;
        try {
            jedis = jedispool.getResource();
            //封装成不会冲突的key
            String afterKey = prefix.getPrefix() + key;
            //获取到真正的key对应的value
            String str = jedis.get(afterKey);
            //将value序列化
            T t = stringToBoolean(str, clazz);
            return t;
        } finally {
            closeRedisToPool(jedis);
        }
    }

    /**
     * 判断redis中是否存在某个key
     * @param keyPrefix key的前缀
     * @param key 需要判断的key
     * @param t
     * @param <T>
     * @return
     */
    public <T> boolean exits (KeyPrefix keyPrefix, String key, T t ) {
        Jedis jedis = null;
        try {
            jedis = jedispool.getResource();
            //生成真正的key
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            closeRedisToPool(jedis);
        }
    }

    /**
     * 配置某个key的value
     * @param prefix 前缀
     * @param key 目标key
     * @param value 传入的value
     * @param <T>
     * @return
     */
    public <T> boolean set ( KeyPrefix prefix, String key, T value ) {
        Jedis jedis = null;
        try {
            jedis = jedispool.getResource();
            //将需要设置的value转成String类型
            String str = booleanToString(value);
            if ( str == null || str.length() <= 0 ) {
                return false;
            }

            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            //获取该key所剩余的时间
            int seconds = prefix.expireSeconds();
            if ( seconds <= 0 ) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        } finally {
            closeRedisToPool(jedis);
        }
    }

    /**
     * 增加1
     * @param prefix 目标key的前缀
     * @param key 目标key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedispool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            return  jedis.incr(realKey);
        }finally {
            closeRedisToPool(jedis);
        }
    }

    /**
     * 将value减少1
     * @param prefix 目标的key的前缀
     * @param key 目标key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedispool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            return  jedis.decr(realKey);
        }finally {
            closeRedisToPool(jedis);
        }
    }
}
