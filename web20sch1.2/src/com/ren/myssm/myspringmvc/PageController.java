package com.ren.myssm.myspringmvc;

import com.ren.web20.service.TopicService;

import javax.servlet.http.HttpSession;

public class PageController {

    private TopicService topicService;

    public String page(String page){
        return page ;       // frames/left
    }

    /* 进入登 r1录页面前的判断，logNum=0时为用户登录 logNum=1时为管理员登录 保存在session作用域中*/
    /* enroll进行判断是注册还是登录 0为登录 1为注册*/
    public String login(Integer enroll,Integer logNum, HttpSession session){
        /* 进行再次点击清空session账户信息，防止用户绕过检验进入管理层*/
        session.setAttribute("author",null);
        session.setAttribute("admin",null);

        session.setAttribute("enrollNum",enroll);
        session.setAttribute("logNum",logNum);
        /* 进行跳转后默认进行赋值登录页面 */
            return "login";
    }
}
