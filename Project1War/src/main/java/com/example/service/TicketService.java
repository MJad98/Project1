package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.dao.TicketDaoImpl;
import com.example.models.TicketModel;

public class TicketService {
//methods in here should include:
//One to update ticket status and use the dao to send the info back to the db
	
	private final LogHelper log = new LogHelper(); 
	
	private TicketDaoImpl tDao;
	
	public TicketService() {}
	
	public TicketService(TicketDaoImpl tDao) {
		super();
		this.tDao=tDao;
	}
	
	public void approveTicket(int ticketid) {//sets status to approved and updates in db
		TicketModel tmod = tDao.callTicket(ticketid);
			if(tmod != null) {
				tmod.setStatusid(2);
				tDao.updateTicket(tmod);
				log.infoLogger(tmod.toString());
			}
	}
	
	public void denyTicket(int ticketid) {//sets status to denied and updates in db
		TicketModel tmod = tDao.callTicket(ticketid);
			if(tmod != null) {
				tmod.setStatusid(3);
				tDao.updateTicket(tmod);
				log.infoLogger(tmod.toString());
			}
	}
	
	public void updateTicket(TicketModel tmod) {
		tDao.updateTicket(tmod);
		log.infoLogger(tmod.toString());
	}
	
	
	public void insertTicket(TicketModel tmod) {
		tDao.insert(tmod);
		log.infoLogger(tmod.toString());
	}
	
	public List<TicketModel> getAllTickets() {
		List<TicketModel> tList = new ArrayList();
		tList = tDao.getAllTickets();
		return tList;
	}
	
	public List<TicketModel> getEmployeeTickets(int userid) {
		List<TicketModel> eList = new ArrayList();
		eList = tDao.getEmployeeTickets(userid);
		return eList;
	}
}
