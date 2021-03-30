package com.daniel.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Package: com.daniel.util
 * @ClassName: MD5Util
 * @Author: daniel
 * @CreateTime: 2021/3/30 16:40
 * @Description: 对用户输入的明文密码进行加密处理. 客户端：pass = 明文 + 固定的salt加密。 服务端：pass = 用户输入  +随机salt加密
 */
public class MD5Util {

    /**
     * 客户端固定的盐值，用于加密
     */
    private static final String salt = "1a2b3c4d";

    /**
     * 明文密码转成MD5
     * @param str 需要进行MD5加密的字符串
     * @return 加密后的明文密码
     */
    public static String getMD5(String str) {
        String md5Hex = DigestUtils.md5Hex(str);
        return md5Hex;
    }

    /**
     * 将明文进行一次MD5加密，首先先拼接上我们固定的盐值，接着再将拼接后的密码进行一次MD5加密
     * @param inputPass 传入的明文
     * @return 明文MD5加密后的密文
     */
    public static String inputPassToFromPass(String inputPass) {
        String str = ""+salt.charAt(0)+ salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        return getMD5(str);
    }

    /**
     * 将加密后的密文，传入我们的盐值，再进行一次加密，得到需要存入数据库中的密文
     * @param fromPass 加密一次后的密文
     * @param salt 加密需要的盐值，这是随机的，因此作为形参！
     * @return 存入数据库中的密文
     */
    public static String fromPassToDBPass(String fromPass, String salt ) {
        String str = ""+ salt.charAt(0)+ salt.charAt(2) + fromPass +salt.charAt(5) + salt.charAt(4);
        return getMD5(str);
    }

    /**
     * 将明文密码转成输入到DB中的密文密码，经历两次MD5加密
     * @param inputPass 明文密码
     * @param DBSalt 加密的盐
     * @return 存入到数据库中的密码
     */
    public static String inputPassToDBPass(String inputPass, String DBSalt ) {
        String fromPass = inputPassToFromPass(inputPass);
        String DBPass = fromPassToDBPass(fromPass, DBSalt);
        return DBPass;
    }

}
