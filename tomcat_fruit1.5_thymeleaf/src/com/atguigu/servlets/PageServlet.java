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
 * @create: 2022-10-30 20:13
 * @description:
 **/
@WebServlet("/page.do")
public class PageServlet extends ViewBaseServlet {

    private final FruitDAO fruitDAO = new FruitDAOImpl();
    private final IndexServlet indexServlet = new IndexServlet();
    int page = 1;  //设置初始页面
    List<Fruit> fruitList = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long rows = fruitDAO.rowQuery();
        long params = (rows / 5) + 1;   //数据库中的总页数  每页5行数据

        String paramStr = req.getParameter("param");  //判断动作是上一页或下一页  上一页为0 下一页为1
        if (!StringUtil.isEmpty(paramStr)){
            int param = Integer.parseInt(paramStr);
            if (param == 0 && page > 1){
                page--;
            }else if (param == 1 && page < params) {
                page++;
            }
        }

        fruitList = fruitDAO.getFruitList(page);
        //保存到session作用域
        HttpSession session = req.getSession();
        session.setAttribute("fruitList", fruitList);

        super.processTemplate("index", req, resp);
    }
}
