package com.jwt.demo.dao;

import javax.persistence.EntityManagerFactory;

import com.jwt.demo.model.UserLogin;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthDaoImpl implements AuthDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthDaoImpl.class);

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public UserLogin getUserByUsername(String username) throws NonUniqueResultException {
		LOGGER.trace("inside getUserByUsername() daoImpl...");
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Query<UserLogin> query = session.createQuery("from UserLogin where userName = :userName", UserLogin.class);
		query.setParameter("userName", username);
		query.setMaxResults(1);
		UserLogin userLogin = query.uniqueResult();
		session.close();
		return userLogin;
	}
}
