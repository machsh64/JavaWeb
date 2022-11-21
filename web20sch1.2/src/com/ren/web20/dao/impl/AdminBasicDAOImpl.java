package com.ren.web20.dao.impl;

import com.ren.myssm.basedao.BaseDAO;
import com.ren.web20.dao.AdminBasicDAO;
import com.ren.web20.pojo.Admin;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-20 15:50
 * @description:  管理员功能实现类
 **/
public class AdminBasicDAOImpl extends BaseDAO<Admin> implements AdminBasicDAO {
    @Override
    public Admin ADMINLogin(String loginId, String password) {
        String sql = "SELECT id,nickName,loginId,password " +
                "FROM w_admin";
        return executeQuery(sql);
    }
}
