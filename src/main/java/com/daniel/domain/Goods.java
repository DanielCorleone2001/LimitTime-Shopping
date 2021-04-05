package com.daniel.domain;

import lombok.Data;
import lombok.Setter;

/**
 * @Package: com.daniel.domain
 * @ClassName: Goods
 * @Author: daniel
 * @CreateTime: 2021/4/1 12:35
 * @Description: 商品属性，对应数据库中的字段
 */
@Data
@Setter
public class Goods {

    private Long id;

    private String goodsName;

    private String goodsTitle;

    private String goodsImg;

    private String goodsDetail;

    private Double goodsPrice;

    private Integer goodsStock;

}
