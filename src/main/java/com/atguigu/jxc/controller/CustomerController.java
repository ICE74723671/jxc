package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * 查询客户列表信息
     *@param page 当前页
     * @param rows 每页显示条数
     * @param customerName 客户名称
     * @return
     */
    @PostMapping("list")
    public Map<String,Object> customerList(Integer page, Integer rows, String customerName){

        List<Customer> customerList = customerService.customerList(page,rows,customerName);
        int size = customerList.size();
        Map<String,Object> map = new HashMap<>();
        map.put("rows",customerList);
        map.put("total",size);
        return map;
    }

    /**
     * 新增或修改客户信息
     * @param customer 客户详情信息
     * @return
     */
    @PostMapping("save")
    public ServiceVO save(Customer customer){
         return customerService.save(customer);
    }

    /**
     * 删除客户信息
     * @param ids 客户id集合
     * @return
     */
    @PostMapping("delete")
    public ServiceVO delete(String ids){
        return customerService.delete(ids);
    }



}
