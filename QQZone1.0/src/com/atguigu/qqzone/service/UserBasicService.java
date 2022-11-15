package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-11 16:30
 * @description:
 **/
public interface UserBasicService {
    //登录服务
    UserBasic login(String loginId,String pwd);
    //获取好友列表
    List<UserBasic> getFriendList(UserBasic userBasic);
}
