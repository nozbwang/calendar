package com.zbwang.calendar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbwang.calendar.service.IEventTimeService;

@Service
public class EventTimeServiceFactory {

	@Autowired
	private ShortEventTimeServiceImpl shortEventTimeServiceImpl;
	@Autowired
	private AllDayEventTimeServiceImpl allDayEventTimeServiceImpl;

	public IEventTimeService getEventTimeService(boolean allDay) {
		return allDay ? allDayEventTimeServiceImpl : shortEventTimeServiceImpl;
	}
}
