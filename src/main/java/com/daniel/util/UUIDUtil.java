package com.daniel.util;

import java.util.UUID;

/**
 * @Package: com.daniel.util
 * @ClassName: UUIDUtil
 * @Author: daniel
 * @CreateTime: 2021/3/30 21:40
 * @Description: 生成随机的ID
 */
public class UUIDUtil {

    /**
     * 将UUID中的“-”替换掉
     * @return 替换后的UUID
     */
    public static String UUID_Create() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
