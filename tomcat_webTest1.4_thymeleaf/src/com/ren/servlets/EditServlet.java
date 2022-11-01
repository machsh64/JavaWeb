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

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-30 16:38
 * @description:
 **/
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {

    private final CustomerDAO customerDAO = new CustomerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if(!StringUtil.isEmpty(idStr)){
            int id = Integer.parseInt(idStr);
            Customer customer = customerDAO.getCustomerByID(id);
            req.setAttribute("customer",customer);
            super.processTemplate("edit",req,resp);
        }
    }
}
