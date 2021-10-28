package com.example.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.UserLoginController;
import com.example.models.UserModel;
import com.example.service.LogHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDispatcher {
	
	public static final LogHelper log = new LogHelper();
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
	
	switch(req.getRequestURI()) {
	
	case "/Project1War/getsessionemployee.json":
		System.out.println("In get session employee case, dispatcher");
		log.infoLogger("In get session employee, JsonDispatcher");
		UserLoginController.getSessionUmod(req, res);
		break;
		
	
	case "/Project1War/ticketlist.json":	
		System.out.println("In get ticketlist case, dispatcher");
		log.infoLogger("In get ticketlist, JsonDispatcher");
		UserLoginController.getTicketList(req, res);
		break;
	
	case "/Project1War/ticket.json":
		System.out.println("In post insert ticket case, dispatcher");
		log.infoLogger("In post insert ticket, JsonDispatcher");
		UserLoginController.createTicket(req, res);
		break;
			
	case "/Project1War/employeetickets.json":
		System.out.println("In get employeetickets case, dispatcher");
		log.infoLogger("In get employeetickets, JsonDispatcher");
		UserLoginController.getEmployeeTicketList(req, res);
		break;
		
	case "/Project1War/ticketupdate.json":
		System.out.println("In post ticketupdate case, dispatcher");
		log.infoLogger("In post ticketupdate, JsonDispatcher");
		UserLoginController.submitTicketUpdate(req, res);
		res.getWriter().write("Update Successful");
		break;
		
	default:
		System.out.println("In JSON default dispatcher");
		log.infoLogger("In default case, JsonDispatcher");
		res.getWriter().write(new ObjectMapper().writeValueAsString(new UserModel()));
		break;
	}
	}
}