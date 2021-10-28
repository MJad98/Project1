package com.example.testdriver;


import com.example.models.TicketModel;

import com.example.dao.ReDBConnection;
import com.example.dao.TicketDaoImpl;

public class TestDriver {

	
	
	
	public static void main(String[] args) {
		
		ReDBConnection recon = new ReDBConnection();
	
		TicketDaoImpl tDao = new TicketDaoImpl(recon);

//		tDao.getResolvedTickets();//this works
//		tDao.getEmployeeTickets(1); //this works
//		tDao.getUnresolvedTickets(); //this works
		
//		tDao.getAllTickets();//this works
//		
//		TicketModel test = new TicketModel(25f, "Company", 2, 1, 1);
//		System.out.println(test);
//		tDao.insert(test); //this works now! stupid constructor
		

//	TicketModel test2 = new TicketModel();
//	test2 = tDao.callTicket(2);
}
}