package com.ren.web20.service.impl;

import com.ren.web20.dao.AdminBasicDAO;
import com.ren.web20.pojo.Admin;
import com.ren.web20.pojo.Author;
import com.ren.web20.pojo.Topic;
import com.ren.web20.service.AdminBasicService;
import com.ren.web20.service.TopicService;
import com.ren.web20.service.UserBasicService;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-20 16:32
 * @description:
 **/
public class AdminBasicServiceImpl implements AdminBasicService {

    private AdminBasicDAO adminBasicDAO;
    private TopicService topicService;
    private UserBasicService userBasicService;

    @Override
    public Admin login(String loginId, String password) {
        Admin admin = adminBasicDAO.ADMINLogin(loginId, password);
        if (admin != null) {
            List<Author> allUser = userBasicService.getAllUser();
            admin.setAuthorList(allUser);
        }
        return admin;
    }

    @Override
    public int delAuthorById(int authorId){
        topicService.delAuthorTopicListById(authorId);
        return adminBasicDAO.delAuthorById(authorId);
    }
}
