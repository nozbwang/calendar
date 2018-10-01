package com.zbwang.calendar.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.zbwang.calendar.domain.CalendarEvent;
import com.zbwang.calendar.service.IEventTimeService;

@Service
public class ShortEventTimeServiceImpl implements IEventTimeService {

	@Override
	public LocalDateTime getEventStartTime(String startTime) {
		if (startTime == null) {
			return null;
		}
		return LocalDateTime.parse(startTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	@Override
	public LocalDateTime getEndTimefromStartTime(LocalDateTime startTime) {
		LocalDateTime endTime = startTime.plusHours(2);
		if (endTime.getDayOfWeek() != startTime.getDayOfWeek()) {
			endTime = endTime.truncatedTo(ChronoUnit.DAYS).minus(1, ChronoUnit.SECONDS);
		}
		return endTime;
	}

	@Override
	public LocalDateTime getChangedStartTime(LocalDateTime time, Long days, Long hours, Long minutes) {
		if (time == null) {
			return time;
		}
		return time.plusDays(days).plusHours(hours).plusMinutes(minutes);
	}

	@Override
	public LocalDateTime getChangedEndTime(CalendarEvent event, LocalDateTime startTime, Long days, Long hours, Long minutes) {
		if (event.allDayEvent()) {
			return getEndTimefromStartTime(startTime);
		}
		return event.getEndTime().plusDays(days).plusHours(hours).plusMinutes(minutes);
	}
}
