package com.daniel.vo;

import com.daniel.validator.IsMobile;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Package: com.daniel.vo
 * @ClassName: LoginVO
 * @Author: daniel
 * @CreateTime: 2021/3/30 17:27
 * @Description: 和前端进行沟通的登录VO
 */

@Data
public class LoginVO {

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;

    @Override
    public String toString() {
        return "LoginVo [mobile=" + mobile + ", password=" + password + "]";
    }
}
