package com.daniel.service;

import com.daniel.dao.GoodsDao;
import com.daniel.domain.Goods;
import com.daniel.domain.MiaoshaGoods;
import com.daniel.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.daniel.service
 * @ClassName: GoodsService
 * @Author: daniel
 * @CreateTime: 2021/4/1 20:44
 * @Description:
 */

@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 获取秒杀商品的属性
     * @return 秒杀商品的信息
     */
    public List<GoodsVO> getGoodsVOList() {return goodsDao.getGoodsVOList();}

    /**
     * 通过商品ID来获取到对应的秒杀商品的信息
     * @param goodsId 需要进行查询的商品的ID
     * @return 商品的秒杀信息
     */
    public GoodsVO getGoodsVObyGoodsId(long goodsId) {
        return goodsDao.getGoodsVOByGoodsId(goodsId);
    }

    /**
     * 减少库存
     * @param goodsVO 需要进行减少库存的商品信息
     */
    public void reduceStock (GoodsVO goodsVO ) {
        MiaoshaGoods goods = new MiaoshaGoods();
        goods.setGoodsId(goodsVO.getId());
        goodsDao.reduceGoodsStock(goods);

    }
}
