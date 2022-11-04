package com.atguigu.myssm.mysringmvc;

import com.atguigu.myssm.util.StringUtil;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-03 19:00
 * @description:
 **/
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private final Map<String,Object> beanMap = new HashMap<>();

    public void init(){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //1，创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2，创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //3，创建Document对象
            Document document = documentBuilder.parse(is);
            //4，获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {   //判断是否是元素节点
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Class<?> controllerBeanClass = Class.forName(className);
                    Object beanObj = controllerBeanClass.newInstance();

                    //获取Controller中的servletContext方法 进行解决Controller未初始化调用init()方法 导致的空指针异常
                    Method setServletContext = controllerBeanClass.getDeclaredMethod("setServletContext", ServletContext.class);
                    setServletContext.setAccessible(true);
                    //给Controller中servletContext方法赋 this.getServletContext() ，意为该类调用获取servletContext方法 进行init()调用
                    setServletContext.invoke(beanObj,this.getServletContext());

                    beanMap.put(beanId,beanObj);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1，设置编码
        request.setCharacterEncoding("utf-8");

        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDoIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastDoIndex);

        Object controllerBeanObj = beanMap.get(servletPath);   //根据请求获取由applicationContext文件中Class.ForName().newInstance()得来的对象

        String operator = request.getParameter("operator");
        if (StringUtil.isEmpty(operator)) {
            operator = "index";
        }

        try {
            //利用反射调用对应operator字符的方法名的方法
            Method method = controllerBeanObj.getClass().getDeclaredMethod(operator, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(controllerBeanObj,request,response);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
