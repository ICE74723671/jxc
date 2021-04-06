package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public ServiceVO getCode() {

        // 获取当前商品最大编码
        String code = goodsDao.getMaxCode();

        // 在现有编码上加1
        Integer intCode = Integer.parseInt(code) + 1;

        // 将编码重新格式化为4位数字符串形式
        String unitCode = intCode.toString();

        for(int i = 4;i > intCode.toString().length();i--){

            unitCode = "0"+unitCode;

        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, unitCode);
    }

    @Override
    public List<Goods> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        int pages = (page - 1)*rows;
         return goodsDao.listInventory(pages,rows,codeOrName,goodsTypeId);

    }

    @Override
    public List<Goods> goodsList(Integer page, Integer rows, String goodsName, Integer goodsTypeId) {
        return goodsDao.goodsList(page,rows,goodsName,goodsTypeId);
    }

    @Override
    public List<Goods> listAlarm() {
        return goodsDao.listAlarm();
    }


}
