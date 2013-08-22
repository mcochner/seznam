package com.idc.seznam.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.idc.seznam.HomeController;


public class UserServiceBasic implements UserService {

	private ArrayList<User> userList;
	static int counter = 1;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idc.seznam.UserServiceInterface#printUsers()
	 */
	
	public void printUsers() {
		for (User user : userList) {
			// logger.info(user.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idc.seznam.UserServiceInterface#saveUser(com.idc.seznam.User)
	 */
	@Override
	public void saveUser(User user) {
		int uid = user.getUid();

		if (uid == 0) {
			user.setUid(generateUid());
			userList.add(user);
		} else {
			User us = getUserByUid(user.getUid());
			us.updateUser(user);
		}
	}

	private static int generateUid() {
		return counter++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idc.seznam.UserServiceInterface#getUsers()
	 */
	@Override
	public List<User> getUsers() {
		return userList;
	}

	public UserServiceBasic() {
		// logger.info("Vytvoril jsem UserService!");
		userList = new ArrayList<User>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idc.seznam.UserServiceInterface#deleteUserByUid(int)
	 */
	@Override
	public void deleteUserByUid(int uid) {
		// pomale ale tak zatim staci...
		for (Iterator<User> iterator = userList.iterator(); iterator.hasNext();) {
			User u = iterator.next();
			if (u.getUid() == uid) {
				iterator.remove();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idc.seznam.UserServiceInterface#getUserByUid(int)
	 */
	@Override
	public User getUserByUid(int uid) {
		for (Iterator<User> iterator = userList.iterator(); iterator.hasNext();) {
			User u = iterator.next();
			if (u.getUid() == uid) {
				return u;
			}
		}
		return null;
	}

	@Override
	public void saveUsers(User... users) {
		// TODO Auto-generated method stub
		
	}

}
