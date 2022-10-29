package com.ren.customer.dao.impl;

import com.ren.customer.dao.CustomerDAO;
import com.ren.customer.pojo.Customer;
import com.ren.customer.dao.base.BaseDAO;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-17 12:20
 * @description:
 **/
public class CustomerImpl extends BaseDAO<Customer> implements CustomerDAO {

    //实例化实现类的时候，自动进行数据库的连接
    private Connection conn;
    {
        try {
            conn = getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //释放资源
    //！注  所有操作方法中仅释放了方法内涉及到的资源，所有调用操作最后应该使用该方法进行统一资源释放
    @Override
    public void release() {

        try {
            DbUtils.closeQuietly(conn);
        } finally {
            System.out.println("\n资源释放完毕...");
        }
    }

    @Override
    public int add(Customer customer) {
        String sql = "INSERT customers(name,email,birth) " +
                "VALUES(?,?,?)";
        return update(conn, sql, customer.getName(), customer.getEmail(), customer.getBirth());
    }

    @Override
    public int update(Customer customer) {
        String sql = "UPDATE customers " +
                "SET name = ?,email = ?,birth = ? " +
                "WHERE id = ?";
        return update(conn,sql, customer.getName(), customer.getEmail(), customer.getBirth(), customer.getId());
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM customers " +
                "WHERE id = ?";
        return update(conn,sql, id);
    }

    @Override
    public Customer getInstance(int id) {
        String sql = "SELECT id,name,email,birth " +
                "FROM customers " +
                "WHERE id = ?";
        return getInstance(conn,sql, id);
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT id,name,email,birth " +
                "FROM customers";
        return getInstances(conn,sql);
    }

    @Override
    public long getCount() {
        String sql = "SELECT COUNT(id) " +
                "FROM customers";
        return (long) getValue(conn,sql);
    }

    @Override
    public Date getMAXBirth() {
        String sql = "SELECT MIN(birth) " +
                "FROM customers";
        return (Date) getValue(conn,sql);
    }
}
