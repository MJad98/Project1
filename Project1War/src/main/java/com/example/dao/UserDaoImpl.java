package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.example.models.UserModel;
import com.example.service.LogHelper;

public class UserDaoImpl {
	
	private LogHelper log = new LogHelper();
	
	ReDBConnection recon = new ReDBConnection();
	
	public UserDaoImpl() {}
	
	public UserDaoImpl(ReDBConnection recon) {
		this.recon=recon;
	}
	
	public UserModel callUser(String username) {//Calls stored user data from db
		UserModel umod = new UserModel();		//This works, note that it is case sensitive with input, as it should be
		try(Connection con = recon.getReDBConnection()) {
			String sql = "select * from ers_users where ers_username=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				umod.setUserid(rs.getInt(1));
				umod.setUsername(rs.getString(2));
				umod.setPassword(rs.getString(3));
				umod.setFirstname(rs.getString(4));
				umod.setLastname(rs.getString(5));
				umod.setEmail(rs.getString(6));
				umod.setRoleid(rs.getInt(7));
				return umod;
			}
			}catch (SQLException e) {
				log.exceptionLogger(e);
		}
		return umod;
	}
	
	public void insert(UserModel entity) {//Inserts new User into DB Table
		try(Connection con = recon.getReDBConnection()) { //THIS WORKS NOW!!!
			String sql = "{? = call insert_user(?,?,?,?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR); //return type
			cs.setString(2, entity.getUsername()); //string username
			cs.setString(3, entity.getPassword()); //string password
			cs.setString(4, entity.getFirstname()); //string firstname
			cs.setString(5, entity.getLastname()); //string lastname
			cs.setString(6, entity.getEmail()); //string email
			cs.setInt(7, entity.getRoleid()); //int role id
			cs.execute();
			
		} catch(SQLException e) {
			log.exceptionLogger(e);
		}
	}
	
}
