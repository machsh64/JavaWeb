package com.ren.qqzone.dao.impltest;

import com.ren.qqzone.dao.HostReplyDAO;
import com.ren.qqzone.dao.impl.HostReplyDAOImpl;
import com.ren.qqzone.pojo.HostReply;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* HostReplyDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>11月 17, 2022</pre> 
* @version 1.0 
*/ 
public class HostReplyDAOImplTest {

    private HostReplyDAO hostReplyDAO;

@Before
public void before() throws Exception {
    hostReplyDAO = new HostReplyDAOImpl();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getHostReplyById(int replyId) 
* 
*/ 
@Test
public void testGetHostReplyById() throws Exception { 
//TODO: Test goes here...
    HostReply hostReply = hostReplyDAO.getHostReplyById(10);
    System.out.println(hostReply);
} 


} 
