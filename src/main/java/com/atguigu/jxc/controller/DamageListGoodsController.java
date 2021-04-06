package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.DamageListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("damageListGoods")
public class DamageListGoodsController {

    @Autowired
    DamageListGoodsService damageListGoodsService;


    /**
     * 保存报损单
     * @param damageList
     * @param damageListGoodsStr
     * @return
     */
    //http://localhost:8080/damageListGoods/save?damageNumber=BS1605766644460
    @RequestMapping("save")
    public ServiceVO save(DamageList damageList, String damageListGoodsStr, HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        Integer userId = user.getUserId();
        damageList.setUserId(userId);
        return damageListGoodsService.save(damageList,damageListGoodsStr);
    }


    /**
     * 报损单查询
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @PostMapping("list")
    public Map<String,Object> damageListGoods(String sTime,String eTime){
        Map<String,Object> map = new HashMap<>();
        List<DamageList> damageLists = damageListGoodsService.damageListGoods(sTime,eTime);
        map.put("rows",damageLists);
        return map;
    }

    /**
     * 查询报损单商品信息
     * @param damageListId 报损单Id
     * @return
     */
    @PostMapping("goodsList")
    public Map<String,Object> goodsList(Integer damageListId){
        Map<String,Object> map = new HashMap<>();
        List<DamageListGoods> damageListGoods = damageListGoodsService.goodsList(damageListId);
        map.put("rows",damageListGoods);
        return map;
    }

}
