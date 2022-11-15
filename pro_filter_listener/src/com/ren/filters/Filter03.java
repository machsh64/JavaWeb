package com.ren.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-07 20:35
 * @description:
 **/
@WebFilter("*.do")
public class Filter03 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("C 01-1 ...");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("C 01_2 ...");
    }

    @Override
    public void destroy() {

    }
}
