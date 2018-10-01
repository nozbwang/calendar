package com.zbwang.calendar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.zbwang.calendar.dao.ICalendarEventDao;
import com.zbwang.calendar.dao.ICalendarTagDao;
import com.zbwang.calendar.domain.CalendarEvent;
import com.zbwang.calendar.domain.CalendarEventVo;
import com.zbwang.calendar.service.ICalendarService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/calendar")
public class CalendarController extends AbstractController {

	@Autowired
	private ICalendarService calendarService;
	@Autowired
	private ICalendarEventDao calendarEventDao;
	@Autowired
	private ICalendarTagDao calendarTagDao;

	@RequestMapping({ "" })
	public ModelAndView calendar() {
		return getModelAndView("tags", calendarTagDao.listCalendarTags(), "pages/calendar");
	}

	@RequestMapping("/event/getEvents")
	public void getCalendarEvents(HttpServletResponse response) throws IOException {
		List<CalendarEvent> events = calendarEventDao.listCalenderEventByMonth();
		List<CalendarEventVo> eventVOs = Lists.newArrayListWithCapacity(events.size());
		events.forEach(event -> {
			CalendarEventVo eventVo = new CalendarEventVo();
			eventVo.setId(event.getEventId());
			eventVo.setTitle(event.getTitle());
			eventVo.setAllDay(event.allDayEvent());
			eventVo.setColor(event.getColor());
			eventVo.setStart(event.getIOSStartTime());
			eventVo.setEnd(event.getIOSEndTime());
			eventVOs.add(eventVo);
		});
		response(response, JSONArray.fromObject(eventVOs).toString());
	}

	@RequestMapping("/event/add")
	public void addCalendarEvent(HttpServletResponse response, String title, String color, String startTime, boolean allDay) throws IOException {
		Integer eventId = calendarService.addCalendarEvent(title, color, startTime, allDay);
		responseAjax(response, true, eventId);
	}

	@RequestMapping("/event/delete")
	public void deleteCalendarEvent(HttpServletResponse response, Integer eventId) throws IOException {
		calendarService.deleteCalendarEvent(eventId);
		responseAjax(response, true);
	}

	@RequestMapping("/event/move")
	public void moveCalenderEvent(HttpServletResponse response, Integer eventId, Long days, Long hours, Long minutes, boolean allDay)
			throws IOException {
		CalendarEvent event = calendarEventDao.queryForCalenderEventByEventId(eventId);
		if (event == null) {
			responseAjax(response, false);
			return;
		}
		calendarService.moveCalendarEvent(event, days, hours, minutes, allDay);
		responseAjax(response, true);
	}

	@RequestMapping("/event/resize")
	public void resizeCalenderEvent(HttpServletResponse response, Integer eventId, Long days, Long hours, Long minutes) throws IOException {
		CalendarEvent event = calendarEventDao.queryForCalenderEventByEventId(eventId);
		if (event == null) {
			responseAjax(response, false);
		}
		calendarService.resizeCalendarEvent(event, days, hours, minutes);
		responseAjax(response, true);
	}

	@RequestMapping("/tag/add")
	public void addCalendarTag(HttpServletResponse response, String title, String color) throws IOException {
		Integer tagId = calendarService.addCalendarTag(title, color);
		responseAjax(response, true, tagId);
	}

	@RequestMapping("/tag/delete")
	public void deleteCalendarTag(HttpServletResponse response, Integer tagId) throws IOException {
		calendarService.deleteCalendarTag(tagId);
		responseAjax(response, true);
	}
}
