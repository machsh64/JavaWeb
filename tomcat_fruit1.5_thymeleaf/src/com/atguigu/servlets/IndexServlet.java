package com.atguigu.servlets;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.mysringmvc.ViewBaseServlet;
import com.atguigu.myssm.util.StringUtil;

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
 * @create: 2022-10-27 23:50
 * @description:
 **/
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    private final FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = 1;
        HttpSession session = req.getSession();

        //进行搜索事件的判断，输入框若不为空则查询与输入的数据有关的数据
        String operator = req.getParameter("operator");
        String keyValue = null;
        if (!StringUtil.isEmpty(operator) && "search".equals(operator)){
            //此时为从搜索框进入的
            keyValue = req.getParameter("keyValue");
            if (StringUtil.isEmpty(keyValue)){
                keyValue = "";
            }
            session.setAttribute("keyValue",keyValue);
        } else {
            //此时为从下方页面按钮进入的
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

        //将pageNo保存到Session作用域
        session.setAttribute("pageNo",pageNo);

        List<Fruit> fruitList = fruitDAO.getFruitList(keyValue,pageNo);   //将pageNo赋值给list
        //保存到session作用域
        session.setAttribute("fruitList",fruitList);

        int rows = fruitDAO.rowQuery(keyValue);  //获取总行数
        int params = (rows+5-1) /5;   //数据库中的总页数  每页5行数据

        //将params总页数保存到session作用域
        session.setAttribute("pageCount",params);

        super.processTemplate("index",req,resp);
    }
}
