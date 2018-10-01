package com.zbwang.calendar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class FormatUtil {

	private static ThreadLocal<SimpleDateFormat> MINUTE_FORMAT = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm");
		};
	};
	private static ThreadLocal<SimpleDateFormat> JQUERY_MINUTE_FORMAT = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy/MM/dd HH:mm");
		};
	};
	private static ThreadLocal<SimpleDateFormat> DAILY_FORMAT = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		};
	};
	private static ThreadLocal<SimpleDateFormat> COLLECT_TIME_FORMAT = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmss");
		};
	};

	public static String formatMinuteTime(Date date) {
		if (date == null) {
			return MINUTE_FORMAT.get().format(new Date());
		}
		return MINUTE_FORMAT.get().format(date);
	}

	public static String formatDailyTime(Date date) {
		if (date == null) {
			return DAILY_FORMAT.get().format(new Date());
		}
		return DAILY_FORMAT.get().format(date);
	}

	public static Date parseDailyTime(String date) {
		try {
			if (StringUtils.isNotBlank(date)) {
				return MINUTE_FORMAT.get().parse(date);
			}
		}
		catch (ParseException e) {
			LogUtil.serviceLog.error("Fail to parse time :" + date, e);
		}
		return null;
	}

	public static Date parseJqueryDailyTime(String date) {
		try {
			if (StringUtils.isNotBlank(date)) {
				return JQUERY_MINUTE_FORMAT.get().parse(date);
			}
		}
		catch (ParseException e) {
			LogUtil.serviceLog.error("Fail to parse time :" + date, e);
		}
		return null;
	}

	public static Date getCollectTime(String date) {
		if (StringUtils.isBlank(date)) {
			return new Date();
		}
		try {
			return COLLECT_TIME_FORMAT.get().parse(date);
		}
		catch (ParseException e) {
			LogUtil.serviceLog.error("Fail to parse time :" + date);
		}
		return new Date();
	}
}
