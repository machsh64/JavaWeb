package com.ren.web20.service;

import com.ren.web20.pojo.Topic;

import java.util.List;

public interface TopicService {
    //根据用户id获取专题集合
    List<Topic> getTopicListByAuthorId(int authorId);
    //获取所有专题集合
    List<Topic> getAllTopic();
    //根据用户id添加专题
    int addTopic(Topic topic);
}
