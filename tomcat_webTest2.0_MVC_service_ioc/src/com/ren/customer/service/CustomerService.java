package com.ren.customer.service;

import com.ren.customer.pojo.Customer;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-06 18:22
 * @description:
 **/
public interface CustomerService {
    //获取用户的列表
    List<Customer> getCustomers(String keyValue,int pageNo);
    //由id获取用户的信息
    Customer getCustomerById(int id);
    //进行用户信息的更新
    int update(Customer customer);
    //进行用户的添加
    int add(Customer customer);
    //根据id删除用户
    int delete(int id);
    //获取数据库的总页数 每页5条
    int GetRow(String keyValue);
}
