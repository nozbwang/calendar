package com.zbwang.calendar.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbwang.calendar.dao.ICalendarEventDao;
import com.zbwang.calendar.dao.ICalendarTagDao;
import com.zbwang.calendar.domain.CalendarEvent;
import com.zbwang.calendar.domain.CalendarTag;
import com.zbwang.calendar.service.ICalendarService;

@Service
public class CalendarServiceImpl implements ICalendarService {

	@Autowired
	private EventTimeServiceFactory eventTimeServiceFactory;
	@Autowired
	private ICalendarEventDao calendarEventDao;
	@Autowired
	private ICalendarTagDao calendarTagDao;
	private static final Integer DEFAULT_ID = 0;
	private static final String DEFAULT_NAME = "admin";

	@Override
	public Integer addCalendarEvent(String title, String color, String startTime, boolean allDay) {
		CalendarEvent calendarEvent = new CalendarEvent();
		calendarEvent.setUserId(DEFAULT_ID);
		calendarEvent.setAddId(DEFAULT_ID);
		calendarEvent.setAddName(DEFAULT_NAME);
		calendarEvent.setAllDay(allDay ? (short) 1 : (short) 0);
		calendarEvent.setEventFlag((short) 1);
		calendarEvent.setTitle(title);
		calendarEvent.setColor(color);
		LocalDateTime start = eventTimeServiceFactory.getEventTimeService(allDay).getEventStartTime(startTime);
		calendarEvent.setStartTime(start);
		calendarEvent.setEndTime(eventTimeServiceFactory.getEventTimeService(allDay).getEndTimefromStartTime(start));
		calendarEventDao.insertCalendarEvent(calendarEvent);
		return calendarEvent.getEventId();
	}

	@Override
	public int deleteCalendarEvent(Integer eventId) {
		return calendarEventDao.deleteCalendarEvent(eventId);
	}

	@Override
	public int moveCalendarEvent(CalendarEvent event, Long days, Long hours, Long minutes, boolean allDay) {
		LocalDateTime startTime = eventTimeServiceFactory.getEventTimeService(allDay).getChangedStartTime(event.getStartTime(), days, hours, minutes);
		LocalDateTime endTime = eventTimeServiceFactory.getEventTimeService(allDay).getChangedEndTime(event, startTime, days, hours, minutes);
		return calendarEventDao.updateEventRange(event.getEventId(), startTime, endTime, allDay ? (short) 1 : (short) 0);
	}

	@Override
	public int resizeCalendarEvent(CalendarEvent event, Long days, Long hours, Long minutes) {
		LocalDateTime endTime = event.getEndTime().plusDays(days).plusHours(hours).plusMinutes(minutes);
		return calendarEventDao.updateEventEndTime(event.getEventId(), endTime);
	}

	@Override
	public Integer addCalendarTag(String title, String color) {
		CalendarTag calendarTag = new CalendarTag();
		calendarTag.setAddId(DEFAULT_ID);
		calendarTag.setAddName(DEFAULT_NAME);
		calendarTag.setColor(color);
		calendarTag.setTitle(title);
		calendarTag.setUserId(DEFAULT_ID);
		calendarTagDao.insertCalendarTag(calendarTag);
		return calendarTag.getTagId();
	}

	@Override
	public int deleteCalendarTag(Integer tagId) {
		return calendarTagDao.deleteCalendarTag(tagId);
	}
}
