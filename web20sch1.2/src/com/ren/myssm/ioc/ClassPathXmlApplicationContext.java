package com.ren.myssm.ioc;

import com.ren.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory {
    private final Map<String, Object> beanMap = new HashMap<>();
    private String path = "applicationContext.xml";

    public ClassPathXmlApplicationContext(){
        this("applicationContext.xml");
    }

    public ClassPathXmlApplicationContext(String path){
        if (StringUtil.isEmpty(path)){
            throw new RuntimeException("IOC配置文件没有指定");
        }
        try {
            //获取document中xml文件的对象
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(is);

            NodeList beansNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beansNodeList.getLength(); i++) {
                Node beanNode = beansNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {   //判断是否是节点对象
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Class<?> beanClass = Class.forName(className);
                    Object beanObj = beanClass.newInstance();

                    //将与元素对应名称的 名称与对象存入哈希map
                    beanMap.put(beanId, beanObj);
                }
            }
            //进行bean之间依赖关系的组装
            for(int i = 0; i < beansNodeList.getLength(); i++){
                Node beanNode = beansNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");   //获取bean节点中的id值 这个值是要进行组装的父类的地址key
                    NodeList beanChildrenNodeList = beanElement.getChildNodes();
                    for(int j = 0; j < beanChildrenNodeList.getLength(); j++){
                        Node beanChildrenNode = beanChildrenNodeList.item(j);
                        if (beanChildrenNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(beanChildrenNode.getNodeName())){
                            Element beanChildrenElement = (Element) beanChildrenNode;
                            String propertyName = beanChildrenElement.getAttribute("name");  //获取bean子节点的name 此name是反射中 需要获取到的父类中继续赋值的 属性名
                            //进行赋值内容 的获取
                            String propertyRef = beanChildrenElement.getAttribute("ref");
                            Object refObj = beanMap.get(propertyRef);    // refObj是要进行赋值的内容

                            Object beanObj = beanMap.get(beanId);
                            Field propertyField = beanObj.getClass().getDeclaredField(propertyName);     //获取反射对象的方法
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);   //至此组装进行完成
                        }
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
