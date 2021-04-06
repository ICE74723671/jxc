package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DamageListGoodsDao {
    List<DamageList> damageListGoods(@Param("sTime") String sTime,@Param("eTime") String eTime);

    List<DamageListGoods> goodsList(@Param("damageListId") Integer damageListId);
}
