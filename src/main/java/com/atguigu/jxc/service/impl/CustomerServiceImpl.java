package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.CustomerDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public List<Customer> customerList(Integer page, Integer rows, String customerName) {

        return customerDao.customerList(page,rows,customerName);
    }

    @Override
    public ServiceVO<Customer> save(Customer customer) {
        if(customer.getCustomerId() != null){
            customerDao.update(customer);
        }else {
            customerDao.save(customer);
        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @Override
    public ServiceVO delete(String ids) {
        List<String> ides = Arrays.asList(ids.split(","));
        System.out.println("ides = " + ides);
        customerDao.delete(ides);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }
}
