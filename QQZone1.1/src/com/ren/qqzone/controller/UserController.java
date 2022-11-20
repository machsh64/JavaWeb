package com.ren.qqzone.controller;

import com.ren.qqzone.pojo.Topic;
import com.ren.qqzone.pojo.UserBasic;
import com.ren.qqzone.service.TopicService;
import com.ren.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserController {

    private UserBasicService userBasicService ;
    private TopicService topicService ;

    //登录判断进入主页
    public String login(String loginId , String pwd , HttpSession session){
        //1.登录验证
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if(userBasic!=null){
            //1-1 获取相关的好友信息
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            //1-2 获取相关的日志列表信息(但是，日志只有id，没有其他信息）
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            session.setAttribute("userBasic",userBasic);
            session.setAttribute("friend",userBasic);
            return "index";
        }else{
            return "login";
        }
    }

    //进入朋友的主页
    public String friendPage(Integer friendId,HttpSession session){
        UserBasic friendBasic = userBasicService.getUserBasicByID(friendId);
        List<Topic> topicList = topicService.getTopicList(friendBasic);
        friendBasic.setTopicList(topicList);
        session.setAttribute("friend",friendBasic);
        return "index";
    }

    //根据日志的id删除对应的日志
    public String delete(Integer topicId,HttpSession session){
        topicService.delTopic(topicId);

        Object userBasicObj = session.getAttribute("userBasic");
        UserBasic userBasic = (UserBasic) userBasicObj;
        List<Topic> topicList = topicService.getTopicList(userBasic);

        userBasic.setTopicList(topicList);

        session.setAttribute("userBasic",userBasic);
        return "frames/main";
    }
}
