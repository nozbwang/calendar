package com.zbwang.calendar.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.zbwang.calendar.domain.User;
import com.zbwang.calendar.util.SecurityInfoHolder;

import net.sf.json.JSONObject;

public abstract class AbstractController {

	protected static final Logger serviceLog = LoggerFactory.getLogger("service");
	protected static final String SUCCESS = "1";
	protected static final String FAILURE = "0";

	public boolean isLogon() {
		User user = getUser();
		return user != null;
	}

	public User getUser() {
		if (SecurityInfoHolder.getSecurityInfo() == null) {
			return null;
		}
		return SecurityInfoHolder.getSecurityInfo().getUser();
	}

	public ModelAndView getModelAndView(String key, Object object, String view) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(key, object);
		modelAndView.setViewName(view);
		modelAndView.addObject("user", getUser());
		return modelAndView;
	}

	public ModelAndView getBaseModelAndView(Object object, String view) {
		return getModelAndView("command", object, view);
	}

	public ModelAndView getBaseModelAndView(Map<String, ?> modelMap, String view) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(modelMap);
		modelAndView.setViewName(view);
		modelAndView.addObject("user", getUser());
		return modelAndView;
	}

	public ModelAndView getBaseModelAndView(String view) {
		return getBaseModelAndView(null, view);
	}

	protected String redirectTo(String url) {
		return "redirect:" + url;
	}

	protected void response(HttpServletResponse response, String content) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");
		response.getWriter().write(content);
	}

	protected void responseAjax(HttpServletResponse response, boolean success) throws IOException {
		responseAjax(response, success, null);
	}

	protected void responseAjax(HttpServletResponse response, boolean success, Object content) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		resultMap.put("success", success);
		resultMap.put("content", content);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");
		response.getWriter().write(JSONObject.fromObject(resultMap).toString());
	}
}
