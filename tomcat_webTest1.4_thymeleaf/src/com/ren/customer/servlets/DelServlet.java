package com.ren.customer.servlets;

import com.ren.customer.dao.CustomerDAO;
import com.ren.customer.dao.impl.CustomerImpl;
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
 * @create: 2022-10-30 19:09
 * @description:
 **/
@WebServlet("/delete.do")
public class DelServlet extends ViewBaseServlet {

    private final CustomerDAO customerDAO = new CustomerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (!StringUtil.isEmpty(idStr)){
            int id = Integer.parseInt(idStr);
            customerDAO.executeDel(id);

            resp.sendRedirect("index");
        }
    }
}
