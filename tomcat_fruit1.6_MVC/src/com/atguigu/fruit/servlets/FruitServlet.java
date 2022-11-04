package com.atguigu.fruit.servlets;

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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-02 23:20
 * @description:
 **/
@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {

    private final FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String operator = request.getParameter("operator");

        if (StringUtil.isEmpty(operator)) {
            operator = "index";
        }

        //利用反射调用对应operator字符的方法名的方法
        Class<?> clazz = this.getClass();
        try {
            Method method = clazz.getDeclaredMethod(operator, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this,request,response);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

/*        //使用switch分配调用的方法
        switch (operator) {
            case "index":
                index(request, response);
                break;
            case "add":
                add(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "update":
                update(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                throw new RuntimeException("operator数据非法!!!");
        }
*/
    }

    //进行页面列表刷新
    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = 1;
        HttpSession session = req.getSession();

        //进行搜索事件的判断，输入框若不为空则查询与输入的数据有关的数据
        String operator = req.getParameter("opera");
        String keyValue = null;
        if (!StringUtil.isEmpty(operator) && "search".equals(operator)) {
            //此时为从搜索框进入的
            keyValue = req.getParameter("keyValue");
            if (StringUtil.isEmpty(keyValue)) {
                keyValue = "";
            }
            session.setAttribute("keyValue", keyValue);
        } else {
            //此时为从下方页面按钮进入的
            String pageNoStr = req.getParameter("pageNo");
            if (!StringUtil.isEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }

            Object keyValueObj = session.getAttribute("keyValue");
            if (keyValueObj != null) {
                keyValue = (String) keyValueObj;
            } else {
                keyValue = "";
            }
        }

        //将pageNo保存到Session作用域
        session.setAttribute("pageNo", pageNo);

        List<Fruit> fruitList = fruitDAO.getFruitList(keyValue, pageNo);   //将pageNo赋值给list
        //保存到session作用域
        session.setAttribute("fruitList", fruitList);

        int rows = fruitDAO.rowQuery(keyValue);  //获取总行数
        int params = (rows + 5 - 1) / 5;   //数据库中的总页数  每页5行数据

        //将params总页数保存到session作用域
        session.setAttribute("pageCount", params);

        super.processTemplate("index", req, resp);
    }


    //用户在add页面 进行创建新对象的操作 跳转到fruit.do方法 进行数据刷新并跳转到index页面
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1，设置网页中获取到的参数编码为utf-8 防止数据乱码

        if (!StringUtil.isEmpty(req.getParameter("fname"))) {
            String fname = req.getParameter("fname");
            String priceStr = req.getParameter("price");
            int price = Integer.parseInt(priceStr);
            String fcountStr = req.getParameter("fcount");
            int fcount = Integer.parseInt(fcountStr);
            String remark = req.getParameter("remark");

            fruitDAO.executeAdd(new Fruit(0, fname, price, fcount, remark));
        }

        resp.sendRedirect("fruit.do");
    }

    //用户进行点击fname携带fid进入更改页面的操作  跳转到edit页面
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if (!StringUtil.isEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            req.setAttribute("fruit", fruit);
            super.processTemplate("edit", req, resp);
        }
    }

    //用户在edit更改页面 进行数据更改刷新的操作 跳转到index方法  进行数据更新 并跳转到index页面
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1，设置网页中设置的编码，防止传入到数据库中为乱码

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
        fruitDAO.executeUpdate(new Fruit(fid, fname, price, fcount, remark));

        //4，资源跳转
//        super.processTemplate("index",req,resp);   //此方法跳转的页面相当于服务器内部跳转 页面内的信息为未进行更新的数据
        //此处需要重定向，重新获取fruitList，然后覆盖到session中，这样index.html页面上显示的数据就是进行过更新的
        resp.sendRedirect("fruit.do");
    }

    //在index页面 进行数据删除的操作 跳转到fruit.do方法 进行数据更新 及跳转到index页面
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if (!StringUtil.isEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            fruitDAO.executeDel(fid);
            //跳转以完成数据的更新
            resp.sendRedirect("fruit.do");
        }
    }
}
