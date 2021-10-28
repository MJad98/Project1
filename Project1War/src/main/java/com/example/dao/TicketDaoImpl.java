package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.example.models.TicketModel;
import com.example.service.LogHelper;

public class TicketDaoImpl {
	
	private LogHelper log = new LogHelper();
	
	ReDBConnection recon = new ReDBConnection();
	
	public TicketDaoImpl() {}
	
	public TicketDaoImpl(ReDBConnection recon) {
		this.recon=recon;
	}	

	public TicketModel callTicket(int ticketid) {//Calls singular stored ticket data from db
		TicketModel tmod = new TicketModel();
		try(Connection con = recon.getReDBConnection()) {
			String sql = "select * from ers_reimbursement where reimb_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ticketid);
			
			ResultSet rs = ps.executeQuery();
			System.out.println("In try block");
			if(rs.next()) {
				tmod.setTicketid(rs.getInt(1));
				tmod.setAmount(rs.getFloat(2));
				tmod.setAuthorid(rs.getInt(3));
				tmod.setResolverid(rs.getInt(4));
				tmod.setStatusid(rs.getInt(5));
				tmod.setTypeid(rs.getInt(6));
				return tmod;
				
			}
			}catch (SQLException e) {
				log.exceptionLogger(e);
				
		}
		return tmod;

}
	
public void updateTicket(TicketModel entity) {//sends updated ticket status to db
	try(Connection con = recon.getReDBConnection()){
	String sql = "update ers_reimbursement set reimb_status_id = ?, reimb_resolver = ? where reimb_id = ?";	
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setInt(1, entity.getStatusid());
	ps.setInt(2, entity.getResolverid());
	ps.setInt(3, entity.getTicketid());
	ps.execute();
	
	}catch(SQLException e) {
		log.exceptionLogger(e);
	}
}


	public TicketModel insert(TicketModel entity) { //inserts new ticket into db using insert statement
		try(Connection con = recon.getReDBConnection()){
		String sql = "insert into ers_reimbursement(reimb_amount, reimb_description, reimb_author, reimb_status_id, reimb_type_id) values(?, ?, ?, ?, ?)";	
		PreparedStatement cs = con.prepareStatement(sql);
		cs.setFloat(1, entity.getAmount());
		cs.setString(2, entity.getDescription());
		cs.setInt(3, entity.getAuthorid());
		cs.setInt(4, entity.getStatusid());
		cs.setInt(5, entity.getTypeid());
		cs.execute();
		
		}catch(SQLException e) {
			log.exceptionLogger(e);
		} return entity;
		} 

	public List<TicketModel> getAllTickets() {//Retrieves ALL tickets
		
		List<TicketModel> ticketList = new ArrayList();
		
		try(Connection con = recon.getReDBConnection()) {
			String sql = "select * from ers_reimbursement";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ticketList.add(new TicketModel(rs.getInt(1), rs.getFloat(2), rs.getString(3), rs.getString(4), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(5)));
			} 
		} catch (SQLException e) {
			log.exceptionLogger(e);
		}
		System.out.println(ticketList);
		return ticketList; 
		
	}
	
//	public List<TicketModel> getUnresolvedTickets() { //calls all unresolved tickets
//		List<TicketModel> urticketList = new ArrayList();
//		
//		try(Connection con = recon.getReDBConnection()) {
//			String sql = "select * from ers_reimbursement where reimb_resolver is null";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				urticketList.add(new TicketModel(rs.getInt(1), rs.getFloat(2), rs.getInt(7), rs.getInt(9), rs.getInt(10), rs.getString(5)));
//			}
//			
//		} catch (SQLException e) {
//			log.exceptionLogger(e);
//		
//	} System.out.println(urticketList);
//		return urticketList;
//	}
	
	public List<TicketModel> getEmployeeTickets(int authorid) {//Shows an employee all of their submitted tickets
		List<TicketModel> eticketList = new ArrayList();
		
		try(Connection con = recon.getReDBConnection()) {
		String sql = "select * from ers_reimbursement where reimb_author =?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, authorid);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			eticketList.add(new TicketModel(rs.getInt(1), rs.getFloat(2), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(5)));
		}
		
	} catch (SQLException e) {
		log.exceptionLogger(e);
	}
		System.out.println(eticketList);
		return eticketList;
	}

//	public List<TicketModel> getResolvedTickets() { //calls all resolved tickets
//		List<TicketModel> reticketList = new ArrayList();
//		
//		try(Connection con = recon.getReDBConnection()) {
//			String sql = "select * from ers_reimbursement where reimb_resolver is not null";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				reticketList.add(new TicketModel(rs.getInt(1), rs.getFloat(2), rs.getInt(7), rs.getInt(9), rs.getInt(10), rs.getString(5)));
//			}
//			
//		} catch (SQLException e) {
//			log.exceptionLogger(e);
//		
//	} System.out.println(reticketList);
//		return reticketList;
//	}

	

}




