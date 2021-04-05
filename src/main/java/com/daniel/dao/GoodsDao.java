package com.daniel.dao;

import com.daniel.domain.MiaoshaGoods;
import com.daniel.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Package: com.daniel.dao
 * @ClassName: GoodsDao
 * @Author: daniel
 * @CreateTime: 2021/4/1 12:45
 * @Description: 对数据库中商品相关操作的方法进行封装
 */

@Mapper
public interface GoodsDao {

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    List<GoodsVO> getGoodsVOList();

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    GoodsVO getGoodsVOByGoodsId(@Param("goodsId")long goodsId );

    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
    int reduceGoodsStock(MiaoshaGoods miaoshaGoods);
}
