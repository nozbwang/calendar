package com.zbwang.calendar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zbwang.calendar.constant.Constants;
import com.zbwang.calendar.constant.MessageConstants;
import com.zbwang.calendar.domain.User;
import com.zbwang.calendar.service.IUserService;
import com.zbwang.calendar.util.CookieUtil;
import com.zbwang.calendar.util.SecurityInfoHolder;
import com.zbwang.calendar.vo.BaseCommand;
import com.zbwang.calendar.vo.UserCommand;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		if (isLogon()) {
			return getBaseModelAndView(redirectTo("/"));
		}
		return getBaseModelAndView("pages/login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginBorn(UserCommand userCommand, HttpServletRequest request, HttpServletResponse response) {
		if (isLogon()) {
		}
		if (userCommand.isLoginComplete()) {
			User faceUser = userService.getUser(userCommand.getUserName());
			if (faceUser != null && faceUser.getPassword().equals(userCommand.getPassword())) {
				recordLGFoot(request, response, faceUser.getUserId());
				SecurityInfoHolder.setSecurityInfo(new BaseCommand(faceUser));
				return getBaseModelAndView(redirectTo("/"));
			}
		}
		return getModelAndView("msg", MessageConstants.LOGIN_ERROR.getNotice(), "pages/login");
	}

	private void recordLGFoot(HttpServletRequest request, HttpServletResponse response, Integer userId) {
		CookieUtil.addLVTCookie(response);
		CookieUtil.addLGCookie(response, userId.toString());
		request.getSession().setAttribute(Constants.SESSION_LOGIN, userId.toString());
	}

	@RequestMapping("/loginOut")
	public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response) {
		CookieUtil.removeLGCookie(response);
		CookieUtil.removeLVTCookie(response);
		return getBaseModelAndView(redirectTo("/user/login"));
	}
}
