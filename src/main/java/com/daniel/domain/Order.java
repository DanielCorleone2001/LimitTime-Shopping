package com.daniel.domain;

import lombok.Data;

/**
 * @Package: com.daniel.domain
 * @ClassName: Order
 * @Author: daniel
 * @CreateTime: 2021/4/1 12:44
 * @Description: 生成的秒杀订单
 */

@Data
public class Order {

    private Long id;

    private Long userId;

    private Long  orderId;

    private Long goodsId;
}
