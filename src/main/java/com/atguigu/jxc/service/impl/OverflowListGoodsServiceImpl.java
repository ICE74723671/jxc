package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.OverflowListGoodsDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.service.OverflowListGoodsService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public ServiceVO save(OverflowList overflowList, String overflowListGoodsStr) {
        overflowListGoodsDao.saveOverflowList(overflowList);
        Integer overflowListId = overflowList.getOverflowListId();
        Gson gson = new Gson();
        JsonArray array = new JsonParser().parse(overflowListGoodsStr).getAsJsonArray();
        List<OverflowListGoods> overflowListGoodsList = new ArrayList<>();
        for(JsonElement jsonElement:array){
            overflowListGoodsList.add(gson.fromJson(jsonElement, OverflowListGoods.class));
        }
        for (OverflowListGoods overflowListGoods:overflowListGoodsList){
            overflowListGoodsDao.saveOverFlowListGoods(overflowListGoods);
            Integer overflowListGoodsId =  overflowListGoods.getOverflowListGoodsId();
            overflowListGoodsDao.setOverflowListId(overflowListId,overflowListGoodsId);
        }

        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }
}
