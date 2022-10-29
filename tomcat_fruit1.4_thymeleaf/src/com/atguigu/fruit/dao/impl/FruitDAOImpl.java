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
    @Override
    public List<Fruit> getFruitList() {
        String sql = "SELECT * " +
                "FROM t_fruit";
        return executeQuery(sql);
    }
}
