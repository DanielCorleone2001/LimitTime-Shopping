package com.daniel.controller;

import com.daniel.redis.RedisService;
import com.daniel.result.DataResult;
import com.daniel.service.UserService;
import com.daniel.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Package: com.daniel.controller
 * @ClassName: LoginController
 * @Author: daniel
 * @CreateTime: 2021/3/30 17:00
 * @Description:
 */
@Controller
@RequestMapping("login")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public DataResult<Boolean> doLogin(HttpServletResponse response, @Valid LoginVO loginVO) {
        log.info(loginVO.toString());
        userService.login(response, loginVO);
        return DataResult.success(true);
    }
}
