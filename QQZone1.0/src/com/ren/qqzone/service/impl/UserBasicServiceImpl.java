package com.ren.qqzone.service.impl;

import com.ren.qqzone.service.UserBasicService;
import com.ren.qqzone.dao.UserBasicDAO;
import com.ren.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-11 16:33
 * @description:
 **/
public class UserBasicServiceImpl implements UserBasicService {

    private final UserBasicDAO userBasicDAO = null;

    @Override
    public UserBasic login(String loginId, String pwd) {
        //1,进行登录验证
        return userBasicDAO.getUserBasic(loginId, pwd);
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic){
        return userBasicDAO.getUserBasicList(userBasic);
    }
}
