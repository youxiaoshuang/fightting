package com.youdows.fightting.service;

import java.util.List;

import com.youdows.fightting.dto.User;
import com.youdows.fightting.entity.UserEntity;

public interface UserService {
	List<User> getLists();
    Integer saveUser(UserEntity user);

}
