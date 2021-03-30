package com.daniel.service;

import com.daniel.dao.UserDao;
import com.daniel.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package: com.daniel.service
 * @ClassName: UserService
 * @Author: daniel
 * @CreateTime: 2021/3/28 18:41
 * @Description:
 */

@Service
public class UserService {

    @Autowired
    public UserDao userDao;

    /**
     * 通过ID来获取到USer对象
     * @param id 目标用户ID
     * @return  返回的结果对象
     */
    public User getUserById ( int id ) {
        return userDao.getById(id);
    }
}
