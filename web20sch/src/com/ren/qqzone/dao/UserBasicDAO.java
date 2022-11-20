package com.ren.qqzone.dao;

import com.ren.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-10 21:36
 * @description:
 **/
public interface UserBasicDAO {
    //根据账号和密码获取特定用户信息
    UserBasic getUserBasic(String loginId, String pwd);
    //获取指定用户的所有好友列表
    List<UserBasic> getUserBasicList(UserBasic userBasic);
    //根据id获取UserBasic信息
    UserBasic getUserBasicById(int id);
}
