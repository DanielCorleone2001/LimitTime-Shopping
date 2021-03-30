package com.daniel.controller;

import com.daniel.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Package: com.daniel.controller
 * @ClassName: GoodListController
 * @Author: daniel
 * @CreateTime: 2021/3/30 20:16
 * @Description:
 */
@Controller
@RequestMapping("goods")
public class GoodListController {

    @RequestMapping("/to_list")
    public String toGoodList(Model model, User user) {
        model.addAttribute("user",user);
        return "goods_list";
    }

}
