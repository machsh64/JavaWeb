package com.ren.qqzone.controller;

import com.ren.qqzone.pojo.HostReply;
import com.ren.qqzone.pojo.Reply;
import com.ren.qqzone.pojo.Topic;
import com.ren.qqzone.pojo.UserBasic;
import com.ren.qqzone.service.HostReplyService;
import com.ren.qqzone.service.ReplyService;
import com.ren.qqzone.service.TopicService;
import com.ren.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-17 20:37
 * @description:
 **/
public class TopicController {

    private TopicService topicService;

    //根据日志的id获取日志 根据日志的id获取日志下所有的回复
    public String topicDetail(Integer topicId, HttpSession session){
        Topic topic = topicService.getTopicById(topicId);
        session.setAttribute("topic",topic);
        return "frames/detail";
    }

}
