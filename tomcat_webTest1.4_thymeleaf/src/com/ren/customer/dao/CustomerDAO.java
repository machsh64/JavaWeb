package com.ren.customer.dao;

import com.ren.customer.pojo.Customer;

import java.util.List;

//实现Customer表操作方法的接口
public interface CustomerDAO {

    //获取用户的列表
    List<Customer> getCustomers();
}
