package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.DamageListGoodsDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.service.DamageListGoodsService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * 保存报损单
     * @param damageList
     * @param damageListGoodsStr
     * @return
     */
    @Override
    public ServiceVO save(DamageList damageList, String damageListGoodsStr) {

        damageListGoodsDao.saveDamageList(damageList);
        //获取主键damage_list_id
        Integer damageListId = damageList.getDamageListId();
        Gson gson = new Gson();
        JsonArray array = new JsonParser().parse(damageListGoodsStr).getAsJsonArray();
        List<DamageListGoods> damageListGoodsList = new ArrayList<>();
        for(JsonElement jsonElement:array){
            damageListGoodsList.add(gson.fromJson(jsonElement, DamageListGoods.class));
        }

        for(DamageListGoods damageListGoods:damageListGoodsList){
            damageListGoodsDao.saveDamageListGoods(damageListGoods);
            Integer damageListGoodsId = damageListGoods.getDamageListGoodsId();
            damageListGoodsDao.setDamageListGoodsId(damageListId,damageListGoodsId);
        }

        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }
}
