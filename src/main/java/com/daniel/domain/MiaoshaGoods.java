package com.daniel.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Package: com.daniel.domain
 * @ClassName: MiaoshaGoods
 * @Author: daniel
 * @CreateTime: 2021/4/1 12:37
 * @Description: 进行秒杀活动的商品，对应数据库中的字段
 */
@Data
public class MiaoshaGoods {

    private Long id;

    private Long goodsId;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;
}
