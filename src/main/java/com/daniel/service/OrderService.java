package com.daniel.service;

import com.daniel.dao.OrderDao;
import com.daniel.domain.Order;
import com.daniel.domain.OrderInfo;
import com.daniel.domain.User;
import com.daniel.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.Date;

/**
 * @Package: com.daniel.service
 * @ClassName: OrderService
 * @Author: daniel
 * @CreateTime: 2021/4/1 20:57
 * @Description:
 */

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 通过用户ID和商品ID来获取对应的订单
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @return 对应的订单
     */
    public Order getOrderByUserIdAndGoodsId(long userId, long goodsId) {
        return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
    }

    /**
     * 秒杀成功后，生成订单
     * @param user 秒杀成功的用户
     * @param goodsVO 秒杀的商品信息
     * @return 生成订单的信息
     */
    @Transactional
    public OrderInfo createOrder (User user, GoodsVO goodsVO) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVO.getId());
        orderInfo.setGoodsName(goodsVO.getGoodsName());
        orderInfo.setGoodsPrice(goodsVO.getGoodsPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());

        long orderId = orderDao.insert(orderInfo);
        Order order = new Order();
        order.setOrderId(orderId);
        order.setGoodsId(goodsVO.getId());
        order.setUserId(user.getId());
        orderDao.insertMiaoshaOrder(order);

        return orderInfo;
    }
}
