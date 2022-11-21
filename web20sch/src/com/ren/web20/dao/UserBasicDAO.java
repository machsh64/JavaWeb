package com.ren.web20.dao;

import com.ren.web20.pojo.Author;

import java.util.List;

public interface UserBasicDAO {
    //获取用户账户密码进行登录
    Author login(String loginId, String password);
    //进行用户判断
    Author finder(Author author);
    //根据id获取用户
    Author getAuthorById(int authorId);
    //获取所有用户
    List<Author> getAllUser();
    //添加用户
    int addUser(Author author);
}
