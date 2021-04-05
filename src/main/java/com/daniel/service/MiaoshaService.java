package com.daniel.service;

import com.daniel.domain.OrderInfo;
import com.daniel.domain.User;
import com.daniel.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Package: com.daniel.service
 * @ClassName: MiaoshaService
 * @Author: daniel
 * @CreateTime: 2021/4/1 21:15
 * @Description:
 */

@Service
public class MiaoshaService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    /**
     * 执行秒杀，采用事务
     * @param user 成功秒杀的用户
     * @param goodsVO 秒杀的商品信息
     * @return 秒杀订单的详细信息
     */
    @Transactional
    public OrderInfo miaosha(User user, GoodsVO goodsVO ) {
        //减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goodsVO);
        //生成订单
        return orderService.createOrder(user, goodsVO);
    }
}
