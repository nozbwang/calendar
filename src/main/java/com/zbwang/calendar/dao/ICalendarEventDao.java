package com.zbwang.calendar.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zbwang.calendar.domain.CalendarEvent;

public interface ICalendarEventDao {

	int insertCalendarEvent(CalendarEvent record);

	List<CalendarEvent> listCalenderEventByMonth();

	int deleteCalendarEvent(Integer eventId);

	CalendarEvent queryForCalenderEventByEventId(Integer eventId);

	int updateEventRange(@Param("eventId") Integer eventId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,
			@Param("allDay") Short allDay);

	int updateEventEndTime(@Param("eventId") Integer eventId, @Param("endTime") LocalDateTime endTime);
}