package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Goods;

import java.util.List;

public interface GoodsService {


    ServiceVO getCode();


    List<Goods> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);


    List<Goods> goodsList(Integer page, Integer rows, String goodsName, Integer goodsTypeId);

    List<Goods> listAlarm();

}
