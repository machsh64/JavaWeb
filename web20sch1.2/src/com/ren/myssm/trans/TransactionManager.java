package com.ren.myssm.trans;

import com.ren.myssm.basedao.ConnUtil;

import java.sql.SQLException;

public class TransactionManager {

    //开启事务
    public static void beginTrans() throws SQLException {

        ConnUtil.getConn().setAutoCommit(false);
        System.out.println("beginTrans true...");
    }

    //提交事务
    public static void commit() throws SQLException {

        ConnUtil.getConn().commit();
        System.out.println("commit true...");
        ConnUtil.closeQuietly();
    }

    //回滚事务
    public static void rollback() throws SQLException {

        ConnUtil.getConn().rollback();
        System.out.println("commit false, rollback...");
        ConnUtil.closeQuietly();
    }
}
