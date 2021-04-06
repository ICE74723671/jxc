package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OverflowListGoodsDao {
    List<OverflowList> overflowListGoods(@Param("sTime") String sTime,@Param("eTime") String eTime);

    List<OverflowListGoods> goodsList(@Param("overflowListId") Integer overflowListId);

    void saveOverflowList(OverflowList overflowList);

    void saveOverFlowListGoods(OverflowListGoods overflowListGoods);

    void setOverflowListId(@Param("overflowListId") Integer overflowListId,
                           @Param("overflowListGoodsId") Integer overflowListGoodsId);
}
