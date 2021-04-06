package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户管理
 *
 */
public interface CustomerDao {


    List<Customer> customerList(@Param("page") Integer page, @Param("rows") Integer rows, @Param("customerName") String customerName);

    void save(Customer customer);

    void delete(@Param("ides")List<String> ides);

    void update(Customer customer);

}
