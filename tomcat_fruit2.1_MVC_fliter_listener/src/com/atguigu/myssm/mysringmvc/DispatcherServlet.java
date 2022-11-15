package com.atguigu.myssm.mysringmvc;

import com.atguigu.myssm.ioc.BeanFactory;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-03 19:00
 * @description:
 **/
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory = null;

    public void init() throws ServletException {
        super.init();
        //之前是再次处主动创建IOC容器的
        //下载优化为从application作用域去获取
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if (beanFactoryObj != null) {
            beanFactory = (BeanFactory) beanFactoryObj;        //beanFactory在ContextLoaderListener中实例化完成并保存在了ServletContext域中
        }else {
            throw new DispatcherServletException("IOC容器获取失败!");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
     /*   //1，设置编码
        request.setCharacterEncoding("utf-8");   //在拦截器里已经进行了编码设置
*/
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDoIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastDoIndex);

        Object controllerBeanObj = beanFactory.getBean(servletPath);   //根据请求获取由applicationContext文件中Class.ForName().newInstance()得来的对象

        String operator = request.getParameter("operator");
        if (StringUtil.isEmpty(operator)) {
            operator = "index";
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operator.equals(method.getName())) {
                    //1，统一获取请求参数
                    //获取一个方法中所有的形参数据组成的数组
                    Parameter[] parameters = method.getParameters();
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        //对形参数据进行String类型的转换 得到形参的内容
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        //对形参内容的初步判断，若为以下三个无法从request中获取到的数据，则在此处手动给予内容
                        if ("request".equals(parameterName)) {
                            parameterValues[i] = request;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = response;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = request.getSession();
                        } else {
                            //从request也就是html文件中获取形参所需要的内容
                            String parameterValue = request.getParameter(parameterName);
                            Object parameterObj = request.getParameter(parameterName);
                            String name = parameter.getType().getName();
                            //对可能出现的空值进行筛选
                            if (parameterObj != null) {
                                //对形参类型的判断以及类型的转化
                                if ("java.lang.Integer".equals(name)) {
                                    parameterValues[i] = Integer.parseInt(parameterValue);
                                } else {
                                    parameterValues[i] = parameterValue;
                                }
                            }
                        }
                    }
                    //2，利用反射调用对应operator字符的方法名的方法
                    method.setAccessible(true);
                    Object methodReturnObj = method.invoke(controllerBeanObj, parameterValues);
                    //3，进行视图处理
                    String methodReturnStr = (String) methodReturnObj;
                    if (methodReturnStr.startsWith("redirect:")) {
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    } else {
                        super.processTemplate(methodReturnStr, request, response);
                    }
                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet 出错了");
        }
    }
}
