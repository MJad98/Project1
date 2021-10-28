package com.example.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ReDBConnection {
ClassLoader classLoader = getClass().getClassLoader();
InputStream is;
Properties p = new Properties();

public ReDBConnection() {
	is = classLoader.getResourceAsStream("connection.properties");
	try { 
		p.load(is);
	} catch(IOException e) {
		e.printStackTrace();
	}
	
}

public Connection getReDBConnection() throws SQLException {
	final String URL = p.getProperty("url");
	final String USERNAME = p.getProperty("username");
	final String PASSWORD = p.getProperty("password");
	try {
        Class.forName("org.postgresql.Driver");
    } catch(ClassNotFoundException e) {
        e.printStackTrace();
    }
	return DriverManager.getConnection(URL, USERNAME, PASSWORD);
}
}
