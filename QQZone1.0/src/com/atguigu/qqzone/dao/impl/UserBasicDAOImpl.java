package com.atguigu.qqzone.dao.impl;

import com.atguigu.qqzone.dao.UserBasicDAO;
import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-10 21:45
 * @description:
 **/
public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        String sql = "SELECT id,loginId,nickName,pwd,headImg " +
                "FROM t_user_basic " +
                "WHERE loginId = ? AND pwd = ?";
        return executeQuery(sql, loginId, pwd);
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        String sql = "SELECT b_user.id,b_user.loginId,b_user.nickName,b_user.pwd,b_user.headImg " +
                "FROM t_user_basic AS a_user " +
                "LEFT JOIN t_friend ON a_user.id = t_friend.uid " +
                "INNER JOIN t_user_basic AS b_user ON t_friend.fid = b_user.id " +
                "WHERE a_user.id = ? " +
                "ORDER BY b_user.id";
        return executeQueryList(sql, userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(int id){
     String sql = "SELECT id,loginId,nickName,pwd,headImg " +
             "FROM t_user_basic " +
             "WHERE id = ?";
     return executeQuery(sql,id);
    }
}
