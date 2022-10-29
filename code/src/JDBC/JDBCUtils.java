package JDBC;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @program: codeJDBC
 * @author: Ren
 * @create: 2022-10-15 16:43
 * @description:
 **/
@SuppressWarnings("unused")
public class JDBCUtils {

    //使用Druid数据库连接池技术
    //数据库连接池只需要存在一个即可
    private static DataSource source = null;

    static {
        Properties pros = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("JDBC\\druid.properties");
        try {
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {

        Connection conn = source.getConnection();
        System.out.println("数据库连接成功...\n");
        return conn;
    }

    //释放资源
    //！注  所有操作方法中仅释放了方法内涉及到的资源，所有调用操作最后应该使用该方法进行统一资源释放
    public static void release(Connection conn, Statement statement) {
   /*     try {
            if (conn != null) {
                conn.close();
            }
            if (statement != null) {
                statement.close();
            }
            System.out.println("\n资源释放完毕...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        try {
            DbUtils.closeQuietly(conn);
        } finally {
            DbUtils.closeQuietly(statement);
            System.out.println("\n资源释放完毕...");
        }
    }

    //使用dbutils.jar 中提供的DBUtils工具类，实现资源的关闭
    public static void release1(Connection conn, Statement statement, ResultSet resultSet) {
        DbUtils.closeQuietly(conn, statement, resultSet);
    }

}
