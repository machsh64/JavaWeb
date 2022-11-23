package com.ren.web20.service;

import com.ren.web20.pojo.Topic;

import java.util.List;

public interface TopicService {
    //根据topicId获取专题
    Topic getTopicById(int topicId);
    //根据用户id获取专题集合
    List<Topic> getTopicListByAuthorId(int authorId);
    //删除特定用户下所有的Topic
    int delAuthorTopicListById(int authorId);
    //获取所有专题集合
    List<Topic> getAllTopic();
    //根据用户id添加专题
    int addTopic(Topic topic);
    //根据topicId进行数据更新
    int updateTopic(Topic topic);
    //根据topicId删除专题
    int delTopicById(int topicId);
}
