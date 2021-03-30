package com.daniel.controller;

import com.daniel.domain.User;
import com.daniel.redis.RedisService;
import com.daniel.redis.UserKey;
import com.daniel.result.CodeMsg;
import com.daniel.result.DataResult;
import com.daniel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;

/**
 * @Package: com.daniel.controller
 * @ClassName: TestController
 * @Author: daniel
 * @CreateTime: 2021/3/17 11:33
 * @Description:
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    public UserService userService;

    @Autowired
    public RedisService redisService;

    @RequestMapping("/hello")
    @ResponseBody
    public DataResult<String> sayHello() {
        return DataResult.success("hello admin");
    }

    @RequestMapping("/error")
    @ResponseBody
    public DataResult<String> error() {
        return DataResult.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/login")
    @ResponseBody
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/sayHello")
    public String toHello(Model model) {
        model.addAttribute("name","admin");
        return "hello";
    }

    @RequestMapping("getUser")
    @ResponseBody
    public DataResult<User> getUserById () {
        User user = userService.getUserById(1);
        return DataResult.success(user);
    }

//    @RequestMapping("/redis/set")
//    @ResponseBody
//    public DataResult<Boolean> redisSet() {
//        User user = new User();
//        user.setId(1);
//        user.setName("test-2");
//        redisService.set(UserKey.getById,""+1,user);
//        return DataResult.success(true);
//    }

//    @RequestMapping("/redis/get")
//    @ResponseBody
//    public DataResult<User> redisGet() {
//        //User user = redisService.get(UserKey.getById,""+1,User.class);
//        //return DataResult.success(user);
//    }
}
