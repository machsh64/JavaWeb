package com.ren.customer.service.impl;

import com.ren.customer.dao.CustomerDAO;
import com.ren.customer.pojo.Customer;
import com.ren.customer.service.CustomerService;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-06 18:21
 * @description:
 **/
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO = null;

    @Override
    public List<Customer> getCustomers(String keyValue, int pageNo) {
        return customerDAO.getCustomers(keyValue, pageNo);
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerByID(id);
    }

    @Override
    public int update(Customer customer) {
        return customerDAO.executeUpdate(customer);
    }

    @Override
    public int add(Customer customer) {
        return customerDAO.executeAdd(customer);
    }

    @Override
    public int delete(int id) {
        return customerDAO.executeDel(id);
    }

    @Override
    public int GetRow(String keyValue) {
        return ((customerDAO.getRow(keyValue) + 5 - 1) / 5);
    }
}
