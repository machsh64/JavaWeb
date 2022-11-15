package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicDAO {
    //获取指定用户的所有日志列表
    List<Topic> getTopicList(UserBasic userBasic);
    //添加日志
    int addTopic(Topic topic);
    //删除日志
    int delTopic(Topic topic);
    //获取特定日志信息
    Topic getTopic(int id);
}
