package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;

import java.util.List;

public interface OverflowListGoodsService {
    List<OverflowList> overflowListGoods(String sTime, String eTime);

    List<OverflowListGoods> goodsList(Integer overflowListId);
}
