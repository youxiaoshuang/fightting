package com.youdows.fightting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youdows.fightting.dao.UserDao;
import com.youdows.fightting.dao.UserEntityMapper;
import com.youdows.fightting.dto.User;
import com.youdows.fightting.entity.UserEntity;
import com.youdows.fightting.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserEntityMapper udao;

	public List<User> getLists() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer saveUser(UserEntity user) {
		// TODO Auto-generated method stub
		return udao.insert(user);
	}

}
