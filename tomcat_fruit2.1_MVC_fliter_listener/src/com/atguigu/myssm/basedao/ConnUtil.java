package com.atguigu.myssm.basedao;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-08 15:04
 * @description:
 **/
public class ConnUtil {

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USER = "root";
    public static final String PWD = "omgd45945.+";

    //创造连接
    private static Connection createConn() {
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取连接
    public static Connection getConn() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    //关闭连接
    public static void closeQuietly() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn != null)
        conn.close();
        threadLocal.set(null);
        System.out.println("Conn close...");
    }
}
