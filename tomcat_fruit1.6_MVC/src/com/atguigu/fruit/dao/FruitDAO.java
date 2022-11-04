package com.atguigu.fruit.dao;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //获取所有的库存列表信息
    List<Fruit> getFruitList(String keyValue,int PageNO);
    //由fid获取库存水果的详细信息
    Fruit getFruitByFid(int fid);
    //更新信息到数据库中
    int executeUpdate(Fruit fruit);
    //向数据库中插入信息
    int executeAdd(Fruit fruit);
    //根据fid删除数据库中对应的水果
    int executeDel(int fid);
    //根据fid获取数据库中的行数
    int rowQuery(String keyValue);
}
