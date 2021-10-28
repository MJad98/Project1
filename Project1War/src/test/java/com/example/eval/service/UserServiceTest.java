package com.example.eval.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.UserDaoImpl;
import com.example.models.UserModel;
import com.example.service.UserService;

public class UserServiceTest {
	
	@Mock
	private UserDaoImpl uDao;
	@Mock
	private UserModel umod;
	@Mock
	private UserService uServ;
	
	@BeforeEach
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		
		
		uServ = new UserService(uDao);
		umod = new UserModel(1, "Test", "Test", "Test", "Test", "Test", 1);
		
		when(uDao.callUser("Test")).thenReturn(umod);
	}
	
	@Test
	public void testVeryifyUserSuccess() {
		assertEquals(uServ.verifyUser("Test", "Test"), umod);
	}
	
	@Test
	public void testVerifyUserFailure() {
		assertEquals(uServ.verifyUser("sdlkfjs", "laksjd"), null);
	}
	
	@Test
	public void insertUserTest() {
		UserModel umod2 = mock(UserModel.class);
		UserDaoImpl uDao2 = mock(UserDaoImpl.class);
		uDao2.insert(umod2);
		verify(uDao2).insert(umod2);
		
	}
	
	
}
