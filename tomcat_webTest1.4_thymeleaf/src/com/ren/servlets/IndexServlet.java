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
        List<Customer> customerList = null;

        String pageNoStr = req.getParameter("pageNo");
        if (!StringUtil.isEmpty(pageNoStr)){
            pageNo = Integer.parseInt(pageNoStr);
        }

        //将pageNo保存到session作用域
        HttpSession session = req.getSession();
        session.setAttribute("pageNo",pageNo);

        //进行搜索事件的判断，输入框若不为空则查询与输入的数据有关的数据
        String operator = req.getParameter("operator");
        if (!StringUtil.isEmpty(operator)){
            customerList = customerDAO.getEspCustomers(operator,pageNo);
        } else {
            customerList = customerDAO.getCustomers(pageNo);   //进行页面选择 每页5列
        }
        //保存到session作用域
        session.setAttribute("customerList",customerList);

        int rows = customerDAO.getRow();   //获取总行数
        int params = (rows+5-1) /5;    //获取总页数

        //将params保存到session作用域
        session.setAttribute("pageCount",params);

        //进行跳转
        super.processTemplate("index",req,resp);
    }
}
