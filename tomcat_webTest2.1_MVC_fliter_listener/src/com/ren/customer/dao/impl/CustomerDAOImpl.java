package com.ren.customer.dao.impl;

import com.ren.customer.dao.CustomerDAO;

import com.ren.customer.pojo.Customer;
import com.ren.myssm.basedao.BaseDAO;

import javax.print.DocFlavor;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-17 12:20
 * @description:
 **/
public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO {
    //获取用户数据的列表
    @Override
    public List<Customer> getCustomers(String keyValue, int pageNo){
        String sql = "SELECT * " +
                "FROM customers " +
                "WHERE name LIKE ? " +
                "LIMIT ?,5";
        return executeQuery(sql, "%"+keyValue+"%", (pageNo - 1) * 5);
    }

    //由id获取用户数据
    @Override
    public Customer getCustomerByID(int id){
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

    //获取数据库总行数  或获取与关键字有关的行数
    @Override
    public int getRow(String keyValue) {
        String sql = "SELECT COUNT(id) " +
                "FROM customers " +
                "WHERE name LIKE ?";
        return ((Long) getValue(sql, "%"+keyValue+"%")).intValue();
    }
}
