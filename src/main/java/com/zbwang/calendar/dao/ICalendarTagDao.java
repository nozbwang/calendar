package com.zbwang.calendar.dao;

import java.util.List;

import com.zbwang.calendar.domain.CalendarTag;

public interface ICalendarTagDao {

	int insertCalendarTag(CalendarTag record);

	List<CalendarTag> listCalendarTags();

	int deleteCalendarTag(Integer tagId);

	CalendarTag selectByPrimaryKey(Integer tagId);

	int updateByPrimaryKey(CalendarTag record);
}