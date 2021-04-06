package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 商品信息
 */
public interface GoodsDao {


    String getMaxCode();


    List<Goods> listInventory(@Param("pages") Integer pages, @Param("rows") Integer rows, @Param("codeOrName") String codeOrName, @Param("goodsTypeId") Integer goodsTypeId);


    List<Goods> goodsList(@Param("pages") Integer pages, @Param("rows") Integer rows, @Param("goodsName") String goodsName, @Param("goodsTypeId") Integer goodsTypeId);

    List<Goods> listAlarm();

}
