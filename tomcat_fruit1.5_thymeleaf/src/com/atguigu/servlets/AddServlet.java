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
import java.io.IOException;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-30 02:17
 * @description:
 **/
@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {

    private final FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if (!StringUtil.isEmpty(req.getParameter("fname"))) {
            String fname = req.getParameter("fname");
            String priceStr = req.getParameter("price");
            int price = Integer.parseInt(priceStr);
            String fcountStr = req.getParameter("fcount");
            int fcount = Integer.parseInt(fcountStr);
            String remark = req.getParameter("remark");

            fruitDAO.executeAdd(new Fruit(0, fname, price, fcount, remark));
        }

        resp.sendRedirect("index");
    }
}
