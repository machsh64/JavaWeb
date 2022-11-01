package com.ren.servlets;

import com.ren.customer.dao.CustomerDAO;
import com.ren.customer.dao.impl.CustomerImpl;
import com.ren.customer.pojo.Customer;
import com.ren.myssm.myspringmvc.ViewBaseServlet;
import com.ren.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-30 17:39
 * @description:
 **/
@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {

    private final CustomerDAO customerDAO = new CustomerImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if (!StringUtil.isEmpty(req.getParameter("name")) && !StringUtil.isEmpty(req.getParameter("email"))) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String birthStr = req.getParameter("birth");
            java.sql.Date date = null;
            try {
                date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthStr).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Customer customer = new Customer(0, name, email, date);
            customerDAO.executeAdd(customer);
        }

        //重定向到index页面
        resp.sendRedirect("index");
    }
}
