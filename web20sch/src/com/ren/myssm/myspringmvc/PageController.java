package com.ren.myssm.myspringmvc;

import javax.servlet.http.HttpSession;

public class PageController {
    public String page(String page){
        return page ;       // frames/left
    }

    /* 进入登录页面前的判断，logNum=0时为用户登录 logNum=1时为管理员登录 保存在session作用域中*/
    public String login(Integer logNum, HttpSession session){
        session.setAttribute("logNum",logNum);
        return "login";
    }
}
