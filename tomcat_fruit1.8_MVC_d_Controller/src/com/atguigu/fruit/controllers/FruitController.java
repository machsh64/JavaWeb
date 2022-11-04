package com.atguigu.fruit.controllers;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-02 23:20
 * @description:
 **/
public class FruitController {

    private final FruitDAO fruitDAO = new FruitDAOImpl();

    //进行页面列表刷新
    private String index(String opera,String keyValue,Integer pageNo,HttpServletRequest request) {
        if (pageNo == null){
            pageNo = 1;
        }
        HttpSession session = request.getSession();

        //进行搜索事件的判断，输入框若不为空则查询与输入的数据有关的数据
        if (!StringUtil.isEmpty(opera) && "search".equals(opera)) {
            //此时为从搜索框进入的
            if (StringUtil.isEmpty(keyValue)) {
                keyValue = "";
            }
            session.setAttribute("keyValue", keyValue);
        } else {
            //此时为从下方页面按钮进入的
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

        //super.processTemplate("index", req, resp);
        return "index";
    }


    //用户在add页面 进行创建新对象的操作 跳转到fruit.do方法 进行数据刷新并跳转到index页面
    private String add(String fname,Integer price,Integer fcount,String remark) {

            fruitDAO.executeAdd(new Fruit(0, fname, price, fcount, remark));

        //resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    //用户进行点击fname携带fid进入更改页面的操作  跳转到edit页面
    private String edit(Integer fid,HttpServletRequest request){
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit", fruit);

            //super.processTemplate("edit", req, resp);

            return "edit";
    }

    //用户在edit更改页面 进行数据更改刷新的操作 跳转到index方法  进行数据更新 并跳转到index页面
    private String update(Integer fid,String fname,Integer price,Integer fcount,String remark){
        //3，执行更新
        fruitDAO.executeUpdate(new Fruit(fid, fname, price, fcount, remark));

        //4，资源跳转
//        super.processTemplate("index",req,resp);   //此方法跳转的页面相当于服务器内部跳转 页面内的信息为未进行更新的数据
        //此处需要重定向，重新获取fruitList，然后覆盖到session中，这样index.html页面上显示的数据就是进行过更新的
        //resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    //在index页面 进行数据删除的操作 跳转到fruit.do方法 进行数据更新 及跳转到index页面
    private String delete(Integer fid) throws ServletException, IOException {
            fruitDAO.executeDel(fid);
            //跳转以完成数据的更新
            //resp.sendRedirect("fruit.do");
            return "redirect:fruit.do";
    }
}
