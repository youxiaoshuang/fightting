package com.youdows.fightting.service.impl;

import com.youdows.fightting.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by youxiaoshuang on 16/4/7.
 */
@Service
//@Scope("prototype")
public class SingletonService {
    @Autowired
    private TransnationTest4 transnationTest4;
    public void Num(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("you");
        userEntity.setPassword("123");
        transnationTest4.saveUser(userEntity);
        System.out.println("当前插入的ID:"+userEntity.getId()+" : "+userEntity.getName()+" 当前线程Thread:"+Thread.currentThread().getId());
    }
}
