package com.zbwang.calendar.vo;

import com.zbwang.calendar.domain.User;

public class BaseCommand {

	private User user;

	public BaseCommand(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
