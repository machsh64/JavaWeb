package com.ren.web20.controller;

import com.ren.myssm.util.StringUtil;
import com.ren.web20.pojo.Admin;
import com.ren.web20.pojo.Author;
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
        session.setAttribute("topicList", allTopic);
        //首页只进行6个专题展示
        List<Topic> indexTopic = new LinkedList<>();
        if (allTopic.size() > 6) {
            for (int i = 0; i < 6; i++) {
                indexTopic.add(allTopic.get(i));
            }
        }
        Object logNumObj = session.getAttribute("logNum");
        if (logNumObj==null){
            session.setAttribute("logNum",0);
        }
        session.setAttribute("indexTopic", indexTopic);

        return page;
    }

    public String putTopic(String title, String headLine, String content, LocalDateTime localDateTime,String publish,Integer authorId) {
        Topic topic = new Topic(title, headLine, content, localDateTime, publish, authorId);
        topicService.addTopic(topic);
        return "redirect:topic.do?operate=JumpCrash";
    }

    /* 进行跳转时使用 利用loginNum进行用户/管理员判断 0为用户 1为管理员 */
    public String JumpCrash(HttpSession session){
        int loginNum = (int) session.getAttribute("logNum");
        if (loginNum == 0){
            Author author = (Author) session.getAttribute("author");
            if (author != null){
                List<Topic> authorTopicList = topicService.getTopicListByAuthorId(author.getId());
                author.setTopicList(authorTopicList);
                session.setAttribute("author",author);
                return "authorWadmin";
            }else {
                return "authorWadmin";
            }
        }else if (loginNum == 1){
            Admin admin = (Admin) session.getAttribute("admin");
            if (admin != null){
                List<Topic> allTopic = topicService.getAllTopic();
                admin.setTopicList(allTopic);
                session.setAttribute("admin",admin);
                return "authorWadmin";
            }else {
                return "authorWadmin";
            }
        }
        throw new RuntimeException("UserController出现问题");
    }

    public String delTopic(Integer topicId,HttpSession session){
        topicService.delTopicById(topicId);
        return "redirect:topic.do?operate=JumpCrash";
    }

    public String topicDetail(Integer topicId,HttpSession session){
        Topic topic = topicService.getTopicById(topicId);
        session.setAttribute("topicDetail",topic);
        return "topicDetail";
    }
}
