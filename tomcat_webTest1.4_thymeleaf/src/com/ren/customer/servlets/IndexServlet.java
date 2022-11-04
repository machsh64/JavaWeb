package com.ren.customer.servlets;

import com.ren.customer.dao.CustomerDAO;
import com.ren.customer.dao.impl.CustomerImpl;
import com.ren.customer.pojo.Customer;
import com.ren.myssm.myspringmvc.ViewBaseServlet;
import com.ren.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-28 18:08
 * @description:
 **/
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    private final CustomerDAO customerDAO = new CustomerImpl();

    //查询的表单是post方式，但查询的操作与列表遍历的操作大同小异，此处进行调用
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = 1;
        HttpSession session = req.getSession();

        //进行搜索事件的判断
        String operator = req.getParameter("operator");
        String keyValue = null;
        if (!StringUtil.isEmpty(operator) && "search".equals(operator)){
            //则此时时从表首的查询进入的
            keyValue = req.getParameter("keyValue");
            if (StringUtil.isEmpty(keyValue)){   //判断搜索框中是否为空，若为空则进行一次刷新
                keyValue = "";
            }
            session.setAttribute("keyValue",keyValue);
        } else {
            //此时是从下方的页面按钮进入的
            String pageNoStr = req.getParameter("pageNo");
            if (!StringUtil.isEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }

            Object keyValueObj = session.getAttribute("keyValue");
            if (keyValueObj != null){
                keyValue = (String) keyValueObj;
            }else {
                keyValue = "";
            }
        }

        //将pageNo保存到session作用域
        session.setAttribute("pageNo",pageNo);

        //获取关键字或全部的列表
        List<Customer> customerList = customerDAO.getCustomers(keyValue, pageNo);
        //保存到session作用域
        session.setAttribute("customerList",customerList);

        int rows = customerDAO.getRow(keyValue);   //获取总行数
        int params = (rows+5-1) /5;    //获取总页数

        //将params保存到session作用域
        session.setAttribute("pageCount",params);

        //进行跳转
        super.processTemplate("index",req,resp);
    }
}
