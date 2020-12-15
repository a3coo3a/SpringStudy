package com.team404.controller;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler.referenceInsertExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "home";
	}
	
	@RequestMapping("/aaa")
	public String aaa() {
		return "aaa";
	}
	
	@RequestMapping("/test/bbb")
	public String bbb() {
		return "test/bbb";
	}
	
}
