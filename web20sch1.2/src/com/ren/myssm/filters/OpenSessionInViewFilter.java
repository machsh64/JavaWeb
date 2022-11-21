package com.ren.myssm.filters;

import com.ren.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.sql.SQLException;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-07 21:52
 * @description:
 **/
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            TransactionManager.beginTrans();
            filterChain.doFilter(servletRequest, servletResponse);
            TransactionManager.commit();
        }catch (Exception e){
            e.printStackTrace();
            try {
                TransactionManager.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
