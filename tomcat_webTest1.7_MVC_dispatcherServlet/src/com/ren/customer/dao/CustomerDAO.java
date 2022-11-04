package com.ren.customer.dao;

import com.ren.customer.pojo.Customer;

import java.util.List;

//实现Customer表操作方法的接口
public interface CustomerDAO {

    //获取用户的列表  或者根据关键字获取用户的列表
    List<Customer> getCustomers(String keyValue,int pageNo);
    //由id获取单个用户的数据
    Customer getCustomerByID(int id);
    //进行用户数据的更新
    int executeUpdate(Customer customer);
    //进行用户的增加
    int executeAdd(Customer customer);
    //根据id进行用户的删除
    int executeDel(int id);
    //获取数据库中的总行数  或获取与关键字有关的行数
    int getRow(String keyValue);
}
