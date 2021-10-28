package com.example.service;

import com.example.dao.UserDaoImpl;
import com.example.models.UserModel;

public class UserService {
	
	private final LogHelper log = new LogHelper(); 
	
	private UserDaoImpl uDao;
	
	public UserService() {};
	
	public UserService(UserDaoImpl uDao) {
		super();
		this.uDao=uDao;
	}
	
	public UserModel verifyUser(String username, String password) {
		try{
			UserModel umod = uDao.callUser(username);
		
			if(umod != null) {
			if(umod.getPassword().equals(password)) { 
				log.infoLogger(umod.toString());
				return umod;
			}
		}
				return null;

	
		} catch(NullPointerException e) {
	e.printStackTrace();
	return null;
}
}
}