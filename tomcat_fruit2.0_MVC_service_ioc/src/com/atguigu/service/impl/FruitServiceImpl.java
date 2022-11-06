package com.atguigu.service.impl;

import com.atguigu.service.FruitService;
import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-05 19:27
 * @description:
 **/
public class FruitServiceImpl implements FruitService {
    private final FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    public List<Fruit> getFruitList(String keyValue, int pageNo) {
        return fruitDAO.getFruitList(keyValue, pageNo);
    }

    @Override
    public Fruit getFruitByFid(int fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public int delFruit(int fid) {
        return fruitDAO.executeDel(fid);
    }

    @Override
    public int addFruit(Fruit fruit) {
        return fruitDAO.executeAdd(fruit);
    }

    @Override
    public int update(Fruit fruit) {
        return fruitDAO.executeUpdate(fruit);
    }

    @Override
    public int paramsQuery(String keyValue) {
        return ((fruitDAO.rowQuery(keyValue) + 5 - 1) / 5);
    }
}
