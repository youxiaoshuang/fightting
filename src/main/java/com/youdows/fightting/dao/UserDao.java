package com.youdows.fightting.dao;

import java.util.List;

import com.youdows.fightting.dto.User;

public interface UserDao {
	Integer saveUser(User user);
    Integer deleteUser(User user);
    List<User> getLists();
}
