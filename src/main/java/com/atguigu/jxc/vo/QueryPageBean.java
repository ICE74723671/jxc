package com.atguigu.jxc.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryPageBean implements Serializable {

    private Integer page;//页码
    private Integer rows;//每页记录数
    private String goodsName;//查询条件
}