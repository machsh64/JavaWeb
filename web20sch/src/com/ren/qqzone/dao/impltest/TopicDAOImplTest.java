package com.ren.qqzone.dao.impltest;

import com.ren.qqzone.dao.TopicDAO;
import com.ren.qqzone.dao.impl.TopicDAOImpl;
import com.ren.qqzone.pojo.Topic;
import com.ren.qqzone.pojo.UserBasic;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.List;

/** 
* TopicDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>11月 17, 2022</pre> 
* @version 1.0 
*/ 
public class TopicDAOImplTest { 
private TopicDAO topicDAO;
@Before
public void before() throws Exception {
    topicDAO = new TopicDAOImpl();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getTopicList(UserBasic userBasic) 
* 
*/ 
@Test
public void testGetTopicList() throws Exception { 
//TODO: Test goes here...
    UserBasic userBasic = new UserBasic(1,"","","");
    List<Topic> topicList = topicDAO.getTopicList(userBasic);
    topicList.forEach(System.out::println);

} 

/** 
* 
* Method: addTopic(Topic topic) 
* 
*/ 
@Test
public void testAddTopic() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: delTopic(int topicId) 
* 
*/ 
@Test
public void testDelTopic() throws Exception { 
//TODO: Test goes here...
    Topic topic = topicDAO.getTopic(3);
    System.out.println(topic);
} 

/** 
* 
* Method: getTopic(int id) 
* 
*/ 
@Test
public void testGetTopic() throws Exception { 
//TODO: Test goes here... 
} 


} 
