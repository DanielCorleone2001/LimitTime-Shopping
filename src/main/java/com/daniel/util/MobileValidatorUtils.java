package com.daniel.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Package: com.daniel.util
 * @ClassName: MobileValidator
 * @Author: daniel
 * @CreateTime: 2021/3/30 19:12
 * @Description: 用正则表达式校验是否是正确的电话号码
 */
public class MobileValidatorUtils {

    /**
     * 电话号码的正则表达式
     */
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    /**
     * 检验输入的号码是否是正确的
     * @param mobileString 需要校验的电话号码
     * @return 返回校验结果
     */
    public static boolean isMobile ( String mobileString ) {
        if (StringUtils.isEmpty( mobileString ) ) {
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(mobileString);
        return matcher.matches();
    }
}
