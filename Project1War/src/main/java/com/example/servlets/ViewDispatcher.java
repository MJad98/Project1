package com.example.servlets;

import javax.servlet.http.HttpServletRequest;

import com.example.controller.UserLoginController;
import com.example.service.LogHelper;

public class ViewDispatcher {
	
	public static LogHelper log = new LogHelper();
	
	public static String process(HttpServletRequest req) {
		
		switch(req.getRequestURI()) {
		
		case "/Project1War/login.change":
			System.out.println("in login.change dispatcher");
			log.infoLogger("In login.change, ViewDispatcher");
			return UserLoginController.login(req);
			
			
		default:
			System.out.println("In Default");
			log.infoLogger("In default case, ViewDispatcher");
			return "html/unsuccessfullogin.html";	
		}
	}
}
