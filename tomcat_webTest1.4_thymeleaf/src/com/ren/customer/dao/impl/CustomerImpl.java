package com.ren.customer.dao.impl;

import com.ren.customer.dao.CustomerDAO;

import com.ren.customer.pojo.Customer;
import com.ren.myssm.basedao.BaseDAO;

import javax.print.DocFlavor;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-17 12:20
 * @description:
 **/
public class CustomerImpl extends BaseDAO<Customer> implements CustomerDAO {
    //获取用户数据的列表
    @Override
    public List<Customer> getCustomers(int pageNo) {
        String sql = "SELECT * " +
                "FROM customers " +
                "LIMIT ?,?";
        return executeQuery(sql, (pageNo - 1) * 5, 5);
    }

    //进行关键字查询用户列表
    @Override
    public List<Customer> getEspCustomers(String Str, int pageNo) {
        String sql = "SELECT * " +
                "FROM customers " +
                "WHERE name LIKE '%?%' " +
                "LIMIT ?,?";
        return executeQuery(sql, Str, (pageNo - 1) * 5, 5);
    }

    //由id获取用户数据
    @Override
    public Customer getCustomerByID(int id) {
        String sql = "SELECT * " +
                "FROM customers " +
                "WHERE id = ?";
        return load(sql, id);
    }

    //进行用户数据的更新
    @Override
    public int executeUpdate(Customer customer) {
        String sql = "UPDATE customers " +
                "SET name = ? , email = ? , birth = ? " +
                "WHERE id = ?";
        return executeUpdate(sql, customer.getName(), customer.getEmail(), customer.getBirth(), customer.getId());
    }

    //进行用户的增加
    @Override
    public int executeAdd(Customer customer) {
        String sql = "INSERT INTO customers(name,email,birth) " +
                "VALUES(?,?,?)";
        return executeUpdate(sql, customer.getName(), customer.getEmail(), customer.getBirth());
    }

    //进行用户的删除
    @Override
    public int executeDel(int id) {
        String sql = "DELETE FROM customers " +
                "WHERE id = ?";
        return executeUpdate(sql, id);
    }

    //获取数据库总行数
    @Override
    public int getRow() {
        String sql = "SELECT COUNT(id) " +
                "FROM customers";
        return ((Long) getValue(sql)).intValue();
    }
}
