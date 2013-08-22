package com.idc.seznam.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.idc.seznam.HomeController;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	@Qualifier("UserDaoHibTmpl")
	UserDao userDao;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);


	@Transactional
	public void saveUsers(User ... users){
		for (User u : users) {
			int uid = u.getUid();
			if (uid == 0) {
				userDao.addUser(u);
			} else {
				userDao.updateUser(u);
			}

		}
	}

	@Transactional
	public void saveUser(User user) {
		int uid = user.getUid();

		if (uid == 0) {
			userDao.addUser(user);
		} else {
			userDao.updateUser(user);
		}
	}

	
	@Transactional(readOnly = true)
	public List<User> getUsers() {
		return userDao.listUsers();
	}

	@Transactional
	public void deleteUserByUid(int uid) {
		userDao.removeUser(uid);
	}

	@Transactional
	public User getUserByUid(int uid) {
		return userDao.findUser(uid);
	}

}
