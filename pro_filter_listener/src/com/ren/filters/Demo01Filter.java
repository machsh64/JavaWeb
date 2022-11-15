package com.ren.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-07 20:26
 * @description:
 **/
//@WebFilter("/demo01.do")
public class Demo01Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter 01-1 ...");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("filter 01_2 ...");
    }

    @Override
    public void destroy() {

    }
}
