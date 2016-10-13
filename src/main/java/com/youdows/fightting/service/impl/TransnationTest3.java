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
public class TransnationTest3 {
    @Autowired
     UserEntityMapper userDao;
    @Autowired
     TransnationTest1 trans1;
    @Autowired
    TransnationTest2 trans2;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void trandationTest1(){
        try {
            trans1.TrandationTest1();
            trans2.trandationTest1();
        }catch (Exception e){
            UserEntity user = new UserEntity();
            userDao.insert(user);
            user.setName("test3");
            user.setPassword("password");
            userDao.insert(user);
        }


    }
}
