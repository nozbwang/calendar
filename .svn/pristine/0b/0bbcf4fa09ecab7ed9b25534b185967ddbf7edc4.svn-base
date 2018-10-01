package com.zbwang.calendar.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.zbwang.calendar.domain.CalendarEvent;
import com.zbwang.calendar.service.IEventTimeService;

@Service
public class AllDayEventTimeServiceImpl implements IEventTimeService {

	@Override
	public LocalDateTime getEventStartTime(String startTime) {
		if (startTime == null) {
			return null;
		}
		startTime = startTime + "T00:00:00";
		return LocalDateTime.parse(startTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	@Override
	public LocalDateTime getEndTimefromStartTime(LocalDateTime startTime) {
		return startTime.plusDays(1);
	}

	@Override
	public LocalDateTime getChangedStartTime(LocalDateTime time, Long days, Long hours, Long minutes) {
		if (time == null) {
			return time;
		}
		return time.plusDays(days).truncatedTo(ChronoUnit.DAYS);
	}

	@Override
	public LocalDateTime getChangedEndTime(CalendarEvent event, LocalDateTime startTime, Long days, Long hours, Long minutes) {
		if (event.allDayEvent()) {
			return event.getEndTime().plusDays(days);
		}
		return startTime.plusDays(1);
	}
}
