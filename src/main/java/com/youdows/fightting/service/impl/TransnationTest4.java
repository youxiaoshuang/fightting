package com.youdows.fightting.service.impl;

import com.youdows.fightting.dao.UserEntityMapper;
import com.youdows.fightting.dto.User;
import com.youdows.fightting.entity.UserEntity;
import com.youdows.fightting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by youxiaoshuang on 16/4/8.
 */
@Service
public class TransnationTest4 implements UserService {
    @Autowired
    private UserEntityMapper userDao;
    public List<User> getLists() {
        return null;
    }

    public Integer saveUser(UserEntity user) {
        return userDao.insert(user);
    }



}
