package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.TicketDaoImpl;
import com.example.dao.UserDaoImpl;
import com.example.models.TicketModel;
import com.example.models.UserModel;
import com.example.service.LogHelper;
import com.example.service.TicketService;
import com.example.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserLoginController {
	
	public static LogHelper log = new LogHelper();
	
	public static TicketService tserv = new TicketService(new TicketDaoImpl());
	
	public static UserService userv = new UserService(new UserDaoImpl());
	
	public static String login(HttpServletRequest req) {
		System.out.println("In UserLoginController login function");
			if(!req.getMethod().equals("POST")) { //prevent login if client is not using an HTTPost
				return "html/unsuccessfullogin.html";
		}
		
		UserModel umod = userv.verifyUser(req.getParameter("username"), req.getParameter("password"));
		
		if(umod == null) {
			return "wrongcreds.change";
		}else if (umod.getRoleid()==1) {
			req.getSession().setAttribute("currentUser", umod);
				return "html/employeehome.html";
		}else if (umod.getRoleid()==0) {
			req.getSession().setAttribute("currentUser", umod);
				return "html/financemanagerhome.html";
		} else {
				return "wrongcreds.change";
		}
	}
	
	public static void getSessionUmod(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		UserModel umod = (UserModel)req.getSession().getAttribute("currentUser");
			res.getWriter().write(new ObjectMapper().writeValueAsString(umod));
		//we are grabbing the current user that is stored in the session during login
		//then we use the response object to send the current logged in user as a json to the front end
	}
	public static String createTicket(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		System.out.println("In UserLoginController ticket insert function"); //This does work, up to the point where tickets are inserted into the db
		
		try { 																//However, it does not redirect to the proper page. Not sure why, something is returning the wrong string
		
			TicketModel tmod = new TicketModel(Float.parseFloat(req.getParameter("amount")), req.getParameter("description"), Integer.parseInt(req.getParameter("userid")), 1, Integer.parseInt(req.getParameter("flexRadioDefault")));
			tserv.insertTicket(tmod);
			log.infoLogger(tmod.toString());	
					//this is still a problem 10/18
			return "html/employeehome.html";
		} catch(Exception e) {
				log.exceptionLogger(e);
				return "html/employeehome.html";
		}
	}
	
	public static void getTicketList(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		System.out.println("In getTicketList call");
		
		List<TicketModel> tList = new ArrayList();
		
		tList = tserv.getAllTickets();
		log.infoLogger(tList.toString());
		res.getWriter().write(new ObjectMapper().writeValueAsString(tList));
	
	}
	public static void getEmployeeTicketList(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		System.out.println("In getEmployeeTicketList call");
		
		List<TicketModel> eList = new ArrayList();
		UserModel umod = (UserModel)req.getSession().getAttribute("currentUser");
		log.infoLogger(umod.toString());
		
		eList = tserv.getEmployeeTickets(umod.getUserid());
		log.infoLogger(eList.toString() + "sending employee tickets to front end");
		res.getWriter().write(new ObjectMapper().writeValueAsString(eList));
	}

	public static void submitTicketUpdate(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		System.out.println("In submitTicketUpdate");
		
		BufferedReader reader = req.getReader();
        ObjectMapper mapper = new ObjectMapper();
		UserModel umod = (UserModel)req.getSession().getAttribute("currentUser");
		
        TicketModel tmod = mapper.readValue(reader.readLine(), TicketModel.class);
		tmod.setResolverid(umod.getUserid());
		tserv.updateTicket(tmod);
		
		log.infoLogger(tmod.toString() + "updated ticketmodel to persist");
		
		
	}
	

}
