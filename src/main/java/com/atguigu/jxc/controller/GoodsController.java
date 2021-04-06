package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.util.StringUtil;
import com.atguigu.jxc.vo.GoodsVo;
import com.atguigu.jxc.vo.QueryPageBean;
import com.google.gson.Gson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 商品信息Controller
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 删除商品
     *
     * @param goodsId
     * @return
     */
    @PostMapping("goods/delete")
    public ServiceVO deleteGoods(Integer goodsId) {
        return goodsService.deleteGoods(goodsId);
    }

    /**
     * 新增或修改商品
     *
     * @param goods
     * @return
     */
    @PostMapping("goods/save")
    public ServiceVO addOrUpdateGoods(Goods goods) {
        return goodsService.addOrUpdateGoods(goods);
    }

    /**
     * 删除分类
     *
     * @param goodsTypeId
     * @return
     */
    @PostMapping("goodsType/delete")
    public ServiceVO deleteGoodsType(Integer goodsTypeId) {
        return goodsService.deleteGoodsType(goodsTypeId);
    }

    /**
     * 新增分类
     *
     * @param goodsTypeName
     * @param pId
     * @return
     */
    @PostMapping("goodsType/save")
    public ServiceVO addGoodsType(String goodsTypeName, Integer pId) {
        return goodsService.addGoodsType(goodsTypeName, pId);
    }

    /**
     * 分页展示商品信息
     *
     * @param goodsTypeId
     * @return
     */
    @PostMapping("goods/list")
    public Map<String, Object> goodsList(String goodsName, Integer goodsTypeId) {
        Map<String, Object> map = new HashMap<>();
        GoodsVo goodsVo = goodsService.goodsList(goodsName, goodsTypeId);
        map.put("total", goodsVo.getTotal());
        map.put("rows", goodsVo.getRows());
        return map;
    }

    /**
     * 查询所有商品单位
     *
     * @return
     */
    @PostMapping("unit/list")
    public Map<String, Object> queryUnits() {
        return goodsService.queryUnits();
    }

    /**
     * 查询商品所有分类
     *
     * @return
     */
    @PostMapping("goodsType/loadGoodsType")
    public String queryCategories() {
        Gson gson = new Gson();
        return gson.toJson(goodsService.queryCategories());
    }


    /**
     * 分页查询商品库存信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param codeOrName 商品编码或名称
     * @param goodsTypeId 商品类别ID
     * @return
     */


    /**
     * 分页查询商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param goodsName 商品名称
     * @param goodsTypeId 商品类别ID
     * @return
     */


    /**
     * 生成商品编码
     *
     * @return
     */
    @RequestMapping("/getCode")
    @RequiresPermissions(value = "商品管理")
    public ServiceVO getCode() {
        return goodsService.getCode();
    }

    /**
     * 添加或修改商品信息
     * @param goods 商品信息实体
     * @return
     */

    /**
     * 删除商品信息
     * @param goodsId 商品ID
     * @return
     */

    /**
     * 分页查询无库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */


    /**
     * 分页查询有库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */


    /**
     * 添加商品期初库存
     * @param goodsId 商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice 成本价
     * @return
     */

    /**
     * 删除商品库存
     * @param goodsId 商品ID
     * @return
     */

    /**
     * 查询库存报警商品信息
     * @return
     */

}
