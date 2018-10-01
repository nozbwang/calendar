package com.zbwang.calendar.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zbwang.calendar.constant.Constants;
import com.zbwang.calendar.domain.User;
import com.zbwang.calendar.service.IUserService;
import com.zbwang.calendar.service.impl.SpringContextUtil;
import com.zbwang.calendar.util.CookieUtil;
import com.zbwang.calendar.util.FormatUtil;
import com.zbwang.calendar.util.SecurityInfoHolder;
import com.zbwang.calendar.vo.BaseCommand;

public class LoginFilter extends OncePerRequestFilter {

	public void destroy() {
	}

	private String[] whiteSuffixs = new String[] { ".css", ".js", ".woff", ".woff2", ".png", ".jpg", ".jpeg" };

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		for (String whiteSuffix : whiteSuffixs) {
			if (request.getRequestURI().endsWith(whiteSuffix)) {
				filterChain.doFilter(request, response);
				return;
			}
		}
		String lvt = CookieUtil.getCookieValue(request, Constants.COOKIE_LOGIN_LVT);
		Date lastVistTime = FormatUtil.parseDailyTime(lvt);
		String userId = CookieUtil.getDecryptCookieValue(request, Constants.COOKIE_LOGIN);
		IUserService faceUserService = (IUserService) SpringContextUtil.getBean(IUserService.class);
		User user = faceUserService.getUser(NumberUtils.toInt(userId));
		if (lastVistTime != null && inLoginValidTime(lastVistTime) && user != null) {
			CookieUtil.addLVTCookie(response);
			SecurityInfoHolder.setSecurityInfo(new BaseCommand(user));
			filterChain.doFilter(request, response);
			SecurityInfoHolder.setSecurityInfo(null);
		}
		else if (request.getRequestURI().endsWith("/login")) {
			filterChain.doFilter(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/user/login");
		}
	}

	private boolean inLoginValidTime(Date lastVistTime) {
		return (new Date().getTime() - lastVistTime.getTime()) < Constants.VALID_VISIT_TIME;
	}
}
