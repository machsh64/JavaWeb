package com.ren.web20.dao.impl;

import com.ren.myssm.basedao.BaseDAO;
import com.ren.web20.dao.UserBasicDAO;
import com.ren.web20.pojo.Author;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-19 10:45
 * @description: 用户功能实现类
 **/
public class UserBasicDAOImpl extends BaseDAO<Author> implements UserBasicDAO {
    @Override
    public Author login(String loginId, String password) {
        String sql = "SELECT id,nickName,loginId,password " +
                "FROM w_author " +
                "WHERE loginId=? AND password=?";
        return executeQuery(sql,loginId,password);
    }

    @Override
    public Author finder(Author author){
        String sql = "SELECT nickName,loginId,password " +
                "FROM w_author " +
                "WHERE nickName=? AND loginId=? AND password=?";
        return executeQuery(sql,author.getNickName(),author.getLoginId(),author.getPassword());
    }

    @Override
    public Author getAuthorById(int authorId){
        String sql = "SELECT id,nickName,loginId,password " +
                "FROM w_author " +
                "WHERE id=?";
        return executeQuery(sql,authorId);
    }

    @Override
    public List<Author> getAllUser() {
        String sql = "SELECT id,nickName,loginId,password " +
                "FROM w_author";
        return executeQueryList(sql);
    }

    @Override
    public int addUser(Author author) {
        String sql = "INSERT INTO w_author(nickName,loginId,password) " +
                "VALUES(?,?,?)";
        return executeUpdate(sql,author.getNickName(),author.getLoginId(),author.getPassword());
    }
}
