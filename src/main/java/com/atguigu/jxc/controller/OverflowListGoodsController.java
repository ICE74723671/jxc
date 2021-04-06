package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.OverflowListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("overflowListGoods")
public class OverflowListGoodsController {

    @Autowired
    OverflowListGoodsService overflowListGoodsService;

    /**
     * 新增报溢单
     */
    @RequestMapping("save")
    public ServiceVO save(OverflowList overflowList, String overflowListGoodsStr, HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        Integer userId = user.getUserId();
        overflowList.setUserId(userId);
        return overflowListGoodsService.save(overflowList,overflowListGoodsStr);
    }


    /**
     * 报溢单查询
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @PostMapping("list")
    public Map<String,Object> overflowListGoods(String sTime, String eTime){
        Map<String,Object> map = new HashMap<>();
        List<OverflowList> overflowLists = overflowListGoodsService.overflowListGoods(sTime,eTime);
        map.put("rows",overflowLists);
        return map;
    }

    /**
     * 查询报溢单商品信息
     * @param overflowListId 报溢单Id
     * @return
     */
    @PostMapping("goodsList")
    public Map<String,Object> goodsList(Integer overflowListId){
        Map<String,Object> map = new HashMap<>();
        List<OverflowListGoods> overflowListGoods = overflowListGoodsService.goodsList(overflowListId);
        map.put("rows",overflowListGoods);
        return map;
    }

}
