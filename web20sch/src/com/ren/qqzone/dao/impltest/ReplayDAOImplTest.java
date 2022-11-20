package com.ren.qqzone.dao.impltest;

import com.ren.qqzone.dao.ReplyDAO;
import com.ren.qqzone.dao.impl.ReplayDAOImpl;
import com.ren.qqzone.pojo.Reply;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.List;

/** 
* ReplayDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>11月 17, 2022</pre> 
* @version 1.0 
*/ 
public class ReplayDAOImplTest { 
    private ReplyDAO replyDAO;
@Before
public void before() throws Exception {
    replyDAO = new ReplayDAOImpl();
} 

@After
public void after() throws Exception {
} 

/** 
* 
* Method: getReplyList(int topicId) 
* 
*/ 
@Test
public void testGetReplyList() throws Exception { 
//TODO: Test goes here...
    List<Reply> replyList = replyDAO.getReplyList(3);
    for(Reply reply:replyList){
        System.out.println(reply);
    }
} 

/** 
* 
* Method: addReply(Reply reply) 
* 
*/ 
@Test
public void testAddReply() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: delReply(int id) 
* 
*/ 
@Test
public void testDelReply() throws Exception { 
//TODO: Test goes here... 
} 


} 
