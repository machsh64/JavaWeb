package com.ren.web20.controller;

import com.ren.web20.pojo.Topic;
import com.ren.web20.service.TopicService;
import com.ren.web20.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-19 10:39
 * @description:
 **/
public class TopicController {
    private TopicService topicService;

    public String page(String page, HttpSession session) {
        List<Topic> allTopic = topicService.getAllTopic();
        //对topic进行设定
        int count = 0;
        for (Topic topic : allTopic) {
            topic.setCount(count++);
        }
        session.setAttribute("topicList", allTopic);
        //首页只进行6个专题展示
        List<Topic> indexTopic = new LinkedList<>();
        if (allTopic.size() > 6) {
            for (int i = 0; i < 6; i++) {
                indexTopic.add(allTopic.get(i));
            }
        }
        session.setAttribute("indexTopic", indexTopic);

        return page;
    }

    public String putTopic(String title, String headLine, String content, LocalDateTime localDateTime,String publish,Integer authorId) {
        Topic topic = new Topic(title, headLine, content, localDateTime, publish, authorId);
        topicService.addTopic(topic);
        return "authorWadmin";
    }
}
