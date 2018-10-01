package com.zbwang.calendar.util;

import com.zbwang.calendar.vo.BaseCommand;

public class SecurityInfoHolder {

	private static ThreadLocal<BaseCommand> securityInfo = new ThreadLocal<BaseCommand>();

	public static BaseCommand getSecurityInfo() {
		return securityInfo.get();
	}

	public static void setSecurityInfo(BaseCommand command) {
		securityInfo.set(command);
	}
}
