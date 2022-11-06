package com.ren.customer.controllers;

import com.ren.customer.dao.CustomerDAO;
import com.ren.customer.dao.impl.CustomerImpl;
import com.ren.customer.pojo.Customer;
import com.ren.myssm.myspringmvc.ViewBaseServlet;
import com.ren.myssm.util.StringUtil;
import com.ren.service.CustomerService;
import com.ren.service.impl.CustomerServiceImpl;

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
public class CustomerController{

    private CustomerService customerService = null;

    //进行主页面列表的刷新
    private String index(String opera, String keyValue, Integer pageNo, HttpServletRequest request) {
        if (pageNo == null)
            pageNo = 1;
        HttpSession session = request.getSession();

        //进行搜索事件的判断
        if (!StringUtil.isEmpty(opera) && "search".equals(opera)) {
            //则此时时从表首的查询进入的
            if (StringUtil.isEmpty(keyValue)) {   //判断搜索框中是否为空，若为空则进行一次刷新
                keyValue = "";
            }
            session.setAttribute("keyValue", keyValue);
        } else {
            //此时是从下方的页面按钮进入的
            Object keyValueObj = session.getAttribute("keyValue");
            if (keyValueObj != null) {
                keyValue = (String) keyValueObj;
            } else {
                keyValue = "";
            }
        }

        //将pageNo保存到session作用域
        session.setAttribute("pageNo", pageNo);

        //获取关键字或全部的列表
        List<Customer> customerList = customerService.getCustomers(keyValue, pageNo);
        //保存到session作用域
        session.setAttribute("customerList", customerList);

        int params = customerService.GetRow(keyValue);
        //将params保存到session作用域
        session.setAttribute("pageCount", params);

        //进行跳转
        //super.processTemplate("index",req,resp);
        return "index";
    }

    //进行用户的创建 然后经过customer.do方法跳转到index页面进行刷新
    private String add(String name, String email, String birth, HttpServletRequest request) throws IOException {
        if (!StringUtil.isEmpty(request.getParameter(name)) && !StringUtil.isEmpty(email)) {
            java.sql.Date date = null;
            try {
                date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(birth).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            customerService.add(new Customer(0, name, email, date));
        }

        //重定向到index页面
        //resp.sendRedirect("customer.do");
        return "redirect:customer.do";
    }

    //在index页面点击用户name 携带id信息进入edit页面
    private String edit(Integer id, HttpServletRequest request) throws ServletException, IOException {
        Customer customer = customerService.getCustomerById(id);
        request.setAttribute("customer", customer);
        //super.processTemplate("edit",req,resp);
        return "edit";
    }

    //进行数据的更新 在edit页面表单提交后 跳转到index页面进行刷新
    private String update(Integer id, String name, String email, String birth) throws ServletException, IOException {
        java.sql.Date date = null;
        try {
            date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(birth).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        customerService.update(new Customer(id, name, email, date));

        //重定向到index页面进行数据更新
        //resp.sendRedirect("customer.do");
        return "redirect:customer.do";
    }

    //在index页面点击del后 携带id信息进入delete方法 然后进入index页面进行数据更新
    private String delete(Integer id) throws ServletException, IOException {
        customerService.delete(id);
        //resp.sendRedirect("customer.do");
        return "redirect:customer.do";
    }
}
