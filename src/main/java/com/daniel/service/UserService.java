package com.daniel.service;

import com.daniel.dao.UserDao;
import com.daniel.domain.User;
import com.daniel.exception.GlobalException;
import com.daniel.redis.RedisService;
import com.daniel.redis.key.UserKey;
import com.daniel.result.CodeMsg;
import com.daniel.util.MD5Util;
import com.daniel.util.UUIDUtil;
import com.daniel.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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

    @Autowired
    public RedisService redisService;

    public static final String COOKI_NAME_TOKEN = "token";
    /**
     * 通过ID来获取到USer对象
     * @param id 目标用户ID
     * @return  返回的结果对象
     */
    public User getUserById ( long id ) {
        return userDao.getUserById(id);
    }

    /**
     * 为会话设置一个cookie
     * @param response
     * @param token 用户的信息
     * @param user 用户的信息
     */
    private void addCookie (HttpServletResponse response, String token, User user ) {
        //更新redis中的缓存信息
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie("token", token);
        //将cookie的过期时间和redis中的过期时间一致
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public User getUserByToken (HttpServletResponse response, String token ) {
        //非空判断
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        //从redis中获取对应的对象
        User user = redisService.get(UserKey.token, token, User.class);
        /**
         * 延长该用户的session有效期。
         * 非空的时候，相当于是用户在前一次登录的session过期前，就退出了登录又重新登录
         * 那么这时候就重新分配一个会话，延长过期时间
         */
        if ( user != null ) {
            addCookie(response, token, user);
        }
        return user;
    }

    /**
     * 登录功能，包含了用户非空校验，登录密码校验，生成会话的功能
     * @param response
     * @param loginVO 前端传入的登录信息
     * @return
     */
    public boolean login (HttpServletResponse response, LoginVO loginVO ) {
        if ( loginVO == null ) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }

        String mobile = loginVO.getMobile();
        //明文密码
        String fromPass = loginVO.getPassword();
        //通过电话号码获取到对象
        User user = getUserById(Long.parseLong(mobile));
        if ( user == null ) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //数据库中的密码
        String DBPassword = user.getPassword();
        //数据库中的盐
        String DBSalt = user.getSalt();
        //将明文密码通过数据库的盐来生成对应的密码，然后和数据库中的密码进行对比来判断是否密码正确
        String calcPassWord = MD5Util.fromPassToDBPass(fromPass,DBSalt);
        if ( !calcPassWord.equals(DBPassword)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成对应的token
        String token = UUIDUtil.UUID_Create();
        addCookie(response, token, user);
        return true;
    }
}
