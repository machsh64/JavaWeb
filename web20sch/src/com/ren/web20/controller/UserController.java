package com.ren.web20.controller;

import com.ren.web20.pojo.Admin;
import com.ren.web20.pojo.Author;
import com.ren.web20.pojo.Topic;
import com.ren.web20.service.AdminBasicService;
import com.ren.web20.service.TopicService;
import com.ren.web20.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-19 10:40
 * @description:
 **/
public class UserController {
    private UserBasicService userBasicService;
    private AdminBasicService adminBasicService;
    private TopicService topicService;

    /* 利用loginNum进行用户/管理员判断 0为用户 1为管理员 */
    public String login(String loginId, String password, HttpSession session){
        Object loginNumObj = session.getAttribute("loginNum");
        int loginNum = (int) loginNumObj;
        if (loginNum == 0){
            Author author = userBasicService.login(loginId, password);
            if (author != null){
                List<Topic> authorTopicList = topicService.getTopicListByAuthorId(author.getId());
                author.setTopicList(authorTopicList);
                session.setAttribute("author",author);
                return "topicHt";
            }
        }else if (loginNum == 1){
            Admin admin = adminBasicService.login(loginId, password);
            if (admin != null){
                List<Topic> allTopic = topicService.getAllTopic();
                admin.setTopicList(allTopic);
                session.setAttribute("admin",admin);
                return "topicHt";
            }
        }
        return "login";
    }

    /* 注册 仅限用户进行注册 */
    public String enroll(String nickName,String loginId,String password,HttpSession session){
        userBasicService.addUser(new Author(nickName,loginId,password));
        session.setAttribute("enrollNum",0);
        return "login";
    }
}
