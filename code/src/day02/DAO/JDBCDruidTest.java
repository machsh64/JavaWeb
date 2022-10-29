package day02.DAO;

import JDBC.JDBCUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import java.sql.Connection;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-17 11:20
 * @description:
 **/
public class JDBCDruidTest {
    @Test
    public void test() {
        Connection conn = null;
        QueryRunner runner = new QueryRunner();
        BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT id, name, email, birth " +
                    "FROM customers " +
                    "WHERE id = ?";
            Customer customer = runner.query(conn,sql,handler,23);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(conn,null);
        }
    }
}
