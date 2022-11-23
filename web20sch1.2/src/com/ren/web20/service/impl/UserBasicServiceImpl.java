package com.ren.web20.service.impl;

import com.ren.myssm.util.TopicUtil;
import com.ren.web20.dao.UserBasicDAO;
import com.ren.web20.pojo.Author;
import com.ren.web20.pojo.Topic;
import com.ren.web20.service.TopicService;
import com.ren.web20.service.UserBasicService;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-19 10:41
 * @description:
 **/
public class UserBasicServiceImpl implements UserBasicService {

    private UserBasicDAO userBasicDAO;
    private TopicService topicService;

    @Override
    public Author getAuthorById(int authorId) {
        return userBasicDAO.getAuthorById(authorId);
    }

    @Override
    public Author login(String loginId, String password) {
        Author author = userBasicDAO.login(loginId, password);
        if (author != null) {
            List<Topic> authorTopicList = topicService.getTopicListByAuthorId(author.getId());
            author.setTopicList(authorTopicList);
        }
        return author;
    }

/*模糊查找用户，功能简单但用户数量少，停止*/
    @Override
    public Author finder(Author author) {
        return null;
    }

    @Override
    public List<Author> getAllUser() {
        List<Author> authorList = userBasicDAO.getAllUser();
        for (Author author :authorList) {
            List<Topic> topicList = topicService.getTopicListByAuthorId(author.getId());
            List<Topic> topicList1 = TopicUtil.getTopicTimeWCount(topicList);
            author.setTopicList(topicList1);
        }
        return authorList;
    }

    @Override
    public int addUser(Author author) {
        Author finder = userBasicDAO.finder(author);
        if (finder == null){
            return userBasicDAO.addUser(author);
        } else {
            return 0;
        }
    }
}
