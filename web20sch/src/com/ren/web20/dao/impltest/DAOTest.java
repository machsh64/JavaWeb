package com.ren.web20.dao.impltest;

import com.ren.web20.dao.AdminBasicDAO;
import com.ren.web20.dao.TopicDAO;
import com.ren.web20.dao.UserBasicDAO;
import com.ren.web20.dao.impl.AdminBasicDAOImpl;
import com.ren.web20.dao.impl.TopicDAOImpl;
import com.ren.web20.dao.impl.UserBasicDAOImpl;
import com.ren.web20.pojo.Admin;
import com.ren.web20.pojo.Author;
import com.ren.web20.pojo.Topic;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-20 16:06
 * @description:
 **/
public class DAOTest {
    private TopicDAO topicDAO;
    private UserBasicDAO userBasicDAO;
    private AdminBasicDAO adminBasicDAO;

    @Before
    public void beforeTest(){
        topicDAO = new TopicDAOImpl();
        userBasicDAO = new UserBasicDAOImpl();
        adminBasicDAO = new AdminBasicDAOImpl();
    }

    @Test
    public void test() {
        System.out.println("*************************");

        Author author = userBasicDAO.login("w001", "ok");
        System.out.println(author);
        System.out.println("*************************");
        List<Author> allUser = userBasicDAO.getAllUser();
        for (Author auhtorE : allUser) {
            System.out.println(auhtorE);
        }
        System.out.println("**************************");

        Admin admin = adminBasicDAO.ADMINLogin("w001", "ok");
        System.out.println(admin);
        System.out.println("*************************");

    }
}
