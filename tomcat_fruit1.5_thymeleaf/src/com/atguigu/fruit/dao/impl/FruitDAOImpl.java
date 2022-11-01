package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;

import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-10-27 23:44
 * @description:
 **/
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    //返回一个Fruit对象的集合
    @Override
    public List<Fruit> getFruitList(int PageNO) {
        String sql = "SELECT * " +
                "FROM t_fruit " +
                "LIMIT ?,?";
        return executeQuery(sql, (PageNO - 1) * 5, 5);
    }

    //根据id返回一个Fruit对象
    @Override
    public Fruit getFruitByFid(int fid) {
        String sql = "SELECT * " +
                "FROM t_fruit " +
                "WHERE fid = ?";
        return load(sql, fid);
    }

    //进行信息的更新
    @Override
    public int executeUpdate(Fruit fruit) {
        String sql = "UPDATE t_fruit " +
                "SET fname = ?,price = ?,fcount = ?,remark = ? " +
                "WHERE fid = ?";
        return executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
    }

    //进行数据的插入
    @Override
    public int executeAdd(Fruit fruit) {
        String sql = "INSERT INTO t_fruit(fname,price,fcount,remark) " +
                "VALUES(?,?,?,?)";
        return executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
    }

    //进行数据的删除
    @Override
    public int executeDel(int fid) {
        String sql = "DELETE FROM t_fruit " +
                "WHERE fid = ?";
        return executeUpdate(sql, fid);
    }

    //获取数据库中的行数
    @Override
    public int rowQuery(){
        String sql = "SELECT COUNT(fid) " +
                "FROM t_fruit";
        return ((Long) getValue(sql)).intValue();
    }
}
