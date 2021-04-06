package com.atguigu.jxc.vo;

import com.atguigu.jxc.entity.Goods;
import lombok.Data;

import java.util.List;

/**
 * description:
 *
 * @author Ice on 2021/4/5 in 17:44
 */
@Data
public class GoodsVo {

    private Integer total;
    private List<Goods> rows;
}
