package com.daniel.validator;

import com.daniel.util.MobileValidatorUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Package: com.daniel.validator
 * @ClassName: MobileValidator
 * @Author: daniel
 * @CreateTime: 2021/3/30 18:22
 * @Description:
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    /**
     * 默认值为不需要，当进行初始化后变为true
     */
    private boolean required = false;

    /**
     * 初始化注解
     * @param constraintAnnotation
     */
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    /**
     * 注解的校验逻辑，调用utils中自己写的方法来判断是否是有效的电话号码
     * @param s 传入的电话号码
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if ( required ) {
            return MobileValidatorUtils.isMobile(s);
        } else {
            if (StringUtils.isEmpty(s)) {
                return true;
            } else {
                return  MobileValidatorUtils.isMobile(s);
            }
        }
    }
}
