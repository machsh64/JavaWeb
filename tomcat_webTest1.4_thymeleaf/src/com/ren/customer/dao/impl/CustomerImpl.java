package com.ren.customer.dao.impl;

import com.ren.customer.dao.CustomerDAO;

import com.ren.customer.pojo.Customer;
import com.ren.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-17 12:20
 * @description:
 **/
public class CustomerImpl extends BaseDAO<Customer> implements CustomerDAO {
    @Override
    public List<Customer> getCustomers() {
        String sql = "SELECT * " +
                "FROM customers";
        return executeQuery(sql);
    }
}
