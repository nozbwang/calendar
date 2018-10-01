package com.zbwang.calendar.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.zbwang.calendar.constant.Constants;

public class CookieUtil {

	private static final String Domain = "bobomeilin.com";

	public static String getCookieValue(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (ArrayUtils.isNotEmpty(cookies)) {
			for (Cookie cookie : cookies) {
				if (StringUtils.equals(key, cookie.getName())) {
					return getDecodeCookieValue(cookie);
				}
			}
		}
		return StringUtils.EMPTY;
	}

	public static String getDecryptCookieValue(HttpServletRequest request, String key) {
		String cookieValue = getCookieValue(request, key);
		return EncryptUtil.decrypt(cookieValue);
	}

	public static void removeLGCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(Constants.COOKIE_LOGIN, null);
		cookie.setDomain(Domain);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	public static void addLGCookie(HttpServletResponse response, String UserId) {
		Cookie cookie = new Cookie(Constants.COOKIE_LOGIN, EncryptUtil.encrypt(UserId));
		cookie.setDomain(Domain);
		cookie.setPath("/");
		cookie.setMaxAge(Constants.VALID_VISIT_TIME);
		addHttpOnlyCookie(response, cookie);
	}

	public static void addLVTCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(Constants.COOKIE_LOGIN_LVT, FormatUtil.formatMinuteTime(new Date()));
		cookie.setDomain(Domain);
		cookie.setPath("/");
		cookie.setMaxAge(Constants.VALID_VISIT_TIME);
		addHttpOnlyCookie(response, cookie);
	}

	public static void removeLVTCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(Constants.COOKIE_LOGIN_LVT, null);
		cookie.setDomain(Domain);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	public static void addHttpOnlyCookie(HttpServletResponse response, Cookie cookie) {
		// 依次取得cookie中的名称、值、最大生存时间、路径、域和是否为安全协议信息
		String cookieName = cookie.getName();
		String cookieValue = getEncodeCookieValue(cookie);
		int maxAge = cookie.getMaxAge();
		String path = cookie.getPath();
		String domain = cookie.getDomain();
		StringBuffer strBufferCookie = new StringBuffer();
		strBufferCookie.append(cookieName + "=" + cookieValue + ";");
		if (maxAge != Constants.COOKIE_SESSION) {
			strBufferCookie.append("Max-Age=" + cookie.getMaxAge() + ";");
		}
		if (domain != null) {
			strBufferCookie.append("domain=" + domain + ";");
		}
		if (path != null) {
			strBufferCookie.append("path=" + path + ";");
		}
		strBufferCookie.append("HTTPOnly;");
		response.addHeader("Set-Cookie", strBufferCookie.toString());
	}

	private static String getEncodeCookieValue(Cookie cookie) {
		try {
			return URLEncoder.encode(cookie.getValue(), "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			LogUtil.serviceLog.error("Fail to encode cookie value. ", e);
		}
		return StringUtils.EMPTY;
	}

	private static String getDecodeCookieValue(Cookie cookie) {
		try {
			return URLDecoder.decode(cookie.getValue(), "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			LogUtil.serviceLog.error("Fail to encode cookie value. ", e);
		}
		return StringUtils.EMPTY;
	}
}
