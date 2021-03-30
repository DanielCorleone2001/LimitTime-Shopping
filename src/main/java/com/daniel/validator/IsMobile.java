package com.daniel.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Package: com.daniel.validator
 * @ClassName: IsMobile
 * @Author: daniel
 * @CreateTime: 2021/3/30 18:23
 * @Description: 电话号码校验注解
 */

@Target({ElementType.METHOD,ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,
        ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)//生命周期
@Constraint(validatedBy = {MobileValidator.class })
public @interface IsMobile {
    /**
     * 默认需要输入号码
     * @return
     */
    boolean required() default true;

    /**
     * 默认的输出信息
     * @return
     */
    String message() default "手机号码格式错误";

    /**
     *
     * @return
     */
    Class<?>[] groups() default { };

    /**
     *
     * @return
     */
    Class<? extends Payload>[] payload() default { };
}
