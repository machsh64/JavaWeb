package com.ren.customer.dao.base;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-17 11:33
 * @description:
 **/
@SuppressWarnings("unused")
public class BaseDAO<T> {

    //获取数据库连接
    public Connection getConn() throws Exception {
        DataSource source = null;
        Properties pros = new Properties();
        InputStream is = BaseDAO.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert source != null;
        return source.getConnection();
    }

    QueryRunner runner = new QueryRunner();
    private Class<T> clazz = null;
    //后期谁继承了该类，则实现该类代码块获取运行时类对象
    {
        //获取当前DAO的子类继承父类中的泛型
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;

        Type[] types = paramType.getActualTypeArguments();  //获取了父类泛型集合
        clazz = (Class<T>) types[0];        // 集合第一个则是继承该类的类的泛型
    }

    //通用的增删改操作
    public int update(Connection conn,String sql, Object... args) {
        try {
            return runner.update(conn, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //通用的查询表中一个字段的操作
    public T getInstance(Connection conn,String sql, Object... args) {
        T t = null;
        BeanHandler<T> handler = new BeanHandler<>(clazz);
        try {
            t = runner.query(conn, sql, handler, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    //通用的查询表中多个字段的操作  返回一个集合
    public List<T> getInstances(Connection conn,String sql, Object... args) {
        List<T> list = null;
        BeanListHandler<T> handlers = new BeanListHandler<>(clazz);
        try {
            list = runner.query(conn, sql, handlers, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //用于查询特殊值的操作
    public Object getValue(Connection conn,String sql, Object... args) {
        Object obj = null;
        ScalarHandler scalarHandler = new ScalarHandler();
        try {
            obj = runner.query(conn, sql, scalarHandler, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
