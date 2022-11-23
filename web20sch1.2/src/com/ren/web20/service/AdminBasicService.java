package com.ren.web20.service;

import com.ren.web20.pojo.Admin;
import com.ren.web20.pojo.Author;
import com.ren.web20.pojo.Topic;

import java.util.List;

public interface AdminBasicService {
    /* 管理员登录 */
    Admin login(String loginId,String password);
    //根据用户id删除用户
    int delAuthorById(int authorId);
}
