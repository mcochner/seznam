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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idc.seznam.HomeController;

@Service(value = "UserDaoImp")
public class UserDaoImp implements UserDao {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private SessionFactory factory;

	// CREATE SEQUENCE "IDC_SAGE"."SAG_BOOKCASE_SEQ" MINVALUE 1 MAXVALUE
	// 9999999999 INCREMENT BY 1 START WITH 244459 NOCACHE NOORDER NOCYCLE ;

	// public UserDaoImp() {
	// factory = new
	// AnnotationConfiguration().addPackage("com.idc.seznam.hibernate").
	// addAnnotatedClass(User.class).configure().buildSessionFactory();
	// }

	@Override
	public void addUser(User user) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public List<User> listUsers() {
		Session session = factory.openSession();
		Transaction tx = null;
		List returnList = new ArrayList<User>();
		try {
			tx = session.beginTransaction();

			String hql = "FROM User"; // HQL
			Query query = session.createQuery(hql);
			returnList = query.list();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (List<User>) returnList;
	}

	// DELETE FROM COCHNER_USERS WHERE USER_ID = 'uid'

	@Override
	public void removeUser(Integer uid) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			// vymazu radek s uid v db
			User user = (User) session.get(User.class, uid);
			session.delete(user);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void updateUser(User user) {
		int uid = user.getUid();
		// TODO Jak updatovat usera

		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			// update s uid v db
			session.update(user);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public User findUser(Integer uid) {
		User user = null;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			user = (User) session.get(User.class, uid);
			logger.info(" find user: " + user.getLastName() + ", "
					+ user.getLastName());
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public void removeUser(User user) {
		int uid = user.getUid();
		removeUser(uid);
	}

}
