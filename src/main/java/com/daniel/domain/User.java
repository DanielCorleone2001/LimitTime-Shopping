package com.daniel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.daniel.domain
 * @ClassName: User
 * @Author: daniel
 * @CreateTime: 2021/3/28 18:38
 * @Description:
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private int id;

    private String name;
}
