package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;

import java.util.List;

/**
 * @description 商品类别
 */

public interface GoodsTypeDao {

    List<GoodsType> selectList();

    List<Integer> goodsType(Integer goodsTypeId);

    Integer updateGoodsTypeState(GoodsType parentGoodsType);

    Integer addGoodsType(String goodsTypeName, Integer pId);

    Integer deleteGoodsType(Integer goodsTypeId);
}
