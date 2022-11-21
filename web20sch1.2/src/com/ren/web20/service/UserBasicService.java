package com.ren.web20.service;

import com.ren.web20.pojo.Author;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-19 10:41
 * @description:
 **/
public interface UserBasicService {
    //根据id获取用户
    Author getAuthorById(int authorId);
    //用户登录
    Author login(String loginId,String password);
    //进行用户判断
    Author finder(Author author);
    //获取所有用户
    List<Author> getAllUser();
    //用户进行注册
    int addUser(Author author);
}
