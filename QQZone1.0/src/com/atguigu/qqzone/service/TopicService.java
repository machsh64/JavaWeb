package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-11 16:48
 * @description:
 **/
public interface TopicService {
    //查询特定用户的日志  根据指定用户的id
    List<Topic> getTopicList(UserBasic userBasic);
}
