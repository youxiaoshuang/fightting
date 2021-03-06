package com.youdows.fightting.dao;

import com.youdows.fightting.entity.OrderEntity;

public interface OrderEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderEntity record);

    int insertSelective(OrderEntity record);

    OrderEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderEntity record);

    int updateByPrimaryKey(OrderEntity record);
}