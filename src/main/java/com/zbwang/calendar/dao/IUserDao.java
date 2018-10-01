package com.zbwang.calendar.dao;

import org.apache.ibatis.annotations.Param;

import com.zbwang.calendar.domain.User;

public interface IUserDao {

	Integer insertUser(User user);

	User getUserByUserName(@Param("userName") String userName);

	User getUserByUserId(@Param("userId") Integer userId);
}
