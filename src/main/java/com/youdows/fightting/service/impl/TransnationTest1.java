package com.youdows.fightting.service.impl;

import com.youdows.fightting.dao.UserEntityMapper;
import com.youdows.fightting.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by youxiaoshuang on 16/4/5.
 */
@Service
public class TransnationTest1{
    @Autowired
    private UserEntityMapper userDao;

    @Transactional(propagation = Propagation.NESTED)
    public void TrandationTest1(){
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setName("test1");
        user.setPassword("password");
        userDao.insert(user);
        user.setId(2);
        user.setName("test1");
        user.setPassword("password");
        userDao.insert(user);
    }
}
