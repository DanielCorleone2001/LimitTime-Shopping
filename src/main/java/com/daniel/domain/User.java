package com.daniel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Package: com.daniel.domain
 * @ClassName: User
 * @Author: daniel
 * @CreateTime: 2021/3/28 18:38
 * @Description: 和数据库中属性一致的用户类
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private Long id;

    private String nickname;

    private String password;

    private String salt;

    private String head;

    private Date registerDate;

    private Date lastLoginDate;

    private Integer loginCount;
}
