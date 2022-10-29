package com.atguigu.myssm.basedao;

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
public class BaseDAO1<T> {

    private Connection conn;

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
    public int executeUpdate(String sql, Object... args) {
        try {
            conn = getConn();
            return runner.update(conn, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return 0;
    }

    //通用的查询表中一个字段的操作
    public T load(String sql, Object... args) {
        T t = null;
        BeanHandler<T> handler = new BeanHandler<>(clazz);
        try {
            conn = getConn();
            t = runner.query(conn, sql, handler, args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return t;
    }

    //通用的查询表中多个字段的操作  返回一个集合
    public List<T> executeQuery(String sql, Object... args) {
        List<T> list = null;
        BeanListHandler<T> handlers = new BeanListHandler<>(clazz);
        try {
            conn = getConn();
            list = runner.query(conn, sql, handlers, args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return list;
    }

    //用于查询特殊值的操作
    public Object getValue(String sql, Object... args) {
        Object obj = null;
        ScalarHandler scalarHandler = new ScalarHandler();
        try {
            conn = getConn();
            obj = runner.query(conn, sql, scalarHandler, args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return obj;
    }

    //执行复杂查询，返回例如统计结果
    protected Object[] executeComplexQuery(String sql , Object... params){
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = getConn() ;
            psmt = conn.prepareStatement(sql);
            //填充sql占位符
            if(params!=null && params.length>0){
                for (int i = 0; i < params.length; i++) {
                    psmt.setObject(i+1,params[i]);
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
            if(rs.next()){
                for(int i = 0 ; i<columnCount;i++){
                    Object columnValue = rs.getObject(i+1);     //33    苹果      5
                    columnValueArr[i]=columnValue;
                }
                return columnValueArr ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn,psmt,rs);
        }
        return null ;
    }

}
