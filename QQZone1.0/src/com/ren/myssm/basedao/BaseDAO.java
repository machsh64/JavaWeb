package com.ren.myssm.basedao;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-17 11:33
 * @description:
 **/
@SuppressWarnings("unused")
public class BaseDAO<T> {

    //获取数据库连接
    private Connection conn = null;

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

    private Connection getConn() {
        conn = ConnUtil.getConn();
        return conn;
    }

    //通用的增删改操作
    public int executeUpdate(String sql, Object... args) {
        try {
            return runner.update(getConn(), sql, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO 层executeUpdate出现问题");
        }
    }

    //通用的查询表中一个字段的操作
    public T executeQuery(String sql, Object... args) {
        T t = null;
        BeanHandler<T> handler = new BeanHandler<>(clazz);
        try {
            t = runner.query(getConn(), sql, handler, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO 层load出现问题");
        }
        return t;
    }

    //通用的查询表中多个字段的操作  返回一个集合
    public List<T> executeQueryList(String sql, Object... args) {
        List<T> list = null;
        BeanListHandler<T> handlers = new BeanListHandler<>(clazz);
        try {
            list = runner.query(getConn(), sql, handlers, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO 层executeQuery出现问题");
        }
        return list;
    }

    //用于查询特殊值的操作
    public Object getValue(String sql, Object... args) {
        Object obj = null;
        ScalarHandler scalarHandler = new ScalarHandler();
        try {
            obj = runner.query(getConn(), sql, scalarHandler, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO 层getValue出现问题");
        }
        return obj;
    }

    //用于查询特殊值集合的操作
    public List<Object> getValues(String sql, Object... args) {
        List<Object> list = new LinkedList<>();
        ScalarHandler scalarHandler = new ScalarHandler();
        try {
            list.add(runner.query(getConn(), sql, scalarHandler, args));
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO 层getValue出现问题");
        }
        return list;
    }

    //执行复杂查询，返回例如统计结果
    protected Object[] executeComplexQuery(String sql, Object... params) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            //填充sql占位符
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    psmt.setObject(i + 1, params[i]);
                }
            }
            //执行sql
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            //6.解析rs
            if (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    columnValueArr[i] = columnValue;
                }
                return columnValueArr;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO 层executeComplexQuery出现问题");
        } finally {
            DbUtils.closeQuietly(null, psmt, rs);
        }
        return null;
    }

}
