package com.daniel.vo;

import com.daniel.domain.Goods;
import lombok.Data;

import java.util.Date;

/**
 * @Package: com.daniel.vo
 * @ClassName: GoodsVO
 * @Author: daniel
 * @CreateTime: 2021/4/1 12:58
 * @Description:
 */

@Data
public class GoodsVO extends Goods {

    private Double miaoshaPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;
}
