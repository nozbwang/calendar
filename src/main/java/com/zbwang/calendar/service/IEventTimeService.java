package com.zbwang.calendar.service;

import java.time.LocalDateTime;

import com.zbwang.calendar.domain.CalendarEvent;

public interface IEventTimeService {

	/**
	 * 获取事件开始时间
	 */
	LocalDateTime getEventStartTime(String startTime);

	/**
	 * 获取事件结束时间
	 */
	LocalDateTime getEndTimefromStartTime(LocalDateTime startTime);

	/**
	 * 获取拖动后的事件开始时间
	 */
	LocalDateTime getChangedStartTime(LocalDateTime startTtime, Long days, Long hours, Long minutes);

	/**
	 * 获取拖动后的事件开始时间
	 */
	LocalDateTime getChangedEndTime(CalendarEvent event, LocalDateTime startTime, Long days, Long hours, Long minutes);
}
