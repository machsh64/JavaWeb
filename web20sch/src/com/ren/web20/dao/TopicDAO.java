package com.ren.web20.dao;

import com.ren.web20.pojo.Topic;

import java.util.List;

public interface TopicDAO {
    //获取用户投稿的专题的集合
    List<Topic> getTopicList(int authorId);
    //获取所有日志的集合
    List<Topic> getAllTopic();
    //根据用户的id添加专题
    int addTopic(Topic topic);
}

