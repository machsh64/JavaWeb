package day02.DAO;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * CustomerImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10月 17, 2022</pre>
 */
public class CustomerImplTest {

    CustomerImpl Imp = null;
    @Before
    public void before() throws Exception {
        Imp = new CustomerImpl();
    }

    @After
    public void After() throws Exception {
       Imp.release();
    }

    /**
     * Method: add(Connection conn, Customer customer)
     */
    @Test
    public void testAdd() throws Exception {
//TODO: Test goes here...
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-22");
        Customer customer = new Customer(10, "李立国", "Lili@fali.con",date);
        System.out.println(Imp.add(customer) > 0 ? "上传成功" : "上传失败");
    }

    /**
     * Method: update(Connection conn, Customer customer)
     */
    @Test
    public void testUpdate() throws Exception {
//TODO: Test goes here...
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-22");
        Customer customer = new Customer(33, "小圆", "lili@fali.con",date);
        System.out.println(Imp.update(customer) > 0 ? "修改成功" : "修改失败");
    }

    /**
     * Method: delete(Connection conn, int id)
     */
    @Test
    public void testDelete() throws Exception {
//TODO: Test goes here...
        System.out.println(Imp.delete(34) > 0 ? "删除成功" : "删除失败");
    }

    /**
     * Method: getInstance(Connection conn, int id)
     */
    @Test
    public void testGetInstance() throws Exception {
//TODO: Test goes here...
        Customer customer = Imp.getInstance(23);
        System.out.println(customer);
    }

    /**
     * Method: getAll(Connection conn)
     */
    @Test
    public void testGetAll() throws Exception {
//TODO: Test goes here...
        List<Customer> list = Imp.getAll();
        list.forEach(System.out::println);
    }

    /**
     * Method: getCount(Connection conn)
     */
    @Test
    public void testGetCount() throws Exception {
//TODO: Test goes here...
        long count = Imp.getCount();
        System.out.println(count);
    }

    /**
     * Method: getMAXBirth(Connection conn)
     */
    @Test
    public void testGetMAXBirth() throws Exception {
//TODO: Test goes here...
        Date birth = Imp.getMAXBirth();
        System.out.println(birth);
    }


} 
