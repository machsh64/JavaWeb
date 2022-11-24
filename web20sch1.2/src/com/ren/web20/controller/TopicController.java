package com.ren.web20.controller;

import com.ren.web20.pojo.Admin;
import com.ren.web20.pojo.Author;
import com.ren.web20.pojo.Topic;
import com.ren.web20.service.TopicService;
import com.ren.web20.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
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
    private UserBasicService userBasicService;

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

    public String putTopic(String title, String headLine, String content, Date localDateTime,String publish,Integer authorId) {
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
                return "authorWadmin";
            }else {
                return "authorWadmin";
            }
        }else if (loginNum == 1){
            Admin admin = (Admin) session.getAttribute("admin");
            if (admin != null){
                List<Author> allUser = userBasicService.getAllUser();
                admin.setAuthorList(allUser);
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

    public String setTopicD(Integer topicId,HttpSession session){
        Topic topic = topicService.getTopicById(topicId);
        session.setAttribute("topicDetail",topic);
        return "setTopic";
    }

    public String updateTopic(String title, String headLine, String content, Date localDateTime, String publish, Integer topicId, HttpSession session){
        Topic topic = new Topic(topicId,title, headLine, content, localDateTime, publish, topicId);
        topicService.updateTopic(topic);

        Author author = (Author) session.getAttribute("author");
        if (author != null){
            List<Topic> authorTopicList = topicService.getTopicListByAuthorId(author.getId());
            author.setTopicList(authorTopicList);
            return "redirect:topic.do?operate=JumpCrash";
        }
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin!=null){
            List<Author> allUser = userBasicService.getAllUser();
            admin.setAuthorList(allUser);
            return "redirect:topic.do?operate=JumpCrash";
        }
        throw new RuntimeException("TopicController: updateTopic_maybe exception");
    }
}
