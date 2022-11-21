package com.ren.web20.service.impl;

import com.ren.web20.dao.TopicDAO;
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
public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO;


    @Override
    public List<Topic> getTopicListByAuthorId(int authorId) {
        return topicDAO.getTopicList(authorId);
    }

    @Override
    public List<Topic> getAllTopic() {
        return topicDAO.getAllTopic();
    }

    @Override
    public int addTopic(Topic topic){
        return topicDAO.addTopic(topic);
    }
}
