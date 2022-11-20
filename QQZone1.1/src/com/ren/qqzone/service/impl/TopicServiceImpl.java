package com.ren.qqzone.service.impl;

import com.ren.qqzone.pojo.Reply;
import com.ren.qqzone.service.ReplyService;
import com.ren.qqzone.service.TopicService;
import com.ren.qqzone.dao.TopicDAO;
import com.ren.qqzone.pojo.Topic;
import com.ren.qqzone.pojo.UserBasic;
import com.ren.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-11 16:49
 * @description:
 **/
public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO;
    private ReplyService replyService;
    private UserBasicService userBasicService;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public int delTopic(int topicID){
        return topicDAO.delTopic(topicID);
    }

    @Override
    public Topic getTopicById(int topicID){
        Topic topic = topicDAO.getTopic(topicID);
        if (topic!=null) {
            List<Reply> replyList = replyService.getReplyList(topic.getId());
            topic.setReplyList(replyList);
            UserBasic userBasic = userBasicService.getUserBasicByID(topic.getAuthorId());
            topic.setAuthor(userBasic);
        }
        return topic;
    }
}
