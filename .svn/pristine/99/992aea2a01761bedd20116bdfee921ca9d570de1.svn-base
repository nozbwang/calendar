package com.zbwang.calendar.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author
 */
public class CalendarEvent implements Serializable {

	private static final Short ALL_DAY_YES = 1;

	public boolean allDayEvent() {
		return ALL_DAY_YES.equals(allDay);
	}

	public String getIOSStartTime() {
		return getIOSTime(startTime);
	}

	public String getIOSEndTime() {
		return getIOSTime(endTime);
	}

	public String getIOSTime(LocalDateTime time) {
		if (time == null) {
			return "";
		}
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(time);
	}

	/**
	 * 日历事件ID
	 */
	private Integer eventId;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 日历内容
	 */
	private String title;
	/**
	 * 开始时间
	 */
	private LocalDateTime startTime;
	/**
	 * 结束时间
	 */
	private LocalDateTime endTime;
	/**
	 * 整天标志 0-非整天 1-整天
	 */
	private Short allDay;
	/**
	 * 颜色
	 */
	private String color;
	/**
	 * 时间完成状态 1-新建 2-完成 3-未完成
	 */
	private Short eventFlag;
	/**
	 * 添加时间
	 */
	private Date addTime;
	/**
	 * 添加人
	 */
	private String addName;
	/**
	 * 添加人ID
	 */
	private Integer addId;

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Short getAllDay() {
		return allDay;
	}

	public void setAllDay(Short allDay) {
		this.allDay = allDay;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Short getEventFlag() {
		return eventFlag;
	}

	public void setEventFlag(Short eventFlag) {
		this.eventFlag = eventFlag;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAddName() {
		return addName;
	}

	public void setAddName(String addName) {
		this.addName = addName;
	}

	public Integer getAddId() {
		return addId;
	}

	public void setAddId(Integer addId) {
		this.addId = addId;
	}
}