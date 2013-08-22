package com.idc.seznam.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idc.seznam.HomeController;

@Repository(value = "UserDaoHibTmpl")
public class UserDaoHibTmpl implements UserDao {

	@Autowired
	private SessionFactory factory;
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);



	@Override
	public void addUser(User user) {
		// zmena, namisto open session je get currentSessionnamisto open session
		// je getCurrentSession
		logger.info("Jsem v addUser");
		Session session = factory.getCurrentSession();
		session.save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		logger.info("Jsem v listUsers");
		Session session = factory.getCurrentSession();
		String hql = "FROM User"; // HQL
		Query query = session.createQuery(hql);
		return (List<User>) query.list();
	}

	// DELETE FROM COCHNER_USERS WHERE USER_ID = 'uid'

	
	@Override
	public void removeUser(Integer uid) {
		logger.info("Jsem v removeUser-uid");
		Session session = factory.getCurrentSession();
		User user = (User) session.get(User.class, uid);
		session.delete(user);
	}
	
	
	@Override
	public void removeUser(User user) {
		logger.info("Jsem v removeUser-user");
		Session session = factory.getCurrentSession();
		session.delete(user);
	}

	
	@Override
	public void updateUser(User user) {		
		logger.info("Jsem v updateUser");
		Session session = factory.getCurrentSession();
		session.update(user);
	}

	
	@Override
	public User findUser(Integer uid) {
		logger.info("Jsem v updateUser");
		Session session = factory.getCurrentSession();
		User user = (User) session.get(User.class, uid);
		return user;
	}

}
