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

public class ClassPathXmlApplicationContext implements BeanFactory {

    private final Map<String,Object> beanMap = new HashMap<>();

    public ClassPathXmlApplicationContext(){
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //1.创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder() ;
            //3.创建Document对象
            Document document = documentBuilder.parse(inputStream);

            //4.获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for(int i = 0 ; i<beanNodeList.getLength() ; i++){
                Node beanNode = beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element)beanNode ;
                    String beanId =  beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Class<?> beanClass = Class.forName(className);
                    //创建bean实例
                    Object beanObj = beanClass.newInstance() ;
                    //将bean实例对象保存到map容器中
                    beanMap.put(beanId , beanObj) ;
                    //到目前为止，此处需要注意的是，bean和bean之间的依赖关系还没有设置
                }
            }
            //5.组装bean之间的依赖关系
            for(int i = 0 ; i<beanNodeList.getLength() ; i++){
                Node beanNode = beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");    //从此处获取bean中的id
                    NodeList beanChildNodeList = beanElement.getChildNodes();  //对每个bean中子节点进行集合化
                    for (int j = 0; j < beanChildNodeList.getLength() ; j++) {    //遍历bean子节点集合
                        Node beanChildNode = beanChildNodeList.item(j);
                        if(beanChildNode.getNodeType()==Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())){   //进行子节点的判断 只留下property类型的子节点
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");   //保留子节点的name

                            //此处进行子节点导航bean中需要进行组转的类的地址
                            String propertyRef = propertyElement.getAttribute("ref");
                            //1) 找到propertyRef对应的实例
                            Object refObj = beanMap.get(propertyRef);  //从hashMap获取bean需要进行组转的类

                            //2) 将refObj设置到当前bean对应的实例的property属性上去
                            Object beanObj = beanMap.get(beanId);     //获取此时bean节点对应的类
                            Class<?> beanClazz = beanObj.getClass();   //开始进行反射前的准备
                            Field propertyField = beanClazz.getDeclaredField(propertyName);   //获取反射类中需要的属性的名称
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);    //将beanObj（bean节点对应的类）中名称为 子节点中name对应的属性进行赋值 赋值为 refObj（从由当前子节点的ref属性 从hashMap中获取到的节点类）。此时完成了组装

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
