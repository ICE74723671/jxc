package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.DamageListGoodsDao;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.service.DamageListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageListGoodsServiceImpl implements DamageListGoodsService {

    @Autowired
    DamageListGoodsDao damageListGoodsDao;

    @Override
    public List<DamageList> damageListGoods(String sTime, String eTime) {
        return damageListGoodsDao.damageListGoods(sTime,eTime);
    }

    @Override
    public List<DamageListGoods> goodsList(Integer damageListId) {
        return damageListGoodsDao.goodsList(damageListId);
    }
}
