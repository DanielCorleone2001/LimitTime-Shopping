package com.daniel.controller;

import com.daniel.domain.Order;
import com.daniel.domain.OrderInfo;
import com.daniel.domain.User;
import com.daniel.redis.RedisService;
import com.daniel.result.CodeMsg;
import com.daniel.service.GoodsService;
import com.daniel.service.MiaoshaService;
import com.daniel.service.OrderService;
import com.daniel.service.UserService;
import com.daniel.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Package: com.daniel.controller
 * @ClassName: MiaoshaController
 * @Author: daniel
 * @CreateTime: 2021/4/5 19:15
 * @Description:
 */

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String list(Model model, User user, @RequestParam("goodsId")long goodsId ) {
        if ( user == null ) {
            return "login";
        }

        GoodsVO goodsVO = goodsService.getGoodsVObyGoodsId(goodsId);
        //获取库存
        int stock = goodsVO.getGoodsStock();
        if ( stock <= 0 ) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }

        Order order = orderService.getOrderByUserIdAndGoodsId(user.getId(),goodsId);
        if ( order != null ) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }

        OrderInfo orderInfo = miaoshaService.miaosha(user,goodsVO);
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("goods",goodsVO);
        return "order_detail";
    }
}
