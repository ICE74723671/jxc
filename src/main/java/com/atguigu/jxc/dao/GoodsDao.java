package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.vo.GoodsTypeVo;
import com.atguigu.jxc.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 商品信息
 */
public interface GoodsDao {

    String getMaxCode();

    List<Goods> goodsList();

    Integer goodsTotal();

    List<Goods> goodsListById(Integer goodsTypeId);

    Integer goodsTotalById(Integer goodsTypeId);

    List<Goods> getGoodByIds(List<Integer> ids);

    Integer addGoods(Goods goods);

    Integer updateGoods(Goods goods);

    Integer deleteGoods(Integer goodsId);

    List<Goods> getGoodsByName(String value);

    List<Goods> listAlarm();

    List<Goods> listInventory(@Param("pages") Integer pages, @Param("rows") Integer rows, @Param("codeOrName") String codeOrName, @Param("goodsTypeId") Integer goodsTypeId);
}
