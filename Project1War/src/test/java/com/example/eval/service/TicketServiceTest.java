package com.example.eval.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.TicketDaoImpl;
import com.example.models.TicketModel;
import com.example.models.UserModel;
import com.example.service.TicketService;


public class TicketServiceTest {
	
	@Mock
	private UserModel umod;
	@Mock
	private TicketDaoImpl tDao;
	@Mock
	private TicketModel tmod;
	@Mock
	private TicketService tServ;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		umod = new UserModel(1, "Test", "Test", "Test", "Test", "Test", 1);
		tServ = new TicketService(tDao);
		tmod = new TicketModel(1, "Ham", 1, 1, 1);
		List<TicketModel> tList = new ArrayList();
		tList.add(tmod);
		
		when(tDao.getAllTickets()).thenReturn(tList);
		when(tDao.getEmployeeTickets(1)).thenReturn(tList);
	}
	
	@Test
	public void testGetAllTickets() {
		List<TicketModel> tList = new ArrayList<>();
		tList.add(tmod);
		assertEquals(tServ.getAllTickets(), tList);
	}
	
	
	@Test
	public void testGetEmployeeTickets() {
		List<TicketModel> tList = new ArrayList<>();
		tList.add(tmod);
		assertEquals(tServ.getEmployeeTickets(umod.getUserid()), tList);
	}
	
	
	@Test
	public void testUpdateTicket() {
		TicketService tServ2 = mock(TicketService.class);
		TicketModel tmod2 =  mock(TicketModel.class);
		tServ2.updateTicket(tmod2);
		verify(tServ2).updateTicket(tmod2);
	}
	
	@Test
	public void testCallTicket() {
		TicketDaoImpl tDao2= mock(TicketDaoImpl.class);
		tDao2.callTicket(1);
		verify(tDao2).callTicket(1);
		
	}
	
	@Test
	public void testInsertTicket() {
		TicketModel tmod2 = mock(TicketModel.class);
		TicketService tServ2 = mock(TicketService.class);
		tServ2.insertTicket(tmod2);
		verify(tServ2).insertTicket(tmod2);
		
	}
	
	
}
