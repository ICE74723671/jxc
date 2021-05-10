package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.dao.UnitDao;
import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Unit;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.vo.GoodsTypeVo;
import com.atguigu.jxc.vo.GoodsVo;
import com.sun.corba.se.impl.oa.toa.TOA;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.swing.text.TabableView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private UnitDao unitDao;

    @Autowired
    private GoodsTypeDao goodsTypeDao;

    private Integer amount = 0;

    @Override
    public List<Goods> listAlarm() {
        return goodsDao.listAlarm();
    }

    @Override
    public GoodsVo getNoInventoryQuantity(String nameOrCode) {
        GoodsVo goodsVo = new GoodsVo();
        List<Goods> rows = goodsDao.getNoInventoryQuantity(nameOrCode);
        Integer total = rows.size();
        goodsVo.setTotal(total);
        goodsVo.setRows(rows);
        return goodsVo;
    }

    @Override
    public GoodsVo getHasInventoryQuantity(String nameOrCode) {
        GoodsVo goodsVo = new GoodsVo();
        List<Goods> rows = goodsDao.getHasInventoryQuantity(nameOrCode);
        Integer total = rows.size();
        goodsVo.setTotal(total);
        goodsVo.setRows(rows);
        return goodsVo;
    }

    @Override
    public ServiceVO saveStock(Integer goodsId, Integer inventoryQuantity, double purchasingPrice) {
        try {
            goodsDao.saveStock(goodsId, inventoryQuantity, purchasingPrice);
            return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        } catch (Exception e) {
            return new ServiceVO<>(ErrorCode.REQ_ERROR_CODE, ErrorCode.REQ_ERROR_MESS);
        }
    }

    @Override
    public ServiceVO deleteStock(Integer goodsId) {
        Integer result = goodsDao.deleteStock(goodsId);
        if (result == 0) {
            return new ServiceVO<>(ErrorCode.STORED_ERROR_CODE, ErrorCode.STORED_ERROR_MESS);
        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);

    }

    @Override
    public List<Goods> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        int pages = (page - 1) * rows;
        return goodsDao.listInventory(pages, rows, codeOrName, goodsTypeId);

    }

    @Override
    public ServiceVO getCode() {

        // 获取当前商品最大编码
        String code = goodsDao.getMaxCode();

        // 在现有编码上加1
        Integer intCode = Integer.parseInt(code) + 1;

        // 将编码重新格式化为4位数字符串形式
        String unitCode = intCode.toString();

        for (int i = 4; i > intCode.toString().length(); i--) {

            unitCode = "0" + unitCode;

        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, unitCode);
    }

    @Override
    public Map<String, Object> queryUnits() {
        List<Unit> units = unitDao.queryUnits();
        Map<String, Object> map = new HashMap<>();
        map.put("rows", units);
        return map;
    }

    @Override
    public GoodsVo goodsList(String goodsName, Integer goodsTypeId) {
        GoodsVo goodsVo = new GoodsVo();
        if (goodsTypeId == null || goodsTypeId == 1) {
            //查询一级分类下的商品
            List<Goods> goods = goodsDao.goodsList();
            Integer total = goodsDao.goodsTotal();
            goodsVo.setTotal(total);
            goodsVo.setRows(goods);
        } else if (goodsTypeDao.goodsType(goodsTypeId).size() != 0) {
            //查询二级分类下的商品
            List<Integer> typeIds = goodsTypeDao.goodsType(goodsTypeId);
            typeIds.forEach(typeId -> {
                amount += goodsDao.goodsTotalById(typeId);
            });
            List<Goods> goods = goodsDao.getGoodByIds(typeIds);
            goodsVo.setTotal(amount);
            goodsVo.setRows(goods);
            amount = 0;
        } else {
            //查询三级分类下的商品
            List<Goods> goods = goodsDao.goodsListById(goodsTypeId);
            Integer total = goodsDao.goodsTotalById(goodsTypeId);
            goodsVo.setTotal(total);
            goodsVo.setRows(goods);
        }

        //根据商品名模糊查询
        if (!StringUtils.isEmpty(goodsName)) {
            List<Goods> goods = goodsDao.getGoodsByName(goodsName);
            goodsVo.setRows(goods);
        }

        return goodsVo;
    }

    @Override
    public ServiceVO addGoodsType(String goodsTypeName, Integer pId) {
        int i = goodsTypeDao.addGoodsType(goodsTypeName, pId);
        if (i != 0) {
            return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
        }
        return null;
    }

    @Override
    public ServiceVO deleteGoodsType(Integer goodsTypeId) {
        int i = goodsTypeDao.deleteGoodsType(goodsTypeId);
        if (i != 0) {
            return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
        }
        return null;
    }

    @Override
    public ServiceVO addOrUpdateGoods(Goods goods) {
        Integer goodsId = goods.getGoodsId();
        if (goodsId == null) {
            goodsDao.addGoods(goods);
        } else {
            goodsDao.updateGoods(goods);
        }
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    @Override
    public ServiceVO deleteGoods(Integer goodsId) {
        goodsDao.deleteGoods(goodsId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    @Override
    public List<GoodsTypeVo> queryCategories() {
        // 查出所有分类
        List<GoodsType> goodsTypes = goodsTypeDao.selectList();

        //查出一级分类及其所有子分类
        List<GoodsType> categories = goodsTypes.stream().filter(goodsType ->
                goodsType.getPId() == -1
        ).map(goodsType -> {
            goodsType.setChildren(getChildren(goodsType, goodsTypes));
            return goodsType;
        }).collect(Collectors.toList());

        return queryCategoriesCast(categories);
    }

    //查询分类下的子分类
    private List<GoodsType> getChildren(GoodsType root, List<GoodsType> all) {

        return all.stream().filter(goodsType -> goodsType.getPId() == root.getGoodsTypeId())
                .map(goodsType -> {
                    //递归查询子菜单
                    goodsType.setChildren(getChildren(goodsType, all));
                    return goodsType;
                }).collect(Collectors.toList());
    }


    //转换返回类型为自定义vo
    private List<GoodsTypeVo> queryCategoriesCast(List<GoodsType> goodsTypes) {

        return goodsTypes.stream().map(goodsType -> {
            GoodsTypeVo goodsTypeVo = new GoodsTypeVo();
            goodsTypeVo.setId(goodsType.getGoodsTypeId());
            goodsTypeVo.setText(goodsType.getGoodsTypeName());
            if (!CollectionUtils.isEmpty(goodsType.getChildren())) {
                goodsTypeVo.setState("closed");
            } else {
                goodsTypeVo.setState("open");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("state", goodsType.getGoodsTypeState());
            goodsTypeVo.setAttributes(map);
            goodsTypeVo.setChildren(this.queryCategoriesCast(goodsType.getChildren()));
            return goodsTypeVo;
        }).collect(Collectors.toList());
    }
}
