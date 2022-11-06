package com.ren.myssm.myspringmvc;

import com.ren.myssm.util.StringUtil;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-04 15:45
 * @description:
 **/
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private final Map<String, Object> beanMap = new HashMap<>();

    public void init() throws ServletException{
        super.init();
        try {
            //获取document中xml文件的对象
            InputStream is = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(is);

            NodeList beansNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beansNodeList.getLength(); i++) {
                Node beanNode = beansNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {   //判断是否是节点对象
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String beanClass = beanElement.getAttribute("class");
                    Class<?> controllerBeanClass = Class.forName(beanClass);
                    Object beanObj = controllerBeanClass.newInstance();

                    //将与元素对应名称的 名称与对象存入哈希map
                    beanMap.put(beanId, beanObj);
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDoIndex = servletPath.lastIndexOf(".do");
        //获取到跳转时的注解名
        servletPath = servletPath.substring(0, lastDoIndex);
        //根据跳转的注解名 获取跳转的对象
        Object controlBeanObj = beanMap.get(servletPath);

        //从operator的value获取需要调用的方法的名称
        String operator = request.getParameter("operator");
        //如果operator为空则赋值为index 使其跳转到主页进行刷新
        if (StringUtil.isEmpty(operator))
            operator = "index";

        try {
            //1，通过反射直接索引到方法
            Method[] methods = controlBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operator.equals(method.getName())) {
                    //对获取到的方法内部形参进行值传递
                    Parameter[] parameters = method.getParameters();
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        //对 request不可获取到的形参类型进行手动赋值
                        if ("request".equals(parameterName)) {
                            parameterValues[i] = request;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = response;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = request.getSession();
                        } else {
                            //从request获取也就是从html表单中获取形参所需要的数据
                            String parameterValue = request.getParameter(parameterName);
                            Object parameterObj = request.getParameter(parameterName);
                            String name = parameter.getType().getName();
                            //对可能出现的空值导致的空指针异常进行筛选
                            if (parameterObj != null) {
                                //对形参类型的判断以及形参类型的转换
                                if ("java.lang.Integer".equals(name)) {
                                    parameterValues[i] = Integer.parseInt(parameterValue);
                                } else {
                                    parameterValues[i] = parameterValue;
                                }
                            }
                        }
                    }
                    //2，通过反射执行对应operator的方法
                    method.setAccessible(true);
                    Object methodReturnObj = method.invoke(controlBeanObj, parameterValues);
                    //3，进行视图处理  页面的跳转以及刷新
                        String methodReturnStr = (String) methodReturnObj;
                        if (methodReturnStr.startsWith("redirect:")){
                            String redirectStr = methodReturnStr.substring("redirect:".length());
                            response.sendRedirect(redirectStr);
                        }else {
                            super.processTemplate(methodReturnStr,request,response);
                        }
                    }
                }
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("operator值非法");
        }
    }
}
