package com.zbwang.calendar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbwang.calendar.dao.IUserDao;
import com.zbwang.calendar.domain.User;
import com.zbwang.calendar.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao loginDao;

	@Override
	public Integer insertUser(User user) {
		return loginDao.insertUser(user);
	}

	@Override
	public User getUser(String userName) {
		return loginDao.getUserByUserName(userName);
	}

	@Override
	public User getUser(Integer userId) {
		return loginDao.getUserByUserId(userId);
	}
}
