package com.ren.qqzone.dao;

import com.ren.qqzone.pojo.Topic;
import com.ren.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicDAO {
    //获取指定用户的所有日志列表
    List<Topic> getTopicList(UserBasic userBasic);
    //添加日志
    int addTopic(Topic topic);
    //删除日志
    int delTopic(int topicId);
    //获取特定日志信息
    Topic getTopic(int id);
}
