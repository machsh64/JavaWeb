package day02.DAO;

import day02.DAO.Customer;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

//实现Customer表操作方法的接口
@SuppressWarnings("unused")
public interface CustomerDAO {

    //针对于内存中的Customer对象，添加到数据库中
    int add(Customer customer);
    //针对于内存中的Customer对象，改变数据库中对应的数据
    int update(Customer customer);
    //针对于指定的ID，删除数据库中的数据
    int delete(int id);
    //针对于指定的ID查询单条数据
    Customer getInstance(int id);
    //查寻表中所有记录组成的集合
    List<Customer> getAll();
    //返回表中的条目数
    long getCount();
    //返回表中的最大生日
    Date getMAXBirth();
}
