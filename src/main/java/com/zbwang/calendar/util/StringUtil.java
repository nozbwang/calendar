package com.zbwang.calendar.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	public static final Pattern pattern = Pattern.compile("~!([\\w]+)!~");

	public static String replaceVariables(String template, Map<String, String> variableMap) {
		Matcher matcher = pattern.matcher(template);
		while (matcher.find()) {
			template = StringUtils.replace(template, matcher.group(0), variableMap.get(matcher.group(1)));
		}
		return template;
	}

	public static String getHiddenName(String name) {
		if (StringUtils.isNotBlank(name)) {
			return name.substring(0, 1) + "***" + name.substring(name.length() - 1, name.length());
		}
		return "";
	}
}
