package com.ren.myssm.myspringmvc;

import com.ren.myssm.util.StringUtil;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-04 15:45
 * @description:
 **/
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet{

    private final Map<String, Object> beanMap = new HashMap<>();

    public void init(){
        try{
            //获取document中xml文件的对象
            InputStream is = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(is);

            NodeList beansNodeList = document.getElementsByTagName("bean");
            for(int i = 0; i < beansNodeList.getLength(); i++){
                Node beanNode = beansNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE){   //判断是否是节点对象
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String beanClass = beanElement.getAttribute("class");
                    Class<?> controllerBeanClass = Class.forName(beanClass);

                    Object beanObj = controllerBeanClass.newInstance();
                    Method setServletContextMethod = controllerBeanClass.getDeclaredMethod("setServletContext", ServletContext.class);
                    setServletContextMethod.setAccessible(true);
                    setServletContextMethod.invoke(beanObj,this.getServletContext());

                    //将与元素对应名称的 名称与对象存入哈希map
                    beanMap.put(beanId,beanObj);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String servletPath = req.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDoIndex = servletPath.lastIndexOf(".do");
        //获取到跳转时的注解名
        servletPath = servletPath.substring(0,lastDoIndex);
        //根据跳转的注解名 获取跳转的对象
        Object controlBeanObj = beanMap.get(servletPath);

        //从operator的value获取需要调用的方法的名称
        String operator = req.getParameter("operator");
        //如果operator为空则赋值为index 使其跳转到主页进行刷新
        if (StringUtil.isEmpty(operator))
            operator = "index";

        try {
            //通过反射直接索引到方法
            Method method = controlBeanObj.getClass().getDeclaredMethod(operator, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(controlBeanObj,req,resp);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("operator值非法");
        }
    }
}
