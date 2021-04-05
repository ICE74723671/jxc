package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SupplierDao;
import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import com.atguigu.jxc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @description
 */

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierDao supplierDao;

    @Override
    public Map<String,Object> queryAllList(Integer page, Integer rows, String supplierName) {

       Map<String, Object> map = new HashMap<>();

       //查询total总条数
        String total=supplierDao.queryAllTotal();
        map.put("total",total);

        List<Supplier> suppliers = new ArrayList<>();
        if (page ==null || page ==0){
            page=1;
        }

        page=(page-1)*rows;
        System.out.println("page = " + page);
        suppliers=supplierDao.queryAllList(page,rows,supplierName);
        map.put("rows",suppliers);

        return map;
    }

    @Override
    public ServiceVO Save(Supplier supplier) {

        // 用户ID为空时，说明是新增操作，需要先判断用户名是否存在
        if (supplier.getSupplierId() ==null){
            //判断是否有相同的用户名
            Supplier supplier1= supplierDao.queryByName(supplier.getContacts());

            if (supplier1 !=null){
                return new ServiceVO(ErrorCode.ACCOUNT_EXIST_CODE,ErrorCode.ACCOUNT_EXIST_MESS);
            }

            //新增
            supplierDao.insert(supplier);
            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        }else {
            supplierDao.update(supplier);

            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        }

    }

    @Override
    public ServiceVO delete(String ids) {

        if (StringUtil.isEmpty(ids)) {
            return new ServiceVO(ErrorCode.NULL_POINTER_CODE,ErrorCode.NULL_POINTER_MESS);
        }

        List<String> ides = Arrays.asList(ids.split(","));
        supplierDao.delete(ides);
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

}
