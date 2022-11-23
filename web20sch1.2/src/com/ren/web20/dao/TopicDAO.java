package com.ren.web20.dao;

import com.ren.web20.pojo.Topic;

import java.util.List;

public interface TopicDAO {
    //根据用户id获取topic集合
    List<Topic> getTopicList(int authorId);
    //删除特定用户Id下所有Topic
    int delAuthorTopicList(int authorId);
    //根据topicId获取topic集合
    Topic getTopicById(int topicId);
    //根据topicId删除topic
    int delTopicById(int topicId);
    //根据topicId进行数据更新
    int updateTopic(Topic topic);
    //获取所有日志的集合
    List<Topic> getAllTopic();
    //根据用户的id添加专题
    int addTopic(Topic topic);
}

