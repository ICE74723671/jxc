package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.vo.GoodsTypeVo;
import com.atguigu.jxc.vo.GoodsVo;
import com.atguigu.jxc.vo.QueryPageBean;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    ServiceVO getCode();

    List<GoodsTypeVo> queryCategories();

    Map<String, Object> queryUnits();

    GoodsVo goodsList(String goodsName, Integer goodsTypeId);

    ServiceVO addGoodsType(String goodsTypeName, Integer pId);

    ServiceVO deleteGoodsType(Integer goodsTypeId);

    ServiceVO addOrUpdateGoods(Goods goods);

    ServiceVO deleteGoods(Integer goodsId);

    List<Goods> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);

    List<Goods> listAlarm();
}
