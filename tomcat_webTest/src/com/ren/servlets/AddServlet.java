package com.ren.servlets;

import com.ren.customer.dao.CustomerDAO;
import com.ren.customer.dao.impl.CustomerImpl;
import com.ren.customer.pojo.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-25 17:13
 * @description:
 **/
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("cust_name");
        String email = req.getParameter("cust_email");
        String birth = req.getParameter("cust_birth");
        java.sql.Date date = null;
        try {
             date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(birth).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer(0,name,email,date);
        CustomerDAO imp = new CustomerImpl();
        imp.add(customer);
        System.out.println("上传成功");
        //进行资源释放
        imp.release();
    }
}
