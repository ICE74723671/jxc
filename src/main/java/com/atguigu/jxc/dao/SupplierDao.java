package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SupplierDao {

    //查询总条数
    String queryAllTotal();

    //查询供应商
    List<Supplier> queryAllList(
            @Param("page") Integer page,
            @Param("rows") Integer rows,
            @Param("supplierName")String supplierName);


    void insert(Supplier supplier);

    void update(Supplier supplier);

    Supplier queryByName(String contacts);

    void delete(@Param("ides") List<String> ides);

}


