package com.atguigu.jxc.vo;

import com.atguigu.jxc.entity.GoodsType;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author Ice on 2021/4/5 in 1:02
 */
@Data
public class GoodsTypeVo {

    private Integer id;
    private String text;
    private String state;
    private String iconCls = "goods-type";
    private Map<String, Object> attributes;
    private List<GoodsTypeVo> children;
}
