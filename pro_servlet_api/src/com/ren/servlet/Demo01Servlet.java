package com.ren.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-05 18:03
 * @description:
 **/
/*@WebServlet(urlPatterns = {"/demo01"},
        initParams = {
                @WebInitParam(name = "qieuli", value = "sakura")
        })*/
public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();

        String 初音 = config.getInitParameter("hacune");
        System.out.println(初音);
        String qieuli = config.getInitParameter("qieuli");
        System.out.println(qieuli);

    }
}
