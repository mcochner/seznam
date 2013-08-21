package com.idc.seznam.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idc.seznam.HomeController;

@Service
public class UserServiceImp implements UserServiceInterface {

	@Autowired
	UserDao userDao;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	public UserServiceImp() {
		logger.info("Vytvoril jsem UserServiceDao!");
	}
	
	public void saveUser(User user) {
		int uid = user.getUid();

		if (uid == 0) {
			userDao.addUser(user);
		} else {
			User us = getUserByUid(user.getUid());
			userDao.updateUser(user);
		}
	}

	public List<User> getUsers() {
		return userDao.listUsers();
	}


	public void deleteUserByUid(int uid) {
		userDao.removeUser(uid);
	}

	public User getUserByUid(int uid) {
		return userDao.findUser(uid);
	}

}
