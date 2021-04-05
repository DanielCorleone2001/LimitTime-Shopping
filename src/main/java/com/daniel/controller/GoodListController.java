package com.daniel.controller;

import com.daniel.domain.User;
import com.daniel.redis.RedisService;
import com.daniel.service.GoodsService;
import com.daniel.service.MiaoshaService;
import com.daniel.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.soap.SOAPBinding;
import java.util.List;

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

    @Autowired
    GoodsService goodsService;

    @Autowired
     RedisService redisService;

    @Autowired
    MiaoshaService miaoshaService;

    /**
     * 这里直接传入User类，而不对User类的属性注入值，就需要将赋值的任务转交给UserArgumentResolver来处理
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/to_list")
    public String toGoodList(Model model, User user) {
        model.addAttribute("user",user);
        List<GoodsVO> goodsVOList = goodsService.getGoodsVOList();
        model.addAttribute("goodsList",goodsVOList);
        return "goods_list";
    }


    @RequestMapping("/to_detail/{goodsId}")
    public String detail (Model model, User user, @PathVariable("goodsId")long goodsId) {
        model.addAttribute("user",user);
        GoodsVO goodsVO = goodsService.getGoodsVObyGoodsId(goodsId);
        model.addAttribute("goods", goodsVO);

        long startAt = goodsVO.getStartDate().getTime();
        long endAt = goodsVO.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }

}
