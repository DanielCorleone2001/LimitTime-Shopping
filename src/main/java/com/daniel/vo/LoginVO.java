package com.daniel.vo;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * @Package: com.daniel.vo
 * @ClassName: LoginVO
 * @Author: daniel
 * @CreateTime: 2021/3/30 17:27
 * @Description: 和前端进行沟通的登录VO
 */

@Data
@ToString
public class LoginVO {

    @NotNull
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
