package com.zbwang.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController extends AbstractController {

	@RequestMapping({ "" })
	public String calendar() {
		return redirectTo("/voice");
	}

	@RequestMapping("/picture")
	public ModelAndView modal() {
		return getBaseModelAndView("pages/picture");
	}
}
