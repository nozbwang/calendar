package com.zbwang.calendar.service;

import com.zbwang.calendar.domain.CalendarEvent;

public interface ICalendarService {

	Integer addCalendarEvent(String title, String color, String startTime, boolean allDay);

	int moveCalendarEvent(CalendarEvent event, Long days, Long hours, Long minutes, boolean allDay);

	int resizeCalendarEvent(CalendarEvent event, Long days, Long hours, Long minutes);

	Integer addCalendarTag(String title, String color);

	int deleteCalendarTag(Integer TagId);

	int deleteCalendarEvent(Integer eventId);
}