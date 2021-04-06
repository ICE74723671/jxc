package com.atguigu.jxc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商品类别
 */
@Data
@NoArgsConstructor
public class GoodsType {

  private Integer goodsTypeId;
  private String goodsTypeName;
  private Integer pId;
  private List<GoodsType> children;
  private Integer goodsTypeState;

  public GoodsType(String goodsTypeName, Integer goodsTypeState, Integer pId) {
    this.goodsTypeName = goodsTypeName;
    this.goodsTypeState = goodsTypeState;
    this.pId = pId;
  }
}
