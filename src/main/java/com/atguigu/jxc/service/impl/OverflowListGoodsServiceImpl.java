package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.OverflowListGoodsDao;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.service.OverflowListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowListGoodsServiceImpl implements OverflowListGoodsService {

    @Autowired
    OverflowListGoodsDao overflowListGoodsDao;


    @Override
    public List<OverflowList> overflowListGoods(String sTime, String eTime) {
        return overflowListGoodsDao.overflowListGoods(sTime,eTime);
    }

    @Override
    public List<OverflowListGoods> goodsList(Integer overflowListId) {
        return overflowListGoodsDao.goodsList(overflowListId);
    }
}
