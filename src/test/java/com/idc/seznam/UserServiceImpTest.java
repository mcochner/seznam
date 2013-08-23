package com.idc.seznam;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

import com.idc.seznam.hibernate.User;
import com.idc.seznam.hibernate.UserDao;
import com.idc.seznam.hibernate.UserServiceImp;




public class UserServiceImpTest {

	@InjectMocks
	private UserServiceImp service;
	
	@Mock
	private UserDao userDao;
	
	@BeforeMethod
	public void before(){
		MockitoAnnotations.initMocks(this);
	}
	
	@org.testng.annotations.Test
	public void test_bbb(){
		"aaa".toString();
	}
	
	@org.testng.annotations.Test
	public void test_aaa(){
	
		//prepare users tak aby vracely uid 0
		User mockedUser1 = mock(User.class);
		when(mockedUser1.getUid()).thenReturn(0);
		
		User mockedUser2 = mock(User.class);
		when(mockedUser2.getUid()).thenReturn(0);
		
		User mockedUser3 = mock(User.class);
		when(mockedUser3.getUid()).thenReturn(0);
		
		service.saveUsers(mockedUser1, mockedUser2);
		
		//service.saveUsers(users);
		//verify that mock was called
		verify(userDao).addUser(mockedUser1);	
		verify(userDao).addUser(mockedUser2);
	}
	
}
