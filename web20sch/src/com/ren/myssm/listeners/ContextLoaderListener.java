package com.ren.myssm.listeners;

import com.ren.myssm.ioc.BeanFactory;
import com.ren.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-08 18:55
 * @description:
 **/
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //在此处进行DispatcherServlet中beanFactory中 beanFactory的赋值，beanFactory负责进行DAO层与Impl层组装工作
        System.out.println("servletContext start...");
        //1,获取ServletContext对象
        ServletContext application = servletContextEvent.getServletContext();
        //2,获取上下文初始化参数
        String path = application.getInitParameter("contextConfigLocation");   //获取不到xml文件中的Context-value信息
        //3,创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);  //导致path值为空，此处报错
        //4,将IOC容器保存在application作用域中
        application.setAttribute("beanFactory", beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("servletContext destroy...");
       /* try {
            while (DriverManager.getDrivers().hasMoreElements()) {
                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            }
            System.out.println("jdbc Driver close");
            AbandonedConnectionCleanupThread.shutdown();
            System.out.println("clean thread success");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

}
