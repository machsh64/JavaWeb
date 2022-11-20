package com.ren.qqzone.service.impl;

import com.ren.qqzone.service.TopicService;
import com.ren.qqzone.dao.TopicDAO;
import com.ren.qqzone.pojo.Topic;
import com.ren.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-11 16:49
 * @description:
 **/
public class TopicServiceImpl implements TopicService {

    private final TopicDAO topicDAO = null;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public int delTopic(int topicID){
        return topicDAO.delTopic(topicID);
    }
}
