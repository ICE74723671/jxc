package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;

import java.util.List;

public interface DamageListGoodsService {
    List<DamageList> damageListGoods(String sTime, String eTime);

    List<DamageListGoods> goodsList(Integer damageListId);
}
