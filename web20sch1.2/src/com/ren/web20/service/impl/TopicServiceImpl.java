package com.ren.web20.service.impl;

import com.ren.myssm.util.TopicUtil;
import com.ren.web20.dao.TopicDAO;
import com.ren.web20.pojo.Topic;
import com.ren.web20.service.TopicService;

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
    public Topic getTopicById(int topicId){
        Topic topic = topicDAO.getTopicById(topicId);
        return TopicUtil.getTopicTimeWPo(topic);
    }

    @Override
    public List<Topic> getTopicListByAuthorId(int authorId) {
        List<Topic> topicList = topicDAO.getTopicList(authorId);
        return TopicUtil.getTopicTimeWCount(topicList);
    }

    @Override
    public int delAuthorTopicListById(int authorId) {
        return topicDAO.delAuthorTopicList(authorId);
    }

    @Override
    public List<Topic> getAllTopic() {
        List<Topic> allTopic = topicDAO.getAllTopic();
        return TopicUtil.getTopicTimeWCount(allTopic);
    }

    @Override
    public int addTopic(Topic topic){
        return topicDAO.addTopic(topic);
    }

    @Override
    public int updateTopic(Topic topic) {
        return topicDAO.updateTopic(topic);
    }

    @Override
    public int delTopicById(int topicId){
        return topicDAO.delTopicById(topicId);
    }
}
