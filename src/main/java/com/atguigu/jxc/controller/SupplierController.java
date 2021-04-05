package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

/**
 * @description  供应商管理系统
 */

@Controller
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;


    //分页查询
    @PostMapping("list")
    @ResponseBody
    public Map<String, Object> queryAllList(Integer page, Integer rows, String supplierName){

        return supplierService.queryAllList(page,rows,supplierName);
    }


    //新增和修改
    @PostMapping("save")
    @ResponseBody
    public ServiceVO queryUpdateOrSave(Supplier supplier){
        return supplierService.Save(supplier);
    }

    @PostMapping("delete")
    @ResponseBody
    public ServiceVO delete(String ids){
        return supplierService.delete(ids);
    }






}
