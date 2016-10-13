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
public class TransnationTest2 {
    @Autowired
    private UserEntityMapper userDao;

    @Transactional(propagation = Propagation.NESTED ,readOnly = false)
    public void trandationTest1(){
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setName("test2");
        user.setPassword("password");
        userDao.insert(user);
        user.setId(2);
        user.setName("test2");
        user.setPassword("password");
        userDao.insert(user);
    }
}
