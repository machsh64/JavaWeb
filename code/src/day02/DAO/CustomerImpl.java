package day02.DAO;

import JDBC.BaseDAO;
import JDBC.JDBCUtils;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-17 12:20
 * @description:
 **/
public class CustomerImpl extends BaseDAO<Customer> implements CustomerDAO {
    //获取数据库连接
    private static Connection conn;
    static {
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int add(Customer customer) {
        String sql = "INSERT customers(name,email,birth) " +
                "VALUES(?,?,?)";
        return update(conn, sql, customer.getName(), customer.getEmail(), customer.getBirth());
    }

    @Override
    public int update(Customer customer) {
        String sql = "UPDATE customers " +
                "SET name = ?,email = ?,birth = ? " +
                "WHERE id = ?";
        return update(conn, sql, customer.getName(), customer.getEmail(), customer.getBirth(), customer.getId());
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM customers " +
                "WHERE id = ?";
        return update(conn, sql, id);
    }

    @Override
    public Customer getInstance(int id) {
        String sql = "SELECT id,name,email,birth " +
                "FROM customers " +
                "WHERE id = ?";
        return getInstance(conn, sql, id);
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT id,name,email,birth " +
                "FROM customers";
        return getInstances(conn,sql);
    }

    @Override
    public long getCount() {
        String sql = "SELECT COUNT(id) " +
                "FROM customers";
        return (long) getValue(conn, sql);
    }

    @Override
    public Date getMAXBirth() {
        String sql = "SELECT MIN(birth) " +
                "FROM customers";
        return (Date) getValue(conn, sql);
    }

    public void release(){
        JDBCUtils.release(conn,null);
    }
}
