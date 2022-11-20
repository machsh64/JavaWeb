package com.ren.myssm.basedao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-07 21:48
 * @description:
 **/
public class ConnUtil {

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();;

    //创造数据库连接
    private static Connection creatConn() throws SQLException {
        DataSource source = null;
        Properties pros = new Properties();
        InputStream is = BaseDAO.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
            return source.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取数据库连接
    public static Connection getConn() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            try {
                conn = ConnUtil.creatConn();
                System.out.println("getConn true...");
            } catch (SQLException e) {
                System.out.println("getConn false...");
                e.printStackTrace();
            }
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    //关闭数据库连接
    public static void closeQuietly() throws SQLException {
        Connection conn = threadLocal.get();
        conn.close();
        threadLocal.set(null);
        System.out.println("Conn close...\n");
    }
}
