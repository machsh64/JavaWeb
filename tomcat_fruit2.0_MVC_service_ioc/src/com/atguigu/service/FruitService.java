package com.atguigu.service;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

public interface FruitService {
    //获取指定页面的库存列表信息
    List<Fruit> getFruitList (String keyValue, int pageNo);
    //由fid获取指定库存信息
    Fruit getFruitByFid(int fid);
    //由fig删除库存记录
    int delFruit(int fid);
    //添加库存列表
    int addFruit(Fruit fruit);
    //更细库存中的信息
    int update(Fruit fruit);
    //获取总页数  每页5条数据
    int paramsQuery(String keyValue);
}
