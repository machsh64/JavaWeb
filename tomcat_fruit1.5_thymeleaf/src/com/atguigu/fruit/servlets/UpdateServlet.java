package com.atguigu.fruit.servlets;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.mysringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-29 23:15
 * @description:
 **/
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    private final FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1，设置网页中设置的编码，防止传入到数据库中为乱码
        req.setCharacterEncoding("UTF-8");

        //2，获取参数
        String fidStr = req.getParameter("fid");
        int fid = Integer.parseInt(fidStr);
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = req.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");

        //3，执行更新
        fruitDAO.executeUpdate(new Fruit(fid,fname,price,fcount,remark));

        //4，资源跳转
//        super.processTemplate("index",req,resp);   //此方法跳转的页面相当于服务器内部跳转 页面内的信息为未进行更新的数据
        //此处需要重定向，重新获取fruitList，然后覆盖到session中，这样index.html页面上显示的数据就是进行过更新的
        resp.sendRedirect("index");
    }
}
