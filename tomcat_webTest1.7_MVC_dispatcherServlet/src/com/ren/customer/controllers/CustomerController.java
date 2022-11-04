package com.ren.customer.controllers;

import com.ren.customer.dao.CustomerDAO;
import com.ren.customer.dao.impl.CustomerImpl;
import com.ren.customer.pojo.Customer;
import com.ren.myssm.myspringmvc.ViewBaseServlet;
import com.ren.myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-03 14:50
 * @description:
 **/
public class CustomerController extends ViewBaseServlet {

    private final CustomerDAO customerDAO = new CustomerImpl();

    public void setServletContext(ServletContext servletContext) throws ServletException {
        super.init(servletContext);
    }

    //进行主页面列表的刷新
    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = 1;
        HttpSession session = req.getSession();

        //进行搜索事件的判断
        String opera = req.getParameter("opera");
        String keyValue = null;
        if (!StringUtil.isEmpty(opera) && "search".equals(opera)){
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

    //进行用户的创建 然后经过customer.do方法跳转到index页面进行刷新
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
        resp.sendRedirect("customer.do");
    }

    //在index页面点击用户name 携带id信息进入edit页面
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if(!StringUtil.isEmpty(idStr)){
            int id = Integer.parseInt(idStr);
            Customer customer = customerDAO.getCustomerByID(id);
            req.setAttribute("customer",customer);
            super.processTemplate("edit",req,resp);
        }
    }

    //进行数据的更新 在edit页面表单提交后 跳转到index页面进行刷新
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String birthStr = req.getParameter("birth");
        java.sql.Date date = null;
        try {
            date = new java .sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Customer customer = new Customer(id,name,email,date);
        customerDAO.executeUpdate(customer);

        //重定向到index页面进行数据更新
        resp.sendRedirect("customer.do");
    }

    //在index页面点击del后 携带id信息进入delete方法 然后进入index页面进行数据更新
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (!StringUtil.isEmpty(idStr)){
            int id = Integer.parseInt(idStr);
            customerDAO.executeDel(id);

            resp.sendRedirect("customer.do");
        }
    }
}
