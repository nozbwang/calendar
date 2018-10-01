package com.zbwang.calendar.domain;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class User {

	private Integer userId;
	private String userName;
	private String email;
	private String password;
	private String confirmPassword;
	private Date addTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserNameInLowerCase() {
		return StringUtils.lowerCase(userName);
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public boolean isLogon() {
		return userId != null && userId > 0;
	}
}