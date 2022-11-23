package com.ren.web20.dao;

import com.ren.web20.pojo.Admin;

public interface AdminBasicDAO {
    //获取管理员账户密码进行登录
    Admin ADMINLogin(String loginId, String password);
    //根据用户Id删除用户
    int delAuthorById(int authorId);
}
