package com.atguigu.myssm.io;

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

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-06 17:38
 * @description:
 **/
public class ClassPathXmlApplicationContext implements BeanFactory{

    private final Map<String,Object> beanMap = new HashMap<>();

    public ClassPathXmlApplicationContext(){
        //将xml文件中所有的bean节点名称以及对象存储在hashMap中
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
                    Class<?> beanClass = Class.forName(className);
                    Object beanObj = beanClass.newInstance();

                    beanMap.put(beanId, beanObj);
                }
            }
            //进行bean之间的依赖关系组装
            for(int i = 0; i < beanNodeList.getLength(); i++){
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    NodeList beanChildNodeList = beanElement.getChildNodes();
                    for(int j = 0; j < beanChildNodeList.getLength(); j++){
                        Node beanChildNode = beanChildNodeList.item(i);
                        if (beanChildNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())){
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");  //保存子节点的Name 此Name为父类要进行组装的属性名称
                            //进行要组装的结尾的对象的结构
                            String propertyRef = propertyElement.getAttribute("ref");
                            Object refObj = beanMap.get(propertyRef);   //此为要赋值的目标对象
                            //获取父类进行反射前属性的获取准备
                            Object beanObj = beanMap.get(beanId);
                            //进行反射赋值
                            Field propertyField = beanObj.getClass().getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
